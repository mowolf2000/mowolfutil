package holiday

import (
	"time"
)

type HolidayList []time.Time

var (
	holidayPerYear map[int]HolidayList
)

/**
 * 16 Bundesländer der BRD. Zusätzlich gibt es eine Trennung in Bayern, Sachsen und Thüringen nach
 * protestantischen und katholischen Gemeinden und Kreisen. Außerdem gibt es in Augsburg (BAYERN_AUGSBURG)
 * mit dem Friedensfest einen zusätzlichen Feiertag:.
 *
 * Sachsen_katholisch gilt für folgende katholisch geprägten Gemeinden des sorbischen Siedlungsgebietes im Landkreis Bautzen:
 * - Bautzen (nur in den Ortsteilen Bolbritz und Salzenforst),
 * - Crostwitz,
 * - Göda (nur im Ortsteil Prischwitz),
 * - Großdubrau (nur im Ortsteil Sdier),
 * - Hoyerswerda (nur im Ortsteil Dörgenhausen),
 * - Königswartha (nicht im Ortsteil Wartha),
 * - Nebelschütz,
 * - Neschwitz (nur in den Ortsteilen Neschwitz und Saritsch),
 * - Panschwitz-Kuckau,
 * - Puschwitz,
 * - Räckelwitz,
 * - Radibor,
 * - Ralbitz-Rosenthal und
 * - Wittichenau.
 * Entscheidend ist dabei der Arbeitsort, nicht der Wohnort eines Arbeitnehmers.
 *
 * Thueringen_katholisch gilt nur
 * - im Landkreis Eichsfeld sowie
 * - in folgenden Gemeinden des Unstrut-Hainich-Kreises und des Wartburgkreises:
 * -- Anrode (nur in den Ortsteilen Bickenriede und Zella),
 * -- Brunnhartshausen (nur in den Ortsteilen Föhlritz und Steinberg),
 * -- Buttlar,
 * -- Dünwald (nur in den Ortsteilen Beberstedt und Hüpstedt),
 * -- Geisa,
 * -- Rodeberg (nur im Ortsteil Struth),
 * -- Schleid,
 * -- Südeichsfeld und
 * -- Zella/Rhön.
 *
 * Mariä Himmelfahrt gilt in Bayern nur
 * - in den derzeit ca. 1700 Gemeinden mit überwiegend katholischer Bevölkerung (Bayern_katholisch und Bayern_Augsburg).
 * - In den restlichen ca. 350 bayerischen Gemeinden (Bayern_protestantisch) der Tag kein gesetzlicher Feiertag.
 */
type Bundesland int

const (
	BadenWuertemberg Bundesland = iota
	Bayern_katholisch
	Bayern_protestantisch
	Bayern_Augsburg
	Berlin
	Brandenburg
	Bremen
	Hamburg
	Hessen
	MecklenburgVorpommern
	Niedersachsen
	NordrheinWestfahlen
	RheinlandPfalz
	Saarland
	SachenAnhalt
	Sachsen_protestantisch
	Sachsen_katholisch
	SchleswigHolstein
	Thueringen_protestantisch
	Thueringen_katholisch
)

func init() {
	holidayPerYear = make(map[int]HolidayList)
}

func generateDate(jahr int, monat time.Month, tag int) time.Time {
	return time.Date(jahr, monat, tag, 0, 0, 0, 0, time.UTC)
}

/**
 * Berechnet den Ostersonntag für ein Jahr.
 *
 * @param jahr betrachtetes Jahr
 * @return berechneter Ostersonntag für das Jahr
 * @see "https://de.wikibooks.org/wiki/Algorithmensammlung:_Kalender:_Feiertage"
 */
func calcEasterSunday(jahr int) time.Time {
	// k   die Säkularzahl
	// m   die säkulare Mondschaltung
	// s   die säkulare Sonnenschaltung
	// a   der Mondparameter
	// d   der Keim für den ersten Vollmond im Frühling
	// r   die kalendarische Korrekturgröße
	// og  die Ostergrenze
	// sz  der ersten Sonntag im März
	// oe  die Entfernung des Ostersonntags von der Ostergrenze
	// os  das Datum des Ostersonntags als Märzdatum (32.März = 1.April)

	k := jahr / 100
	m := 15 + (3*k+3)/4 - (8*k+13)/25
	s := 2 - (3*k+3)/4
	a := jahr % 19
	d := (19*a + m) % 30
	r := (d + a/11) / 29
	og := 21 + d - r
	sz := 7 - (jahr+jahr/4+s)%7
	oe := 7 - (og-sz)%7
	os := og + oe

	osterMonat := 2 + (os+30)/31
	osterTag := os - 31*(osterMonat/4)

	return generateDate(jahr, time.Month(osterMonat), osterTag)
}

/**
 * Berechnet den Buß- und Bettag für ein Jahr.
 *
 * @param year betrachtetes Jahr
 * @return berechneter Buß- und Bettag
 * @see "https://de.wikibooks.org/wiki/Algorithmensammlung:_Kalender:_Feiertage"
 */
func calcPenanceDay(year int) time.Time {
	// Der Buß- und Bettag ist immer ein Mittwoch, er liegt zwischen dem 16. und 22. November
	date := generateDate(year, time.November, 22)
	currentDayOfWeek := date.Weekday()
	if currentDayOfWeek == time.Wednesday {
		return date
	}

	date = date.AddDate(0, 0, int(time.Wednesday-currentDayOfWeek))
	if currentDayOfWeek < time.Wednesday {
		return date.AddDate(0, 0, -7)
	}

	return date
}

/**
 * Liefert die Liste aller Feiertage in dem Jahr zu dem angegebenen Bundesland.
 *
 * @param year betrachtets Jahr
 * @param bl betrachtets Bundesland
 * @return Slice mit allen Feiertagen in dem Jahr für dieses Bundesland
 */
func calcHolidaysOfYear(year int, bl Bundesland) HolidayList {
	// https://www.dgb.de/gesetzliche-feiertage-deutschland

	osterSonntag := calcEasterSunday(year)
	holidays := make(HolidayList, 0)

	holidays = append(holidays, generateDate(year, time.January, 1)) // Neujahr

	switch bl {
	case BadenWuertemberg,
		Bayern_protestantisch,
		Bayern_katholisch,
		Bayern_Augsburg,
		SachenAnhalt:
		holidays = append(holidays, generateDate(year, time.January, 6)) // Hl. 3 Könige
	}

	// holidays = append(holidays, osterSonntag.AddDate(0, 0, -48)) // Rosenmontag

	switch bl {
	case Berlin,
		MecklenburgVorpommern:
		holidays = append(holidays, generateDate(year, time.March, 8)) // Internationaler Frauentag
	}

	holidays = append(holidays, osterSonntag.AddDate(0, 0, -2)) // Karfreitag
	holidays = append(holidays, osterSonntag)                   // Ostersonntag
	holidays = append(holidays, osterSonntag.AddDate(0, 0, 1))  // Ostermontag
	holidays = append(holidays, osterSonntag.AddDate(0, 0, 39)) // Christi Himmelfahrt
	holidays = append(holidays, osterSonntag.AddDate(0, 0, 49)) // Pfingstsonntag
	holidays = append(holidays, osterSonntag.AddDate(0, 0, 50)) // Pfingstmontag

	holidays = append(holidays, generateDate(year, time.May, 1)) // Tag der Arbeit

	switch bl {
	case BadenWuertemberg,
		Bayern_protestantisch,
		Bayern_katholisch,
		Bayern_Augsburg,
		Hessen,
		NordrheinWestfahlen,
		RheinlandPfalz,
		Saarland,
		Sachsen_katholisch,
		Thueringen_katholisch:
		holidays = append(holidays, osterSonntag.AddDate(0, 0, 60)) // Fronleichnam
	}

	if bl == Bayern_Augsburg {
		holidays = append(holidays, generateDate(year, time.August, 8)) // Augsburger Friedensfest
	}

	switch bl {
	case Saarland,
		Bayern_katholisch,
		Bayern_Augsburg:
		holidays = append(holidays, generateDate(year, time.August, 15)) // Mariä Himmelfahrt
	}

	switch bl {
	case Thueringen_protestantisch,
		Thueringen_katholisch:
		holidays = append(holidays, generateDate(year, time.September, 20)) // Weltkindertag
	}

	if year <= 1990 {
		holidays = append(holidays, generateDate(year, time.June, 17)) // Tag der dt. Einheit
	} else {
		holidays = append(holidays, generateDate(year, time.October, 3)) // Tag der dt. Einheit
	}

	if year != 2017 {
		switch bl {
		case Brandenburg,
			Bremen,
			Hamburg,
			MecklenburgVorpommern,
			Niedersachsen,
			Sachsen_protestantisch,
			Sachsen_katholisch,
			SachenAnhalt,
			SchleswigHolstein,
			Thueringen_protestantisch,
			Thueringen_katholisch:
			holidays = append(holidays, generateDate(year, time.October, 31)) // Reformationstag
		}
	} else {
		holidays = append(holidays, generateDate(year, time.October, 31)) // Reformationstag
	}

	switch bl {
	case BadenWuertemberg,
		Bayern_protestantisch,
		Bayern_katholisch,
		Bayern_Augsburg,
		NordrheinWestfahlen,
		RheinlandPfalz,
		Saarland:
		holidays = append(holidays, generateDate(year, time.November, 1)) // Allerheiligen
	}

	if year < 1995 {
		holidays = append(holidays, calcPenanceDay(year)) // Buß- und Bettag
	} else {
		switch bl {
		case Sachsen_protestantisch,
			Sachsen_katholisch:
			holidays = append(holidays, calcPenanceDay(year)) // Buß- und Bettag
		}
	}

	holidays = append(holidays, generateDate(year, time.December, 25)) // 1. Weihnachtstag
	holidays = append(holidays, generateDate(year, time.December, 26)) // 2. Weihnachtstag

	return holidays
}

/**
 * Liefert die Liste aller Feiertage in dem Jahr zu dem angegebenen Bundesland.
 *
 * @param year betrachtets Jahr
 * @param bl betrachtets Bundesland
 * @return Slice mit allen Feiertagen in dem Jahr für dieses Bundesland
 */
func HolidaysOfYear(year int, bl Bundesland) HolidayList {
	key := year*100 + int(bl)
	holidays := holidayPerYear[key]
	if holidays == nil {
		holidays = calcHolidaysOfYear(year, bl)
		holidayPerYear[key] = holidays
	}
	return holidays
}

/**
 * Prüft, ob der übergebene Tag ein Feiertag ist.
 *
 * Wenn für das Jahr des Tags noch keine Feiertagsliste hinterlegt ist,
 * wird für dieses Jahr die Feiertagsliste aktualisiert
 *
 * @param day zu prüfender Tag
 * @param bl betrachtets Bundesland
 * @return true, wenn es ein Feiertag ist.
 */
func IsHoliday(day time.Time, bl Bundesland) bool {
	holidays := HolidaysOfYear(day.Year(), bl)
	for _, h := range holidays {
		if IsSameDate(h, day) {
			return true
		}
	}
	return false
}

func IsSameDate(d1 time.Time, d2 time.Time) bool {
	return d1.Year() == d2.Year() && d1.Month() == d2.Month() && d1.Day() == d2.Day()
}

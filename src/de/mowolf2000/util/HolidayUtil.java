package de.mowolf2000.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * The Class HolidayUtil.
 *
 * Hilfsklasse zur Ermittlung der Feiertage in Deutschland
 */
public class HolidayUtil {
    private static Map<Integer, Set<LocalDate>> holidayPerYear = Collections.synchronizedMap(new WeakHashMap<>());

    /**
     * 16 Bundesländer der BRD. Zusätzlich gibt es eine Trennung in Bayern, Sachsen und Thüringen nach
     * protestantischen und katholischen Gemeinden und Kreisen. Außerdem gibt es in Augsburg (BAYERN_AUGSBURG)
     * mit dem Friedensfest einen zusätzlichen Feiertag:.
     *
     * SACHSEN_KATH gilt für folgende katholisch geprägten Gemeinden des sorbischen Siedlungsgebietes im Landkreis Bautzen:
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
     * THUERINGEN_KATH gilt nur
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
     * - in den derzeit ca. 1700 Gemeinden mit überwiegend katholischer Bevölkerung (BAYERN_KATH und BAYERN_AUGSBURG).
     * - In den restlichen ca. 350 bayerischen Gemeinden (BAYERN_PROT) der Tag kein gesetzlicher Feiertag.
     */
    public enum Bundesland {
        BADEN_WUERTEMBERG,
        BAYERN_KATH,
        BAYERN_PROT,
        BAYERN_AUGSBURG,
        BERLIN,
        BRANDENBURG,
        BREMEN,
        HAMBURG,
        HESSEN,
        MECKLENBURG_VORPOMMERN,
        NIEDERSACHSEN,
        NORDRHEIN_WESTFALEN,
        RHEINLAND_PFALZ,
        SAARLAND,
        SACHSEN_ANHALT,
        SACHSEN_PROT,
        SACHSEN_KATH,
        SCHLESWIG_HOLSTEIN,
        THUERINGEN_PROT,
        THUERINGEN_KATH
    }

    /**
     * Berechnet den Ostersonntag für ein Jahr.
     *
     * @param jahr betrachtetes Jahr
     * @return berechneter Ostersonntag für das Jahr
     * @see "https://de.wikibooks.org/wiki/Algorithmensammlung:_Kalender:_Feiertage"
     */
    // package for testing
    static LocalDate calcEasterSunday(int jahr) {
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

        int k = jahr / 100;
        int m = 15 + (3 * k + 3) / 4 - (8 * k + 13) / 25;
        int s = 2 - (3 * k + 3) / 4;
        int a = jahr % 19;
        int d = (19 * a + m) % 30;
        int r = (d + a / 11) / 29;
        int og = 21 + d - r;
        int sz = 7 - (jahr + jahr / 4 + s) % 7;
        int oe = 7 - (og - sz) % 7;
        int os = og + oe;

        int osterMonat = 2 + (os + 30) / 31;
        int osterTag = os - 31 * (osterMonat / 4);

        return LocalDate.of(jahr, osterMonat, osterTag);
    }

    /**
     * Berechnet den Buß- und Bettag für ein Jahr.
     *
     * @param year betrachtetes Jahr
     * @return berechneter Buß- und Bettag
     * @see "https://de.wikibooks.org/wiki/Algorithmensammlung:_Kalender:_Feiertage"
     */
    // package for testing
    static LocalDate calcPenanceDay(int year) {
        // Der Buß- und Bettag ist immer ein Mittwoch, er liegt zwischen dem 16. und 22. November
        LocalDate date = LocalDate.of(year, Month.NOVEMBER, 22);
        DayOfWeek currentDayOfWeek = date.getDayOfWeek();
        if (currentDayOfWeek == DayOfWeek.WEDNESDAY) {
            return date;
        }

        date = date.plusDays(DayOfWeek.WEDNESDAY.ordinal() - currentDayOfWeek.ordinal());
        if (currentDayOfWeek.ordinal() < DayOfWeek.WEDNESDAY.ordinal()) {
            return date.minusDays(7);
        }

        return date;
    }

    /**
     * Liefert die Liste aller Feiertage in dem Jahr zu dem angegebenen Bundesland.
     *
     * @param year betrachtets Jahr
     * @param bl betrachtets Bundesland
     * @return Set mit allen Feiertagen in dem Jahr für dieses Bundesland
     */
    private static Set<LocalDate> calcHolidaysOfYear(int year, Bundesland bl) {
        // https://www.dgb.de/gesetzliche-feiertage-deutschland

        LocalDate osterSonntag = calcEasterSunday(year);
        Set<LocalDate> holidays = new HashSet<>();

        holidays.add(LocalDate.of(year, Month.JANUARY, 1));             // Neujahr
        switch (bl) {
            case BADEN_WUERTEMBERG:
            case BAYERN_PROT:
            case BAYERN_KATH:
            case BAYERN_AUGSBURG:
            case SACHSEN_ANHALT:
                holidays.add(LocalDate.of(year, Month.JANUARY, 6));     // Hl. 3 Könige
                break;
            default:
                break;
        }
        // holidays.add(osterSonntag.minusDays(48));                    // Rosenmontag
        switch (bl) {
            case BERLIN:
            case MECKLENBURG_VORPOMMERN:
                holidays.add(LocalDate.of(year, Month.MARCH, 8));       // Internationaler Frauentag
                break;
            default:
                break;
        }
        holidays.add(osterSonntag.minusDays(2));                        // Karfreitag
        holidays.add(osterSonntag);                                     // Ostersonntag
        holidays.add(osterSonntag.plusDays(1));                         // Ostermontag
        holidays.add(LocalDate.of(year, Month.MAY, 1));                 // Tag der Arbeit
        holidays.add(osterSonntag.plusDays(39));                        // Christi Himmelfahrt
        holidays.add(osterSonntag.plusDays(49));                        // Pfingstsonntag
        holidays.add(osterSonntag.plusDays(50));                        // Pfingstmontag
        switch (bl) {
            case BADEN_WUERTEMBERG:
            case BAYERN_PROT:
            case BAYERN_KATH:
            case BAYERN_AUGSBURG:
            case HESSEN:
            case NORDRHEIN_WESTFALEN:
            case RHEINLAND_PFALZ:
            case SAARLAND:
            case SACHSEN_KATH:
            case THUERINGEN_KATH:
                holidays.add(osterSonntag.plusDays(60));                // Fronleichnam
                break;
            default:
                break;
        }
        if (bl == Bundesland.BAYERN_AUGSBURG) {
            holidays.add(LocalDate.of(year, Month.AUGUST, 8));          // Augsburger Friedensfest
        }
        switch (bl) {
            case SAARLAND:
            case BAYERN_KATH:
            case BAYERN_AUGSBURG:
                holidays.add(LocalDate.of(year, Month.AUGUST, 15));     // Mariä Himmelfahrt
                break;
            default:
                break;
        }
        switch (bl) {
            case THUERINGEN_PROT:
            case THUERINGEN_KATH:
                holidays.add(LocalDate.of(year, Month.SEPTEMBER, 20));  // Weltkindertag
                break;
            default:
                break;
        }
        if (year <= 1990) {
            holidays.add(LocalDate.of(year, Month.JUNE, 17));           // Tag der dt. Einheit
        }
        else {
            holidays.add(LocalDate.of(year, Month.OCTOBER, 3));         // Tag der dt. Einheit
        }
        switch (bl) {
            case BRANDENBURG:
            case BREMEN:
            case HAMBURG:
            case MECKLENBURG_VORPOMMERN:
            case NIEDERSACHSEN:
            case SACHSEN_PROT:
            case SACHSEN_KATH:
            case SACHSEN_ANHALT:
            case SCHLESWIG_HOLSTEIN:
            case THUERINGEN_PROT:
            case THUERINGEN_KATH:
                holidays.add(LocalDate.of(year, Month.OCTOBER, 31));    // Reformationstag
                break;
            case BADEN_WUERTEMBERG:
            case BAYERN_PROT:
            case BAYERN_KATH:
            case BAYERN_AUGSBURG:
            case NORDRHEIN_WESTFALEN:
            case RHEINLAND_PFALZ:
            case SAARLAND:
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1));    // Allerheiligen
                break;
            default:
                break;
        }
        if (year == 2017) {
            holidays.add(LocalDate.of(year, Month.OCTOBER, 31));        // Reformationstag
        }

        if (year < 1995) {
            holidays.add(calcPenanceDay(year));                         // Buß- und Bettag
        }
        else {
            switch (bl) {
                case SACHSEN_PROT:
                case SACHSEN_KATH:
                    holidays.add(calcPenanceDay(year));                 // Buß- und Bettag
                    break;
                default:
                    break;
            }
        }
        holidays.add(LocalDate.of(year, Month.DECEMBER, 25));           // 1. Weihnachtstag
        holidays.add(LocalDate.of(year, Month.DECEMBER, 26));           // 2. Weihnachtstag

        return Collections.unmodifiableSet(holidays);
    }

    /**
     * Liefert die Liste aller Feiertage in dem Jahr zu dem angegebenen Bundesland.
     *
     * @param year betrachtets Jahr
     * @param bl betrachtets Bundesland
     * @return Set mit allen Feiertagen in dem Jahr für dieses Bundesland
     */
    public static synchronized Set<LocalDate> holidays(int year, Bundesland bl) {
        int key = year * 100 + bl.ordinal();
        Set<LocalDate> holidays = holidayPerYear.get(key);
        if (holidays == null) {
            holidays = calcHolidaysOfYear(year, bl);
            holidayPerYear.put(key, holidays);
        }
        return holidays;
    }

    /**
     * Prüft, ob der übergebene Tag ein Feiertag ist.
     *
     * Wenn für das Jahr des Tags noch keine Feiertagsliste hinterlegt ist,
     * wird für dieses Jahr die Feiertagsliste aktualisiert
     *
     * @param ts zu prüfender Tag
     * @param bl betrachtets Bundesland
     * @return true, wenn es ein Feiertag ist.
     */
    public static boolean isHoliday(LocalDateTime ts, Bundesland bl) {
        return isHoliday(ts.toLocalDate(), bl);
    }

    /**
     * Prüft, ob der übergebene Tag ein Feiertag ist.
     *
     * Wenn für das Jahr des Tags noch keine Feiertagsliste hinterlegt ist,
     * wird für dieses Jahr die Feiertagsliste aktualisiert
     *
     * @param instant zu prüfender Tag
     * @param bl betrachtets Bundesland
     * @return true, wenn es ein Feiertag ist.
     */
    public static boolean isHoliday(Instant instant, Bundesland bl) {
        return isHoliday(instant.atZone(ZoneId.systemDefault()).toLocalDate(), bl);
    }

    /**
     * Prüft, ob der übergebene Tag ein Feiertag ist.
     *
     * Wenn für das Jahr des Tags noch keine Feiertagsliste hinterlegt ist,
     * wird für dieses Jahr die Feiertagsliste aktualisiert
     *
     * @param date zu prüfender Tag
     * @param bl betrachtets Bundesland
     * @return true, wenn es ein Feiertag ist.
     */
    public static boolean isHoliday(Date date, Bundesland bl) {
        return isHoliday(date.toInstant(), bl);
    }

    /**
     * Prüft, ob der übergebene Tag ein Feiertag ist.
     *
     * Wenn für das Jahr des Tags noch keine Feiertagsliste hinterlegt ist,
     * wird für dieses Jahr die Feiertagsliste aktualisiert
     *
     * @param cal zu prüfender Tag
     * @param bl betrachtets Bundesland
     * @return true, wenn es ein Feiertag ist.
     */
    public static boolean isHoliday(Calendar cal, Bundesland bl) {
        return isHoliday(LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)), bl);
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
    public static boolean isHoliday(LocalDate day, Bundesland bl) {
        return holidays(day.getYear(), bl).contains(day);
    }
}

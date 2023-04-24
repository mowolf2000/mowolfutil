package holiday

import (
	"testing"
	"time"
)

func assertEqualsDate(t *testing.T, exp time.Time, val time.Time) {
	if !IsSameDate(exp, val) {
		t.Errorf("ERROR: exp %v is not equals val=%v", exp, val)
	}
}

func assertHoliday(t *testing.T, exp bool, val time.Time) {
	if exp != IsHoliday(val, Hessen) {
		if exp {
			t.Errorf("ERROR: Day %v not found as holiday", val)
		} else {
			t.Errorf("ERROR: Day %v found as holiday", val)
		}
	}
}

/**
 * Test method for {@link HolidayUtil#isHoliday(java.util.Calendar, Bundesland)}.
 */
func TestIsHoliday(t *testing.T) {
	// Neujahr 1.1
	assertHoliday(t, true, generateDate(2023, time.January, 1))
	assertHoliday(t, false, generateDate(2023, time.January, 2))

	// Hl. 3 Könige 6.1.
	assertHoliday(t, false, generateDate(2023, time.January, 5))
	assertHoliday(t, false, generateDate(2023, time.January, 6))
	assertHoliday(t, false, generateDate(2023, time.January, 7))

	// Internationaler Frauentag 8.3.
	assertHoliday(t, false, generateDate(2023, time.March, 7))
	assertHoliday(t, false, generateDate(2023, time.March, 8))
	assertHoliday(t, false, generateDate(2023, time.March, 9))

	// Gründonnerstag, Karfreitag, Karsamstag, Ostersonntag, Ostermontag, Osterdienstag
	assertHoliday(t, false, generateDate(2023, time.April, 6))
	assertHoliday(t, true, generateDate(2023, time.April, 7))
	assertHoliday(t, false, generateDate(2023, time.April, 8))
	assertHoliday(t, true, generateDate(2023, time.April, 9))
	assertHoliday(t, true, generateDate(2023, time.April, 10))
	assertHoliday(t, false, generateDate(2023, time.April, 11))

	// Tag der Arbeit 1.5.
	assertHoliday(t, false, generateDate(2023, time.April, 30))
	assertHoliday(t, true, generateDate(2023, time.May, 1))
	assertHoliday(t, false, generateDate(2023, time.May, 2))

	// Christi Himmelfahrt
	assertHoliday(t, false, generateDate(2023, time.May, 17))
	assertHoliday(t, true, generateDate(2023, time.May, 18))
	assertHoliday(t, false, generateDate(2023, time.May, 19))

	// Pfingsten
	assertHoliday(t, false, generateDate(2023, time.May, 27))
	assertHoliday(t, true, generateDate(2023, time.May, 28))
	assertHoliday(t, true, generateDate(2023, time.May, 29))
	assertHoliday(t, false, generateDate(2023, time.May, 30))

	// Fronleichnahm
	assertHoliday(t, false, generateDate(2023, time.June, 7))
	assertHoliday(t, true, generateDate(2023, time.June, 8))
	assertHoliday(t, false, generateDate(2023, time.June, 9))

	// Augsburger Friedensfest 8.8.
	assertHoliday(t, false, generateDate(2023, time.August, 7))
	assertHoliday(t, false, generateDate(2023, time.August, 8))
	assertHoliday(t, false, generateDate(2023, time.August, 9))

	// Mariä Himmelfahrt 15.8.
	assertHoliday(t, false, generateDate(2023, time.August, 14))
	assertHoliday(t, false, generateDate(2023, time.August, 15))
	assertHoliday(t, false, generateDate(2023, time.August, 16))

	// Weltkindertag 20.9.
	assertHoliday(t, false, generateDate(2023, time.September, 19))
	assertHoliday(t, false, generateDate(2023, time.September, 20))
	assertHoliday(t, false, generateDate(2023, time.September, 21))

	// Tag der dt. Einheit 3.10.
	assertHoliday(t, false, generateDate(2023, time.October, 2))
	assertHoliday(t, true, generateDate(2023, time.October, 3))
	assertHoliday(t, false, generateDate(2023, time.October, 4))

	// Reformationstag 31.10., Allerheiligen 1.11.
	assertHoliday(t, false, generateDate(2023, time.October, 30))
	assertHoliday(t, false, generateDate(2023, time.October, 31))
	assertHoliday(t, false, generateDate(2023, time.November, 1))
	assertHoliday(t, false, generateDate(2023, time.November, 2))

	// Buß- und Bettag, Mi zwischen 16.11. und 22.11.
	assertHoliday(t, false, generateDate(2023, time.November, 21))
	assertHoliday(t, false, generateDate(2023, time.November, 22))
	assertHoliday(t, false, generateDate(2023, time.November, 23))

	// Weihnachten 24.12.-26.12.
	assertHoliday(t, false, generateDate(2023, time.December, 23))
	assertHoliday(t, false, generateDate(2023, time.December, 24))
	assertHoliday(t, true, generateDate(2023, time.December, 25))
	assertHoliday(t, true, generateDate(2023, time.December, 26))
	assertHoliday(t, false, generateDate(2023, time.December, 27))

	// Silvester 31.12.
	assertHoliday(t, false, generateDate(2023, time.December, 30))
	assertHoliday(t, false, generateDate(2023, time.December, 31))
}

/**
 * Test method for {@link HolidayUtil#calcEasterSunday(int)}.
 */
func TestCalcEasterSunday(t *testing.T) {
	// https://www.dasinternet.net/ostern_1900-2099.php

	assertEqualsDate(t, generateDate(2015, time.April, 5), calcEasterSunday(2015))
	assertEqualsDate(t, generateDate(2016, time.March, 27), calcEasterSunday(2016))
	assertEqualsDate(t, generateDate(2017, time.April, 16), calcEasterSunday(2017))
	assertEqualsDate(t, generateDate(2018, time.April, 1), calcEasterSunday(2018))
	assertEqualsDate(t, generateDate(2019, time.April, 21), calcEasterSunday(2019))
	assertEqualsDate(t, generateDate(2020, time.April, 12), calcEasterSunday(2020))
	assertEqualsDate(t, generateDate(2021, time.April, 4), calcEasterSunday(2021))
	assertEqualsDate(t, generateDate(2022, time.April, 17), calcEasterSunday(2022))
	assertEqualsDate(t, generateDate(2023, time.April, 9), calcEasterSunday(2023))
	assertEqualsDate(t, generateDate(2024, time.March, 31), calcEasterSunday(2024))
	assertEqualsDate(t, generateDate(2025, time.April, 20), calcEasterSunday(2025))
	assertEqualsDate(t, generateDate(2026, time.April, 5), calcEasterSunday(2026))
	assertEqualsDate(t, generateDate(2027, time.March, 28), calcEasterSunday(2027))
	assertEqualsDate(t, generateDate(2028, time.April, 16), calcEasterSunday(2028))
	assertEqualsDate(t, generateDate(2029, time.April, 1), calcEasterSunday(2029))
	assertEqualsDate(t, generateDate(2030, time.April, 21), calcEasterSunday(2030))
	assertEqualsDate(t, generateDate(2031, time.April, 13), calcEasterSunday(2031))
	assertEqualsDate(t, generateDate(2032, time.March, 28), calcEasterSunday(2032))
	assertEqualsDate(t, generateDate(2033, time.April, 17), calcEasterSunday(2033))
	assertEqualsDate(t, generateDate(2034, time.April, 9), calcEasterSunday(2034))
	assertEqualsDate(t, generateDate(2035, time.March, 25), calcEasterSunday(2035))
	assertEqualsDate(t, generateDate(2036, time.April, 13), calcEasterSunday(2036))
	assertEqualsDate(t, generateDate(2037, time.April, 5), calcEasterSunday(2037))
	assertEqualsDate(t, generateDate(2038, time.April, 25), calcEasterSunday(2038))
	assertEqualsDate(t, generateDate(2039, time.April, 10), calcEasterSunday(2039))
	assertEqualsDate(t, generateDate(2040, time.April, 1), calcEasterSunday(2040))
}

/**
 * Test method for {@link HolidayUtil#calcPenanceDay(int)}.
 */
func TestCalcPenanceDay(t *testing.T) {
	// https://www.dasinternet.net/buss_und_bettag_2000-2300.php

	assertEqualsDate(t, generateDate(2015, time.November, 18), calcPenanceDay(2015))
	assertEqualsDate(t, generateDate(2016, time.November, 16), calcPenanceDay(2016))
	assertEqualsDate(t, generateDate(2017, time.November, 22), calcPenanceDay(2017))
	assertEqualsDate(t, generateDate(2018, time.November, 21), calcPenanceDay(2018))
	assertEqualsDate(t, generateDate(2019, time.November, 20), calcPenanceDay(2019))
	assertEqualsDate(t, generateDate(2020, time.November, 18), calcPenanceDay(2020))
	assertEqualsDate(t, generateDate(2021, time.November, 17), calcPenanceDay(2021))
	assertEqualsDate(t, generateDate(2022, time.November, 16), calcPenanceDay(2022))
	assertEqualsDate(t, generateDate(2023, time.November, 22), calcPenanceDay(2023))
	assertEqualsDate(t, generateDate(2024, time.November, 20), calcPenanceDay(2024))
	assertEqualsDate(t, generateDate(2025, time.November, 19), calcPenanceDay(2025))
	assertEqualsDate(t, generateDate(2026, time.November, 18), calcPenanceDay(2026))
	assertEqualsDate(t, generateDate(2027, time.November, 17), calcPenanceDay(2027))
	assertEqualsDate(t, generateDate(2028, time.November, 22), calcPenanceDay(2028))
	assertEqualsDate(t, generateDate(2029, time.November, 21), calcPenanceDay(2029))
	assertEqualsDate(t, generateDate(2030, time.November, 20), calcPenanceDay(2030))
	assertEqualsDate(t, generateDate(2031, time.November, 19), calcPenanceDay(2031))
	assertEqualsDate(t, generateDate(2032, time.November, 17), calcPenanceDay(2032))
	assertEqualsDate(t, generateDate(2033, time.November, 16), calcPenanceDay(2033))
	assertEqualsDate(t, generateDate(2034, time.November, 22), calcPenanceDay(2034))
	assertEqualsDate(t, generateDate(2035, time.November, 21), calcPenanceDay(2035))
	assertEqualsDate(t, generateDate(2036, time.November, 19), calcPenanceDay(2036))
	assertEqualsDate(t, generateDate(2037, time.November, 18), calcPenanceDay(2037))
	assertEqualsDate(t, generateDate(2038, time.November, 17), calcPenanceDay(2038))
	assertEqualsDate(t, generateDate(2039, time.November, 16), calcPenanceDay(2039))
	assertEqualsDate(t, generateDate(2040, time.November, 21), calcPenanceDay(2040))
}

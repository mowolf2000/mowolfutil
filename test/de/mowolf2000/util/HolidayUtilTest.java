/**
 *
 */
package de.mowolf2000.util;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import de.mowolf2000.util.HolidayUtil.Bundesland;

public class HolidayUtilTest {

    /**
     * Test method for {@link HolidayUtil#isHoliday(java.util.Calendar, Bundesland)}.
     */
    @Test
    public void testIsHolidayLocalDate() {
        // Neujahr 1.1
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JANUARY, 1), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JANUARY, 2), Bundesland.HESSEN));

        // Hl. 3 Könige 6.1.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JANUARY, 5), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JANUARY, 6), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JANUARY, 7), Bundesland.HESSEN));

        // Internationaler Frauentag 8.3.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MARCH, 7), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MARCH, 8), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MARCH, 9), Bundesland.HESSEN));

        // Gründonnerstag, Karfreitag, Karsamstag, Ostersonntag, Ostermontag, Osterdienstag
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 6), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 7), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 8), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 9), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 10), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 11), Bundesland.HESSEN));

        // Tag der Arbeit 1.5.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.APRIL, 30), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 1), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 2), Bundesland.HESSEN));

        // Christi Himmelfahrt
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 17), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 18), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 19), Bundesland.HESSEN));

        // Pfingsten
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 27), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 28), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 29), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.MAY, 30), Bundesland.HESSEN));

        // Fronleichnahm
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JUNE, 7), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JUNE, 8), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.JUNE, 9), Bundesland.HESSEN));

        // Augsburger Friedensfest 8.8.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 7), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 8), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 9), Bundesland.HESSEN));

        // Mariä Himmelfahrt 15.8.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 14), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 15), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.AUGUST, 16), Bundesland.HESSEN));

        // Weltkindertag 20.9.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.SEPTEMBER, 19), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.SEPTEMBER, 20), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.SEPTEMBER, 21), Bundesland.HESSEN));

        // Tag der dt. Einheit 3.10.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.OCTOBER, 2), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.OCTOBER, 3), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.OCTOBER, 4), Bundesland.HESSEN));

        // Reformationstag 31.10., Allerheiligen 1.11.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.OCTOBER, 30), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.OCTOBER, 31), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.NOVEMBER, 1), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.NOVEMBER, 2), Bundesland.HESSEN));

        // Buß- und Bettag, Mi zwischen 16.11. und 22.11.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.NOVEMBER, 21), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.NOVEMBER, 22), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.NOVEMBER, 23), Bundesland.HESSEN));

        // Weihnachten 24.12.-26.12.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 23), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 24), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 25), Bundesland.HESSEN));
        assertTrue(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 26), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 27), Bundesland.HESSEN));

        // Silvester 31.12.
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 30), Bundesland.HESSEN));
        assertFalse(HolidayUtil.isHoliday(LocalDate.of(2023, Month.DECEMBER, 31), Bundesland.HESSEN));
    }

    /**
     * Test method for {@link HolidayUtil#calcEasterSunday(int)}.
     */
    @Test
    public void testCalcEasterSunday() {
        // https://www.dasinternet.net/ostern_1900-2099.php

        assertEquals(LocalDate.of(2015, Month.APRIL, 5), HolidayUtil.calcEasterSunday(2015));
        assertEquals(LocalDate.of(2016, Month.MARCH, 27), HolidayUtil.calcEasterSunday(2016));
        assertEquals(LocalDate.of(2017, Month.APRIL, 16), HolidayUtil.calcEasterSunday(2017));
        assertEquals(LocalDate.of(2018, Month.APRIL, 1), HolidayUtil.calcEasterSunday(2018));
        assertEquals(LocalDate.of(2019, Month.APRIL, 21), HolidayUtil.calcEasterSunday(2019));
        assertEquals(LocalDate.of(2020, Month.APRIL, 12), HolidayUtil.calcEasterSunday(2020));
        assertEquals(LocalDate.of(2021, Month.APRIL, 4), HolidayUtil.calcEasterSunday(2021));
        assertEquals(LocalDate.of(2022, Month.APRIL, 17), HolidayUtil.calcEasterSunday(2022));
        assertEquals(LocalDate.of(2023, Month.APRIL, 9), HolidayUtil.calcEasterSunday(2023));
        assertEquals(LocalDate.of(2024, Month.MARCH, 31), HolidayUtil.calcEasterSunday(2024));
        assertEquals(LocalDate.of(2025, Month.APRIL, 20), HolidayUtil.calcEasterSunday(2025));
        assertEquals(LocalDate.of(2026, Month.APRIL, 5), HolidayUtil.calcEasterSunday(2026));
        assertEquals(LocalDate.of(2027, Month.MARCH, 28), HolidayUtil.calcEasterSunday(2027));
        assertEquals(LocalDate.of(2028, Month.APRIL, 16), HolidayUtil.calcEasterSunday(2028));
        assertEquals(LocalDate.of(2029, Month.APRIL, 1), HolidayUtil.calcEasterSunday(2029));
        assertEquals(LocalDate.of(2030, Month.APRIL, 21), HolidayUtil.calcEasterSunday(2030));
        assertEquals(LocalDate.of(2031, Month.APRIL, 13), HolidayUtil.calcEasterSunday(2031));
        assertEquals(LocalDate.of(2032, Month.MARCH, 28), HolidayUtil.calcEasterSunday(2032));
        assertEquals(LocalDate.of(2033, Month.APRIL, 17), HolidayUtil.calcEasterSunday(2033));
        assertEquals(LocalDate.of(2034, Month.APRIL, 9), HolidayUtil.calcEasterSunday(2034));
        assertEquals(LocalDate.of(2035, Month.MARCH, 25), HolidayUtil.calcEasterSunday(2035));
        assertEquals(LocalDate.of(2036, Month.APRIL, 13), HolidayUtil.calcEasterSunday(2036));
        assertEquals(LocalDate.of(2037, Month.APRIL, 5), HolidayUtil.calcEasterSunday(2037));
        assertEquals(LocalDate.of(2038, Month.APRIL, 25), HolidayUtil.calcEasterSunday(2038));
        assertEquals(LocalDate.of(2039, Month.APRIL, 10), HolidayUtil.calcEasterSunday(2039));
        assertEquals(LocalDate.of(2040, Month.APRIL, 1), HolidayUtil.calcEasterSunday(2040));
    }

    /**
     * Test method for {@link HolidayUtil#calcPenanceDay(int)}.
     */
    @Test
    public void testCalcPenanceDay() {
        // https://www.dasinternet.net/buss_und_bettag_2000-2300.php

        assertEquals(LocalDate.of(2015, Month.NOVEMBER, 18), HolidayUtil.calcPenanceDay(2015));
        assertEquals(LocalDate.of(2016, Month.NOVEMBER, 16), HolidayUtil.calcPenanceDay(2016));
        assertEquals(LocalDate.of(2017, Month.NOVEMBER, 22), HolidayUtil.calcPenanceDay(2017));
        assertEquals(LocalDate.of(2018, Month.NOVEMBER, 21), HolidayUtil.calcPenanceDay(2018));
        assertEquals(LocalDate.of(2019, Month.NOVEMBER, 20), HolidayUtil.calcPenanceDay(2019));
        assertEquals(LocalDate.of(2020, Month.NOVEMBER, 18), HolidayUtil.calcPenanceDay(2020));
        assertEquals(LocalDate.of(2021, Month.NOVEMBER, 17), HolidayUtil.calcPenanceDay(2021));
        assertEquals(LocalDate.of(2022, Month.NOVEMBER, 16), HolidayUtil.calcPenanceDay(2022));
        assertEquals(LocalDate.of(2023, Month.NOVEMBER, 22), HolidayUtil.calcPenanceDay(2023));
        assertEquals(LocalDate.of(2024, Month.NOVEMBER, 20), HolidayUtil.calcPenanceDay(2024));
        assertEquals(LocalDate.of(2025, Month.NOVEMBER, 19), HolidayUtil.calcPenanceDay(2025));
        assertEquals(LocalDate.of(2026, Month.NOVEMBER, 18), HolidayUtil.calcPenanceDay(2026));
        assertEquals(LocalDate.of(2027, Month.NOVEMBER, 17), HolidayUtil.calcPenanceDay(2027));
        assertEquals(LocalDate.of(2028, Month.NOVEMBER, 22), HolidayUtil.calcPenanceDay(2028));
        assertEquals(LocalDate.of(2029, Month.NOVEMBER, 21), HolidayUtil.calcPenanceDay(2029));
        assertEquals(LocalDate.of(2030, Month.NOVEMBER, 20), HolidayUtil.calcPenanceDay(2030));
        assertEquals(LocalDate.of(2031, Month.NOVEMBER, 19), HolidayUtil.calcPenanceDay(2031));
        assertEquals(LocalDate.of(2032, Month.NOVEMBER, 17), HolidayUtil.calcPenanceDay(2032));
        assertEquals(LocalDate.of(2033, Month.NOVEMBER, 16), HolidayUtil.calcPenanceDay(2033));
        assertEquals(LocalDate.of(2034, Month.NOVEMBER, 22), HolidayUtil.calcPenanceDay(2034));
        assertEquals(LocalDate.of(2035, Month.NOVEMBER, 21), HolidayUtil.calcPenanceDay(2035));
        assertEquals(LocalDate.of(2036, Month.NOVEMBER, 19), HolidayUtil.calcPenanceDay(2036));
        assertEquals(LocalDate.of(2037, Month.NOVEMBER, 18), HolidayUtil.calcPenanceDay(2037));
        assertEquals(LocalDate.of(2038, Month.NOVEMBER, 17), HolidayUtil.calcPenanceDay(2038));
        assertEquals(LocalDate.of(2039, Month.NOVEMBER, 16), HolidayUtil.calcPenanceDay(2039));
        assertEquals(LocalDate.of(2040, Month.NOVEMBER, 21), HolidayUtil.calcPenanceDay(2040));
    }

    /**
     * Test method for {@link HolidayUtil#isHoliday(java.util.Calendar, Bundesland)}.
     */
    @Test
    public void testIsHolidayCalendar() {
        Calendar cal = new GregorianCalendar(2023, Calendar.APRIL, 9, 14, 33, 58);
        assertTrue(HolidayUtil.isHoliday(cal, Bundesland.HESSEN));

        cal = new GregorianCalendar(2023, Calendar.APRIL, 8, 14, 33, 58);
        assertFalse(HolidayUtil.isHoliday(cal, Bundesland.HESSEN));
    }

    /**
     * Test method for {@link HolidayUtil#isHoliday(java.time.LocalDateTime, Bundesland)}.
     */
    @Test
    public void testIsHolidayLocalDateTime() {
        LocalDateTime ts = LocalDateTime.of(2023, Month.APRIL, 9, 14, 33, 58);
        assertTrue(HolidayUtil.isHoliday(ts, Bundesland.HESSEN));

        ts = LocalDateTime.of(2023, Month.APRIL, 8, 14, 33, 58);
        assertFalse(HolidayUtil.isHoliday(ts, Bundesland.HESSEN));
    }

    /**
     * Test method for {@link HolidayUtil#isHoliday(java.time.Instant, Bundesland)}.
     */
    @Test
    public void testIsHolidayInstant() {
        Calendar cal = new GregorianCalendar(2023, Calendar.APRIL, 9, 14, 33, 58);
        Instant instant = cal.toInstant();
        assertTrue(HolidayUtil.isHoliday(instant, Bundesland.HESSEN));

        cal = new GregorianCalendar(2023, Calendar.APRIL, 8, 14, 33, 58);
        instant = cal.toInstant();
        assertFalse(HolidayUtil.isHoliday(instant, Bundesland.HESSEN));
    }

    /**
     * Test method for {@link HolidayUtil#isHoliday(java.util.Date, Bundesland)}.
     */
    @Test
    public void testIsHolidayUtilDate() {
        Calendar cal = new GregorianCalendar(2023, Calendar.APRIL, 9, 14, 33, 58);
        java.util.Date udate = cal.getTime();
        assertTrue(HolidayUtil.isHoliday(udate, Bundesland.HESSEN));

        cal = new GregorianCalendar(2023, Calendar.APRIL, 8, 14, 33, 58);
        udate = cal.getTime();
        assertFalse(HolidayUtil.isHoliday(udate, Bundesland.HESSEN));
    }

    /**
     * Test method for {@link HolidayUtil#holidays(int, Bundesland)}.
     */
    @Test
    public void testHolidays() {
        LocalDate friedensfest = LocalDate.of(2023, Month.AUGUST, 8);

        Set<LocalDate> holidays = HolidayUtil.holidays(2023, Bundesland.BAYERN_AUGSBURG);
        assertTrue(holidays.contains(friedensfest));

        holidays = HolidayUtil.holidays(2023, Bundesland.BAYERN_KATH);
        assertFalse(holidays.contains(friedensfest));
    }
}

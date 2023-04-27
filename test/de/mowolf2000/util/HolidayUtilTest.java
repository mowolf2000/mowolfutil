/**
 *
 */
package de.mowolf2000.util;

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

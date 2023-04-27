/**
 *
 */
package de.mowolf2000.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author 131265724
 *
 */
public class GermanHolidayTest {

    /**
     * Test method for {@link com.ibm.erp.testfactory.vau.GermanHoliday.Ostersonntag.{...}#calcDate(int)}.
     */
    @Test
    public void testOstersonntagCalcDate() {
        // https://www.dasinternet.net/ostern_1900-2099.php

        assertEquals(LocalDate.of(2015, Month.APRIL, 5), GermanHoliday.Ostersonntag.calcDate(2015));
        assertEquals(LocalDate.of(2016, Month.MARCH, 27), GermanHoliday.Ostersonntag.calcDate(2016));
        assertEquals(LocalDate.of(2017, Month.APRIL, 16), GermanHoliday.Ostersonntag.calcDate(2017));
        assertEquals(LocalDate.of(2018, Month.APRIL, 1), GermanHoliday.Ostersonntag.calcDate(2018));
        assertEquals(LocalDate.of(2019, Month.APRIL, 21), GermanHoliday.Ostersonntag.calcDate(2019));
        assertEquals(LocalDate.of(2020, Month.APRIL, 12), GermanHoliday.Ostersonntag.calcDate(2020));
        assertEquals(LocalDate.of(2021, Month.APRIL, 4), GermanHoliday.Ostersonntag.calcDate(2021));
        assertEquals(LocalDate.of(2022, Month.APRIL, 17), GermanHoliday.Ostersonntag.calcDate(2022));
        assertEquals(LocalDate.of(2023, Month.APRIL, 9), GermanHoliday.Ostersonntag.calcDate(2023));
        assertEquals(LocalDate.of(2024, Month.MARCH, 31), GermanHoliday.Ostersonntag.calcDate(2024));
        assertEquals(LocalDate.of(2025, Month.APRIL, 20), GermanHoliday.Ostersonntag.calcDate(2025));
        assertEquals(LocalDate.of(2026, Month.APRIL, 5), GermanHoliday.Ostersonntag.calcDate(2026));
        assertEquals(LocalDate.of(2027, Month.MARCH, 28), GermanHoliday.Ostersonntag.calcDate(2027));
        assertEquals(LocalDate.of(2028, Month.APRIL, 16), GermanHoliday.Ostersonntag.calcDate(2028));
        assertEquals(LocalDate.of(2029, Month.APRIL, 1), GermanHoliday.Ostersonntag.calcDate(2029));
        assertEquals(LocalDate.of(2030, Month.APRIL, 21), GermanHoliday.Ostersonntag.calcDate(2030));
        assertEquals(LocalDate.of(2031, Month.APRIL, 13), GermanHoliday.Ostersonntag.calcDate(2031));
        assertEquals(LocalDate.of(2032, Month.MARCH, 28), GermanHoliday.Ostersonntag.calcDate(2032));
        assertEquals(LocalDate.of(2033, Month.APRIL, 17), GermanHoliday.Ostersonntag.calcDate(2033));
        assertEquals(LocalDate.of(2034, Month.APRIL, 9), GermanHoliday.Ostersonntag.calcDate(2034));
        assertEquals(LocalDate.of(2035, Month.MARCH, 25), GermanHoliday.Ostersonntag.calcDate(2035));
        assertEquals(LocalDate.of(2036, Month.APRIL, 13), GermanHoliday.Ostersonntag.calcDate(2036));
        assertEquals(LocalDate.of(2037, Month.APRIL, 5), GermanHoliday.Ostersonntag.calcDate(2037));
        assertEquals(LocalDate.of(2038, Month.APRIL, 25), GermanHoliday.Ostersonntag.calcDate(2038));
        assertEquals(LocalDate.of(2039, Month.APRIL, 10), GermanHoliday.Ostersonntag.calcDate(2039));
        assertEquals(LocalDate.of(2040, Month.APRIL, 1), GermanHoliday.Ostersonntag.calcDate(2040));
    }

    /**
     * Test method for {@link com.ibm.erp.testfactory.vau.GermanHoliday.BussUndBettag.{...}#calcDate(int)}.
     */
    @Test
    public void testBussUndBettagCalcDate() {
        // https://www.dasinternet.net/buss_und_bettag_2000-2300.php

        assertEquals(LocalDate.of(2015, Month.NOVEMBER, 18), GermanHoliday.BussUndBettag.calcDate(2015));
        assertEquals(LocalDate.of(2016, Month.NOVEMBER, 16), GermanHoliday.BussUndBettag.calcDate(2016));
        assertEquals(LocalDate.of(2017, Month.NOVEMBER, 22), GermanHoliday.BussUndBettag.calcDate(2017));
        assertEquals(LocalDate.of(2018, Month.NOVEMBER, 21), GermanHoliday.BussUndBettag.calcDate(2018));
        assertEquals(LocalDate.of(2019, Month.NOVEMBER, 20), GermanHoliday.BussUndBettag.calcDate(2019));
        assertEquals(LocalDate.of(2020, Month.NOVEMBER, 18), GermanHoliday.BussUndBettag.calcDate(2020));
        assertEquals(LocalDate.of(2021, Month.NOVEMBER, 17), GermanHoliday.BussUndBettag.calcDate(2021));
        assertEquals(LocalDate.of(2022, Month.NOVEMBER, 16), GermanHoliday.BussUndBettag.calcDate(2022));
        assertEquals(LocalDate.of(2023, Month.NOVEMBER, 22), GermanHoliday.BussUndBettag.calcDate(2023));
        assertEquals(LocalDate.of(2024, Month.NOVEMBER, 20), GermanHoliday.BussUndBettag.calcDate(2024));
        assertEquals(LocalDate.of(2025, Month.NOVEMBER, 19), GermanHoliday.BussUndBettag.calcDate(2025));
        assertEquals(LocalDate.of(2026, Month.NOVEMBER, 18), GermanHoliday.BussUndBettag.calcDate(2026));
        assertEquals(LocalDate.of(2027, Month.NOVEMBER, 17), GermanHoliday.BussUndBettag.calcDate(2027));
        assertEquals(LocalDate.of(2028, Month.NOVEMBER, 22), GermanHoliday.BussUndBettag.calcDate(2028));
        assertEquals(LocalDate.of(2029, Month.NOVEMBER, 21), GermanHoliday.BussUndBettag.calcDate(2029));
        assertEquals(LocalDate.of(2030, Month.NOVEMBER, 20), GermanHoliday.BussUndBettag.calcDate(2030));
        assertEquals(LocalDate.of(2031, Month.NOVEMBER, 19), GermanHoliday.BussUndBettag.calcDate(2031));
        assertEquals(LocalDate.of(2032, Month.NOVEMBER, 17), GermanHoliday.BussUndBettag.calcDate(2032));
        assertEquals(LocalDate.of(2033, Month.NOVEMBER, 16), GermanHoliday.BussUndBettag.calcDate(2033));
        assertEquals(LocalDate.of(2034, Month.NOVEMBER, 22), GermanHoliday.BussUndBettag.calcDate(2034));
        assertEquals(LocalDate.of(2035, Month.NOVEMBER, 21), GermanHoliday.BussUndBettag.calcDate(2035));
        assertEquals(LocalDate.of(2036, Month.NOVEMBER, 19), GermanHoliday.BussUndBettag.calcDate(2036));
        assertEquals(LocalDate.of(2037, Month.NOVEMBER, 18), GermanHoliday.BussUndBettag.calcDate(2037));
        assertEquals(LocalDate.of(2038, Month.NOVEMBER, 17), GermanHoliday.BussUndBettag.calcDate(2038));
        assertEquals(LocalDate.of(2039, Month.NOVEMBER, 16), GermanHoliday.BussUndBettag.calcDate(2039));
        assertEquals(LocalDate.of(2040, Month.NOVEMBER, 21), GermanHoliday.BussUndBettag.calcDate(2040));
    }
}

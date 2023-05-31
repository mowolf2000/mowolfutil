package de.mowolf2000.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * Liefert die Liste aller Feiertage in dem Jahr zu dem angegebenen Bundesland.
     *
     * @param year betrachtets Jahr
     * @param bl betrachtets Bundesland
     * @return Set mit allen Feiertagen in dem Jahr für dieses Bundesland
     */
    private static Set<LocalDate> calcHolidaysOfYear(int year, Bundesland bl) {
        Set<GermanHoliday> holidays = bl.calcHolidays(year);
        Set<LocalDate> dates = new HashSet<>();
        for (GermanHoliday holiday : holidays) {
            dates.add(holiday.calcDate(year));
        }

        return Collections.unmodifiableSet(dates);
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

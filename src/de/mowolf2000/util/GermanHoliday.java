package de.mowolf2000.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public enum GermanHoliday {
    // https://www.dgb.de/gesetzliche-feiertage-deutschland
    Neujahr {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.JANUARY, 1);
        }
    },

    Hl3Koenige {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.JANUARY, 6);
        }
    },

    Rosenmontag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).minusDays(48);
        }
    },

    Aschermittwoch {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).minusDays(46);
        }
    },

    InternationalerFrauentag {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.MARCH, 8);
        }
    },

    Gruendonnerstag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).minusDays(3);
        }
    },

    Karfreitag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).minusDays(2);
        }
    },

    Ostersonntag {
        /**
         * Berechnet den Ostersonntag für ein Jahr.
         *
         * @param jahr betrachtetes Jahr
         * @return berechneter Ostersonntag für das Jahr
         * @see "https://de.wikibooks.org/wiki/Algorithmensammlung:_Kalender:_Feiertage"
         */
        @Override
        public LocalDate calcDate(int year) {
            // k die Säkularzahl
            // m die säkulare Mondschaltung
            // s die säkulare Sonnenschaltung
            // a der Mondparameter
            // d der Keim für den ersten Vollmond im Frühling
            // r die kalendarische Korrekturgröße
            // og die Ostergrenze
            // sz der ersten Sonntag im März
            // oe die Entfernung des Ostersonntags von der Ostergrenze
            // os das Datum des Ostersonntags als Märzdatum (32.März = 1.April)

            int k = year / 100;
            int m = 15 + (3 * k + 3) / 4 - (8 * k + 13) / 25;
            int s = 2 - (3 * k + 3) / 4;
            int a = year % 19;
            int d = (19 * a + m) % 30;
            int r = (d + a / 11) / 29;
            int og = 21 + d - r;
            int sz = 7 - (year + year / 4 + s) % 7;
            int oe = 7 - (og - sz) % 7;
            int os = og + oe;

            int osterMonat = 2 + (os + 30) / 31;
            int osterTag = os - 31 * (osterMonat / 4);

            return LocalDate.of(year, osterMonat, osterTag);
        }
    },

    Ostermontag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).plusDays(1);
        }
    },

    TagDerArbeit {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.MAY, 1);
        }
    },

    ChristiHimmelfahrt {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).plusDays(39);
        }
    },

    Pfingstsonntag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).plusDays(49);
        }
    },

    Pfingstmontag {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).plusDays(50);
        }
    },

    Fronleichnam {
        @Override
        public LocalDate calcDate(int year) {
            return Ostersonntag.calcDate(year).plusDays(60);
        }
    },

    Friedensfest {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.AUGUST, 8);
        }
    },

    MariaeHimmelfahrt {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.AUGUST, 15);
        }
    },

    Weltkindertag {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.SEPTEMBER, 20);
        }
    },

    TagDerDeutschenEinheit {
        @Override
        public LocalDate calcDate(int year) {
            if (year <= 1990) {
                return LocalDate.of(year, Month.JUNE, 17);
            }
            return LocalDate.of(year, Month.OCTOBER, 3);
        }
    },

    Reformationstag {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.OCTOBER, 31);
        }
    },

    Allerheiligen {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.NOVEMBER, 1);
        }
    },

    BussUndBettag {
        @Override
        public LocalDate calcDate(int year) {
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
    },

    HlAbend {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.DECEMBER, 24);
        }
    },

    Weihnachten1 {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.DECEMBER, 25);
        }
    },

    Weihnachten2 {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.DECEMBER, 26);
        }
    },

    Silvester {
        @Override
        public LocalDate calcDate(int year) {
            return LocalDate.of(year, Month.DECEMBER, 31);
        }
    };

    public abstract LocalDate calcDate(int year);
}

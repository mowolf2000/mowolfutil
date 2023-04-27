package de.mowolf2000.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    BADEN_WUERTEMBERG {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Hl3Koenige);
            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BAYERN_KATH {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Hl3Koenige);
            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.MariaeHimmelfahrt);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BAYERN_PROT {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Hl3Koenige);
            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BAYERN_AUGSBURG {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Hl3Koenige);
            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Friedensfest);
            holidays.add(GermanHoliday.MariaeHimmelfahrt);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BERLIN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.InternationalerFrauentag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BRANDENBURG {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    BREMEN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    HAMBURG {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    HESSEN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);

            return Collections.unmodifiableSet(holidays);
        }
    },
    MECKLENBURG_VORPOMMERN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.InternationalerFrauentag);
            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    NIEDERSACHSEN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    NORDRHEIN_WESTFALEN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    RHEINLAND_PFALZ {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    SAARLAND {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.MariaeHimmelfahrt);
            holidays.add(GermanHoliday.Allerheiligen);

            return Collections.unmodifiableSet(holidays);
        }
    },
    SACHSEN_ANHALT {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Hl3Koenige);
            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    SACHSEN_PROT {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);
            holidays.add(GermanHoliday.BussUndBettag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    SACHSEN_KATH {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Reformationstag);
            holidays.add(GermanHoliday.BussUndBettag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    SCHLESWIG_HOLSTEIN {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    THUERINGEN_PROT {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Weltkindertag);
            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    },
    THUERINGEN_KATH {
        @Override
        public Set<GermanHoliday> calcHolidays() {
            Set<GermanHoliday> holidays = globalHolidays();

            holidays.add(GermanHoliday.Fronleichnam);
            holidays.add(GermanHoliday.Weltkindertag);
            holidays.add(GermanHoliday.Reformationstag);

            return Collections.unmodifiableSet(holidays);
        }
    };

    public abstract Set<GermanHoliday> calcHolidays();

    static Set<GermanHoliday> globalHolidays() {
        // https://www.dgb.de/gesetzliche-feiertage-deutschland

        Set<GermanHoliday> holidays = new HashSet<>();

        holidays.add(GermanHoliday.Neujahr);
        holidays.add(GermanHoliday.Karfreitag);
        holidays.add(GermanHoliday.Ostersonntag);
        holidays.add(GermanHoliday.Ostermontag);
        holidays.add(GermanHoliday.TagDerArbeit);
        holidays.add(GermanHoliday.ChristiHimmelfahrt);
        holidays.add(GermanHoliday.Pfingstsonntag);
        holidays.add(GermanHoliday.Pfingstmontag);
        holidays.add(GermanHoliday.TagDerDeutschenEinheit);
        holidays.add(GermanHoliday.Weihnachten1);
        holidays.add(GermanHoliday.Weihnachten2);

        return holidays;
    }
}

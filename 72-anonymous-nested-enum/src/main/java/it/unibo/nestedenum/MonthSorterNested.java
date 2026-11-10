package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    /**
     * Month
     */
    public enum Month {
        GENNAIO("Gennaio"),
        FEBBRAIO("Febbraio"),
        MARZO("Marzo"),
        APRILE("Aprile"),
        MAGGIO("Maggio"),
        GIUGNO("Giugno"),
        LUGLIO("Luglio"),
        AGOSTO("Agosto"),
        SETTEMBRE("Settembre"),
        OTTOBRE("Ottobre"),
        NOVEMBRE("Novembre"),
        DICEMBRE("Dicembre");

        private final String actualName;

        private Month(final String actualName) {
            this.actualName = actualName;
        }

        public static Month fromString(String name) {
            for (Month b : Month.values()) {
                if (b.actualName.equalsIgnoreCase(name)) {
                    return b;
                }
            }
            return null;
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        return null;
    }

    @Override
    public Comparator<String> sortByOrder() {
        return null;
    }
}

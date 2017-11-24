import java.text.DecimalFormat;

/**
 * Created by Andrei Tolkachev on 20.06.2017.
 */

class Converter {
    private static final long HOUR = 60;
    private static final long DAY = HOUR * 24;
    private static final long MONTH = DAY * 30;
    private static final long YEAR = MONTH * 12;
    private static final String FORMATTED_DOUBLE = "#0.0";
    private static long num;

    static void setNum(long num) {
        Converter.num = num;
    }

    public static long getNum() {
        return num;
    }

    /**
     * Numbers inputs
     * @param field
     * @return
     */
    static double inputString(javafx.scene.control.TextField field) {
        num = Long.decode(field.getText().trim());
        return num;
    }

    /**
     * Converts minutes to hours
     * @return
     */
    static String toHours() {
        double hour = (double) num / HOUR;
        String formattedDouble = new DecimalFormat(FORMATTED_DOUBLE).format(hour);
        if (formattedDouble.length() > 8) {
            formattedDouble = "infinity";
        }
        return formattedDouble;
    }

    /**
     * Converts minutes to days
     * @return
     */
     static String toDays() {
        double days = (double) num / DAY;
        String formattedDouble = new DecimalFormat(FORMATTED_DOUBLE).format(days);
        if (formattedDouble.length() > 8) {
            formattedDouble = "infinity";
        }
        return formattedDouble;
    }

    /**
     * Converts minutes to months
     * @return
     */
     static String toMonths() {
        double months = (double) num / MONTH;
        String formattedDouble = new DecimalFormat(FORMATTED_DOUBLE).format(months);
        if (formattedDouble.length() > 8) {
            formattedDouble = "infinity";
        }
        return formattedDouble;
    }

    /**
     * Converts minutes to years
     * @return
     */
     static String toYears() {
        double years = (double) num / YEAR;
        String formattedDouble = new DecimalFormat(FORMATTED_DOUBLE).format(years);
        if (formattedDouble.length() > 8) {
            formattedDouble = "infinity";
        }
        return formattedDouble;
    }
}

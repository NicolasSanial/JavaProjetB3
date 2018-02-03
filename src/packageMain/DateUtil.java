package packageMain;

import packageModels.Pdf;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Properties;

public class DateUtil {

    // Define the date pattern
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    // The date formatter
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    /**
     * Returns the given date as a well formatted String. The above defined
     * {@link DateUtil#DATE_PATTERN} is used.
     *
     * @param date the date to be returned as a string
     * @return formatted string
     */
    public static String format(LocalDate date) {

        if (date == null) {

            return null;
        }

        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String in the format of the defined {@link DateUtil#DATE_PATTERN}
     * to a {@link LocalDate} object.
     *
     * Returns null if the String could not be converted.
     *
     * @param dateString the date as String
     * @return the date object or null if it could not be converted
     */
    public static LocalDate parse(String dateString) {

        try {

            return DATE_FORMATTER.parse(dateString, LocalDate::from);

        } catch (DateTimeParseException e) {

            return null;
        }
    }

    public static Date asDate(LocalDate localDate) {

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return date;
    }

    /**
     * Checks the String whether it is a valid date.
     *
     * @param dateString
     * @return true if the String is a valid date
     */
    public static boolean validDate(String dateString) {

        // Try to parse the String.
        return DateUtil.parse(dateString) != null;
    }

}

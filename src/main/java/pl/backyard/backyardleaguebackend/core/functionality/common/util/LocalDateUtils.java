package pl.backyard.backyardleaguebackend.core.functionality.common.util;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalDateUtils {

    public static final DateTimeFormatter formatter_YYYY_MM_DD = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter formatter_DD_MM_YYYY_HH_MM_SS = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final String YYYY_MM_DD_REGEXP = "\\d{4}-\\d{2}-\\d{2}";

    public static boolean isValidDate(String regexp, String value) {
        var pattern = Pattern.compile(regexp);

        return !Strings.isNullOrEmpty(value) && pattern.matcher(value).matches();
    }

    public static LocalDate parseToLocalDate(DateTimeFormatter formatter, String value) {
        return LocalDate.parse(value, formatter);
    }

    public static String parseToString(DateTimeFormatter formatter, LocalDateTime value) {
        return value.format(formatter);
    }

    public static String parseToString(DateTimeFormatter formatter, LocalDate value) {
        return value.format(formatter);
    }


}

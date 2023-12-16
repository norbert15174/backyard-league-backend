package pl.backyard.backyardleaguebackend.core.functionality.common.util;

import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {

    public static boolean anyStringNullOrEmpty(String... args) {
        for (var arg : args) {
            if (Strings.isNullOrEmpty(arg)) {
                return true;
            }
        }
        return false;
    }

}

package pl.backyard.backyardleaguebackend.security.helper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.backyard.backyardleaguebackend.security.properties.AccountProperties;

@AllArgsConstructor
@Component
public class PasswordHelper {

    private final AccountProperties accountProperties;
    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String password) {
        return passwordEncoder.encode(addSaltAndPepper(password));
    }

    public boolean matchPassword(String passwordToCheck, String encodedPassword) {
        return passwordEncoder.matches(addSaltAndPepper(passwordToCheck), encodedPassword);
    }

    private String addSaltAndPepper(String password) {
        return accountProperties.getSalt() + password + accountProperties.getPepper();
    }

}

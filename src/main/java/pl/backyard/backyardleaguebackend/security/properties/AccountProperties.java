package pl.backyard.backyardleaguebackend.security.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "account")
public class AccountProperties {

    private String salt;
    private String pepper;

}

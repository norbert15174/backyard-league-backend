package pl.backyard.backyardleaguebackend.api.functionality.user.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

}

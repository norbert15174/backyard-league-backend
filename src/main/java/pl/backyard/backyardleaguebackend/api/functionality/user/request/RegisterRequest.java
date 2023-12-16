package pl.backyard.backyardleaguebackend.api.functionality.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckByRegexp;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckUniqueEmail;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.CheckUniqueName;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.common.annotation.NotNullOrEmpty;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.annotation.CheckPassword;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.annotation.CheckUsername;
import pl.backyard.backyardleaguebackend.core.functionality.common.validator.user.sequence.First;
import pl.backyard.backyardleaguebackend.core.functionality.user.service.crud.UserQueryService;

import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest {

    @NotNullOrEmpty(groups = First.class)
    @CheckUsername
    @CheckUniqueName(queryService = UserQueryService.class)
    private String username;
    @NotNullOrEmpty(groups = First.class)
    @Size(max = 50)
    @CheckPassword
    private String password;
    @Email
    @NotNullOrEmpty(groups = First.class)
    @CheckUniqueEmail(queryService = UserQueryService.class)
    private String email;

    @CheckByRegexp(regexp = "^(?=.{2,40}$)[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]+(?: [A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]+)?$", message = "annotation.nameNotMatch")
    @NotNullOrEmpty(groups = First.class)
    private String name;
    @CheckByRegexp(regexp = "^(?=.{2,40}$)[A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]+(?: [A-ZĄĆĘŁŃÓŚŹŻa-ząćęłńóśźż]+)?$", message = "annotation.lastnameNotMatch")
    @NotNullOrEmpty(groups = First.class)
    private String lastname;
    private Set<String> roles;

}

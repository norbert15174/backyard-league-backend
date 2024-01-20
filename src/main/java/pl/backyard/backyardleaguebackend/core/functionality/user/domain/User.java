package pl.backyard.backyardleaguebackend.core.functionality.user.domain;

import jakarta.persistence.*;
import lombok.*;
import pl.backyard.backyardleaguebackend.core.functionality.common.domain.EntityId;
import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;
import pl.backyard.backyardleaguebackend.core.functionality.team.domain.UserTeam;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User implements EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private String email;
    private LocalDate createdAt = LocalDate.now();
    private boolean enabled;

    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "user")
    private Set<UserTeam> teams;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void addRole(Role role) {
        this.getRoles().add(role);
        role.getUsers().add(this);
    }

    public String getFullName() {
        return String.format("%s %s", getName(), getLastname());
    }
}

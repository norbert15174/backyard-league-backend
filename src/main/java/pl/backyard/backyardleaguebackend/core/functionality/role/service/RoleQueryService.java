package pl.backyard.backyardleaguebackend.core.functionality.role.service;


import pl.backyard.backyardleaguebackend.core.functionality.role.domain.Role;

import java.util.Collection;
import java.util.Set;

public interface RoleQueryService {
    Set<Role> getAllByNames(Collection<String> names);
}

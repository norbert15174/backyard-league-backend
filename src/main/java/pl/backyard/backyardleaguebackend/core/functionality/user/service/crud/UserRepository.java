package pl.backyard.backyardleaguebackend.core.functionality.user.service.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.backyard.backyardleaguebackend.core.functionality.user.domain.User;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "left join fetch u.roles " +
            "where lower(u.username) = :username or lower(u.email) = :username")
    Optional<User> findByUsernameOrEmail(@Param("username") String username);

    @Query("select u from User u " +
            "left join fetch u.roles " +
            "where lower(u.email) = :email ")
    Optional<User> findByEmail(@Param("email") String email);
}

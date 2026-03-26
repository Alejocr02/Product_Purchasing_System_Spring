package co.edu.cesde.pps.repository;

import co.edu.cesde.pps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuario por email (case-insensitive)
    Optional<User> findByEmailIgnoreCase(String email);

    // Comprobar existencia por email (case-insensitive)
    boolean existsByEmailIgnoreCase(String email);

}
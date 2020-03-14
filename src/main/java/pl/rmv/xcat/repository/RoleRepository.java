package pl.rmv.xcat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rmv.xcat.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

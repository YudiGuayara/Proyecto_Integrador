package co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories;

import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {
}


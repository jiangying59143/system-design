package example.authorizationserver.repository;

import example.authorizationserver.domain.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<SysUserEntity, UUID> {
    SysUserEntity findSysUserEntityByUsername(String userName);
}

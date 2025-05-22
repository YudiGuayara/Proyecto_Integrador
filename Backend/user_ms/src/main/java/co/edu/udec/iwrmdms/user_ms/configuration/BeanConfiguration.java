package co.edu.udec.iwrmdms.user_ms.configuration;

import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.adapter.RolePostgresqlAdapter;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.adapter.UserPostgresqlAdapter;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.mappers.IProfileEntityMapper;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.mappers.IRoleEntityMapper;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.mappers.IUserEntityMapper;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IProfileRepository;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IRoleRepository;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IUserRepository;
import co.edu.udec.iwrmdms.user_ms.domains.api.IRoleServicePort;
import co.edu.udec.iwrmdms.user_ms.domains.api.IUserServicePort;
import co.edu.udec.iwrmdms.user_ms.domains.spi.IRolePersistencePort;
import co.edu.udec.iwrmdms.user_ms.domains.spi.IUserPersistencePort;
import co.edu.udec.iwrmdms.user_ms.domains.usecase.RoleUseCase;
import co.edu.udec.iwrmdms.user_ms.domains.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    private final IProfileRepository profileRepository;
    private final IProfileEntityMapper profileEntityMapper;
 
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
 

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPostgresqlAdapter(userRepository, profileRepository, roleRepository, userEntityMapper, passwordEncoder());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RolePostgresqlAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }
}

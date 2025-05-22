package co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.adapter;

import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.entity.UserEntity;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.exceptions.*;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.mappers.IUserEntityMapper;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IProfileRepository;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IRoleRepository;
import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.repositories.IUserRepository;
import co.edu.udec.iwrmdms.user_ms.domains.model.User;
import co.edu.udec.iwrmdms.user_ms.domains.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
import static co.edu.udec.iwrmdms.user_ms.configuration.Constants.*;

import java.util.List;
 

import java.util.List;

import static co.edu.udec.iwrmdms.user_ms.configuration.Constants.*;

>>>>>>> 1c3f0d3 (Resuelto conflicto)
@RequiredArgsConstructor
@Transactional
public class UserPostgresqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IProfileRepository profileRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
<<<<<<< HEAD

    @Override
    public void saveUser(User user) {
        // Permitir solo los roles de Administrador, Inspector y Asistente
        if (!(user.getRole().getRoleId().equals(ADMINISTRATOR_ROLE_ID) || 
              user.getRole().getRoleId().equals(INSPECTOR_ROLE_ID) || 
              user.getRole().getRoleId().equals(ASSISTANT_ROLE_ID))) {
            throw new RoleNotAllowedForCreationException();
        }

        // Verificar si el usuario ya existe
        if(userRepository.findByProfileEntityIdAndRoleEntityId(user.getProfile().getProfileId(), user.getRole().getRoleId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Verificar que el perfil y el rol existen
        profileRepository.findById(user.getProfile().getProfileId()).orElseThrow(ProfileNotFoundException::new);
        roleRepository.findById(user.getRole().getRoleId()).orElseThrow(RoleNotFoundException::new);
        
        // Cifrar la contraseña del usuario antes de guardar
 
 
    @Override
    public void saveUser(User user) {
        if (userRepository.findByProfile_ProfileIdAndRole_RoleId(user.getProfile().getProfileId(), user.getRole().getRoleId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        profileRepository.findById(user.getProfile().getProfileId()).orElseThrow(ProfileNotFoundException::new);
        roleRepository.findById(user.getRole().getRoleId()).orElseThrow(RoleNotFoundException::new);
 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(User user) { 
        if(userRepository.findByProfileEntityIdAndRoleEntityId(user.getProfile().getProfileId(), user.getRole().getRoleId()).isPresent()) {
            userRepository.deleteByProfileEntityIdAndRoleEntityId(user.getProfile().getProfileId(), user.getRole().getRoleId());
 
        if (userRepository.findByProfile_ProfileIdAndRole_RoleId(user.getProfile().getProfileId(), user.getRole().getRoleId()).isPresent()) {
            userRepository.deleteByProfile_ProfileIdAndRole_RoleId(user.getProfile().getProfileId(), user.getRole().getRoleId());

        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<User> getAllAdministrators(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(ADMINISTRATOR_ROLE_ID, pagination);

        List<UserEntity> userEntityList = userRepository.findAllByRole_RoleId(ADMINISTRATOR_ROLE_ID, pagination);

        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public List<User> getAllInspectors(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(INSPECTOR_ROLE_ID, pagination);

        List<UserEntity> userEntityList = userRepository.findAllByRole_RoleId(INSPECTOR_ROLE_ID, pagination);

        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public List<User> getAllAssistants(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);

        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(ASSISTANT_ROLE_ID, pagination);
 
        List<UserEntity> userEntityList = userRepository.findAllByRole_RoleId(ASSISTANT_ROLE_ID, pagination);
 
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getAdministrator(Long id) {
 
        UserEntity userEntity = userRepository.findByProfileEntityIdAndRoleEntityId(id, ADMINISTRATOR_ROLE_ID)
 
        UserEntity userEntity = userRepository.findByProfile_ProfileIdAndRole_RoleId(id, ADMINISTRATOR_ROLE_ID)
 
            .orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getInspector(Long id) {
 
        UserEntity userEntity = userRepository.findByProfileEntityIdAndRoleEntityId(id, INSPECTOR_ROLE_ID)
 
        UserEntity userEntity = userRepository.findByProfile_ProfileIdAndRole_RoleId(id, INSPECTOR_ROLE_ID)
 
            .orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getAssistant(Long id) {
 
        UserEntity userEntity = userRepository.findByProfileEntityIdAndRoleEntityId(id, ASSISTANT_ROLE_ID)
 
        UserEntity userEntity = userRepository.findByProfile_ProfileIdAndRole_RoleId(id, ASSISTANT_ROLE_ID)
             .orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }
}

package co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.mappers;

import co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.entity.UserEntity;
import co.edu.udec.iwrmdms.user_ms.domains.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
<<<<<<< HEAD

import java.util.List; 
import jakarta.persistence.*;


import java.util.List;

>>>>>>> 1c3f0d3 (Resuelto conflicto)
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper { 
    @Mapping(target = "profile.profileId", source = "profile.profileId")
    @Mapping(target = "role.roleId", source = "role.roleId")
    UserEntity toEntity(User user);
    @Mapping(target = "profile.profileId", source = "profile.profileId")
    @Mapping(target = "role.roleId", source = "role.roleId")
    User toUser(UserEntity userEntity);
    List<User> toUserList(List<UserEntity> userEntityList);
}  
    @Mapping(target = "profile.profileId", source = "profile.profileId") // Cambiado de profileEntity a profile
    @Mapping(target = "role.roleId", source = "role.roleId") // Cambiado de roleEntity a role
    UserEntity toEntity(User user);

    @Mapping(target = "profile.profileId", source = "profile.profileId") // Cambiado de profileEntity a profile
    @Mapping(target = "role.roleId", source = "role.roleId") // Cambiado de roleEntity a role
    User toUser(UserEntity userEntity);

    List<User> toUserList(List<UserEntity> userEntityList);
} 

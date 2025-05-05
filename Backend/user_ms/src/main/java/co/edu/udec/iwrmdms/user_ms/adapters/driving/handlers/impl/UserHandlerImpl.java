package co.edu.udec.iwrmdms.user_ms.adapters.driving.handlers.impl;

import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.request.UserRequestDto;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.response.ProfileResponseDto;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.handlers.IUserHandler;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.mappers.IProfileResponseMapper;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.mappers.IUserRequestMapper;
import co.edu.udec.iwrmdms.user_ms.domains.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IProfileResponseMapper profileResponseMapper;
    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void deleteUser(UserRequestDto userRequestDto) {
        userServicePort.deleteUser(userRequestMapper.toUser(userRequestDto));
    }

    
    // Métodos para obtener usuarios de diferentes roles
    @Override
    public List<ProfileResponseDto> getAdministrators(Integer page) {
        return profileResponseMapper.userListToProfileResponseList(userServicePort.getAllAdministrators(page));
    }
    
    @Override
    public List<ProfileResponseDto> getInspectors(Integer page) {
        return profileResponseMapper.userListToProfileResponseList(userServicePort.getAllInspectors(page));
    }
 

    @Override
    public List<ProfileResponseDto> getAssistants(Integer page) {
        return profileResponseMapper.userListToProfileResponseList(userServicePort.getAllAssistants(page));
    }
 
    // Métodos para obtener perfiles por ID
    @Override
    public ProfileResponseDto getAdministrator(Long id) {
        return profileResponseMapper.userToProfileResponse(userServicePort.getAdministrator(id));
    }

    @Override
    public ProfileResponseDto getInspector(Long id) {
        return profileResponseMapper.userToProfileResponse(userServicePort.getInspector(id));
    }

    

    @Override
    public ProfileResponseDto getAssistant(Long id) {
        return profileResponseMapper.userToProfileResponse(userServicePort.getAssistant(id));
    }

     
}

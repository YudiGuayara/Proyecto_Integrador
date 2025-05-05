 
package co.edu.udec.iwrmdms.user_ms.adapters.driving.handlers;

import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.request.UserRequestDto;
import co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.response.ProfileResponseDto;

import java.util.List;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    void deleteUser(UserRequestDto userRequestDto);

    List<ProfileResponseDto> getAdministrators(Integer page);
    List<ProfileResponseDto> getInspectors(Integer page);
    List<ProfileResponseDto> getAssistants(Integer page);
    
    ProfileResponseDto getAdministrator(Long id);
    ProfileResponseDto getInspector(Long id);
    ProfileResponseDto getAssistant(Long id);
}

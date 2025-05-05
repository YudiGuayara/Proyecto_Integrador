package co.edu.udec.iwrmdms.user_ms.adapters.driving.dto.request;

import co.edu.udec.iwrmdms.user_ms.domains.model.Profile;
import co.edu.udec.iwrmdms.user_ms.domains.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private Long idProfile;
    private Long idRole;
}

package co.edu.udec.iwrmdms.user_ms.domains.api;

import co.edu.udec.iwrmdms.user_ms.domains.model.Profile;

public interface IProfileServicePort {
    void saveProfile(Profile profile);
    void updateProfile(Long idProfile, Profile profile);

}

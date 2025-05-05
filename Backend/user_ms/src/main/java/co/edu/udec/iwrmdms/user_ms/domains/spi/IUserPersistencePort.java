package co.edu.udec.iwrmdms.user_ms.domains.spi;

import co.edu.udec.iwrmdms.user_ms.domains.model.User;

import java.util.List;

public interface IUserPersistencePort {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllAdministrators(int page);
    List<User> getAllInspectors(int page);
    List<User> getAllAssistants(int page);
    User getAdministrator(Long id);
    User getInspector(Long id);
    User getAssistant(Long id);
}

package co.edu.udec.iwrmdms.user_ms.domains.api;

import co.edu.udec.iwrmdms.user_ms.domains.model.User;

import java.util.List;

public interface IUserServicePort {
    void saveUser(User user);
    void deleteUser(User user);
    List<User> getAllInspectors(int page);
    List<User> getAllAdministrators(int page); 
    List<User> getAllAssistants(int page); 
    User getInspector(Long id);
    User getAdministrator(Long id);
    User getAssistant(Long id); 
}

package co.edu.udec.iwrmdms.user_ms.domains.usecase;

import co.edu.udec.iwrmdms.user_ms.domains.api.IUserServicePort;
import co.edu.udec.iwrmdms.user_ms.domains.model.User;
import co.edu.udec.iwrmdms.user_ms.domains.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userPersistencePort.deleteUser(user);
    }

    @Override
    public List<User> getAllAdministrators(int page) {
        return userPersistencePort.getAllAdministrators(page);
    }

    @Override
    public List<User> getAllInspectors(int page) {
        return userPersistencePort.getAllInspectors(page);
    }

    @Override
    public List<User> getAllAssistants(int page) {
        return userPersistencePort.getAllAssistants(page);
    }

    @Override
    public User getAdministrator(Long id) {
        return userPersistencePort.getAdministrator(id);
    }

    @Override
    public User getInspector(Long id) {
        return userPersistencePort.getInspector(id);
    }

    @Override
    public User getAssistant(Long id) {
        return userPersistencePort.getAssistant(id);
    }
}

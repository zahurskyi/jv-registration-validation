package core.basesyntax.service;

import core.basesyntax.RegistrationException;
import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_LOGIN_LENGTH = 6;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_AGE = 120;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null) {
            throw new RegistrationException("Login can`t be null!");
        }
        if (user.getLogin().length() < MIN_LOGIN_LENGTH) {
            throw new RegistrationException("Login is too short");
        }
        if (user.getPassword() == null) {
            throw new RegistrationException("Password can`t be null!");
        }
        if (user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Password is too short");
        }
        if (user.getAge() == null) {
            throw new RegistrationException("Age can`t be null!");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("Age must be greater than 18!");
        }
        if (user.getAge() > MAX_AGE) {
            throw new RegistrationException("Incorrect age!");
        }
        if ((storageDao.get(user.getLogin()) != null)) {
            throw new RegistrationException("User with current login is already registered");
        }
        return storageDao.add(user);
    }
}

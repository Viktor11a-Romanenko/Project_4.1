package repository;

import model.User;

public interface UserRepository {
    User addUser(String email, String name);

    boolean isEmailExists(String email);

    User getUserByEmail(String email);


}

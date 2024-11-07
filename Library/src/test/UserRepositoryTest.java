package test;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private UserRepositoryImpl userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    public void testAddUser_NewEmail() {


        String email = "testEmail@gmail.com";
        String password = "password";
        User user = userRepository.addUser(email, password);

        assertNotNull(user);
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());

        assertEquals(email, userRepository.getUserByEmail(email).getEmail()); // Check user list size
    }


    @Test
    public void testIsEmailExists_ExistingEmail() {

        String existingEmail = "1";
        boolean exists = userRepository.isEmailExists(existingEmail);
        assertTrue(exists);
    }

    @Test
    public void testIsEmailExists_NonexistentEmail() {
        String nonExistentEmail = "newEmail@yandex.ru";
        boolean exists = userRepository.isEmailExists(nonExistentEmail);
        assertFalse(exists);
    }

    @Test
    public void testGetUserByEmail_ExistingEmail() {
        String existingEmail = "1";
        User user = userRepository.getUserByEmail(existingEmail);
        assertNotNull(user);
        assertEquals(existingEmail, user.getEmail());
    }

    @Test
    public void testGetUserByEmail_NonexistentEmail() {
        String nonExistentEmail = "newEmail@yandex.ru";
        User user = userRepository.getUserByEmail(nonExistentEmail);
        assertNull(user);
    }
}
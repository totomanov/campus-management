package nl.tudelft.oopp.group31.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import nl.tudelft.oopp.group31.entities.User;
import nl.tudelft.oopp.group31.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;
    private User changedUser;
    private User emptyUser;
    private String userAuthBase64;

    /**
     * Sets up the Users used in multiple tests.
     */
    @BeforeEach
    public void setup() {
        user = new User("maikdevries", "Hagelslag", 1, false);
        userAuthBase64 = "000000bWFpa2RldnJpZXM6SGFnZWxzbGFn";
        changedUser = new User("maikdevries", "Ponies", 2, true);
        emptyUser = new User();
    }

    @Test
    public void testAddUser() {
        when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<String> responseEntity = userController.addNewUser(user);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(user.toString(), responseEntity.getBody());
    }

    @Test
    public void testLoginSuccess() {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByNetID(user.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.login(userAuthBase64);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testLoginFail() {
        // Change Base64 encoded value which now translates to "maiodevries"
        String userAuthBase64Augmented = userAuthBase64.replaceFirst("a", "b");

        Optional<User> emptyUser = Optional.empty();
        when(userRepository.findByNetID("maiodevries")).thenReturn(emptyUser);
        ResponseEntity<String> responseEntity = userController.login(userAuthBase64Augmented);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetAllUsersEmpty() {
        ArrayList<User> userIterable = new ArrayList<>();
        Iterable<User> response = userController.getAllUsers();

        assertEquals(response, userIterable);
    }

    @Test
    public void testChangeUserFail() {
        Optional<User> optionalEmptyUser = Optional.empty();
        when(userRepository.findByNetID(emptyUser.getNetID())).thenReturn(optionalEmptyUser);
        ResponseEntity<String> responseEntity = userController.changeDetails(emptyUser);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testChangeUserSuccess() {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByNetID(user.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.changeDetails(changedUser);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedUser.toString(), responseEntity.getBody());
    }

    @Test
    public void testDeleteUserFail() {
        Optional<User> optionalEmptyUser = Optional.empty();
        when(userRepository.findByNetID(emptyUser.getNetID())).thenReturn(optionalEmptyUser);
        ResponseEntity<String> responseEntity = userController.deleteUser(emptyUser.getNetID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testDeleteUserSuccess() {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByNetID(user.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.deleteUser(user.getNetID());

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetUserFail() {
        Optional<User> optionalEmptyUser = Optional.empty();
        when(userRepository.findByNetID(emptyUser.getNetID())).thenReturn(optionalEmptyUser);
        ResponseEntity<String> responseEntity = userController.getUser(emptyUser.getNetID());

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetUserSuccess() {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByNetID(user.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.getUser(user.getNetID());

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(user.toString(), responseEntity.getBody());
    }

    @Test
    public void testRentBikeFailUser() {
        Optional<User> optionalEmptyUser = Optional.empty();
        when(userRepository.findByNetID(emptyUser.getNetID())).thenReturn(optionalEmptyUser);
        ResponseEntity<String> responseEntity = userController.rentBike(emptyUser.getNetID(), true);

        assertEquals(404, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testRentBikeFailBike() {
        Optional<User> optionalUser = Optional.of(changedUser);
        when(userRepository.findByNetID(changedUser.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.rentBike(changedUser.getNetID(), true);

        assertEquals(406, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testRentBikeSuccess() {
        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByNetID(user.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.rentBike(user.getNetID(), true);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(user.toString(), responseEntity.getBody());
        assertTrue(user.getRentedBike());
    }

    @Test
    public void testReturnBikeSuccess() {
        Optional<User> optionalUser = Optional.of(changedUser);
        when(userRepository.findByNetID(changedUser.getNetID())).thenReturn(optionalUser);
        ResponseEntity<String> responseEntity = userController.rentBike(changedUser.getNetID(), false);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(changedUser.toString(), responseEntity.getBody());
        assertFalse(changedUser.getRentedBike());
    }

}

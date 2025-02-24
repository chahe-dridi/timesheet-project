import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    @Mock
    private UserRepositoryMock userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testAddUser() {
        User user = new User("John Doe", "john.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
    }

    @Test
    void testUpdateUser() {
        User user = new User("Jane Doe", "jane.doe@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals("Jane Doe", updatedUser.getName());
    }

    @Test
    void testRetrieveUser() {
        User user = new User("Alice", "alice@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.retrieveUser(1L);
        assertNotNull(retrievedUser);
        assertEquals("Alice", retrievedUser.getName());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        assertDoesNotThrow(() -> userService.deleteUser(1L));
    }
}

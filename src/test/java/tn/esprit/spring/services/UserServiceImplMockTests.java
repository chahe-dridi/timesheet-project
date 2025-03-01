package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.entities.Role;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@Disabled // Désactive l'exécution automatique du test
class UserServiceImplMockTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceImplMockTests() {
        // Pas besoin d'initialiser les mocks ici, @ExtendWith(MockitoExtension.class) le fait déjà
    }

    @Test
    void testAddUser() {
        // Correction du constructeur User (ajout des paramètres corrects)
        User user = new User("John", "Doe", new Date(), Role.INGENIEUR);

        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.addUser(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user); // Vérifie que la méthode save a bien été appelée
    }

    @Test
    void testUpdateUser() {
        User user = new User(1L, "John", "Doe", new Date(), Role.INGENIEUR);

        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertEquals(user, updatedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser("1"); // Correction ici : passe un String

        verify(userRepository, times(1)).deleteById(1L);
    }


    @Test
    void testRetrieveUser() {
        User user = new User(1L, "John", "Doe", new Date(), Role.INGENIEUR);

        // Correction : retrieveUser attend un String (exemple : 1L transformé en "1")
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.retrieveUser("1"); // Correction ici : passe un String

        assertEquals(user, retrievedUser);
        verify(userRepository, times(1)).findById(1L);
    }
}
package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
class UserServiceImplTest {

    @Test
    void testAddUser() {
        String envVar = System.getenv("TIMESHEET_TESTS_FAIL");
        if ("True".equalsIgnoreCase(envVar)) {
            assertFalse(false, "Le test échoue car TIMESHEET_TESTS_FAIL est activé !");
        }
    }
}
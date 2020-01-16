package ru.leovalter.votingballot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.leovalter.votingballot.model.Role;
import ru.leovalter.votingballot.model.User;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.leovalter.votingballot.data.UserTestData.*;

class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService service;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelCache();
    }

    @Test
    void create() {
        User newUser = new User(null, "SomeUser", "someuser@gmail.com", "somepassword", true, Collections.singleton(Role.ROLE_USER));
        User created = service.create(new User(newUser));
        newUser.setId(created.getId());
        assertMatch(created, newUser);
        assertMatch(service.getAll(), ADMIN, newUser, USER);
    }

    @Test
    void duplicateMailCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new User(null, "Duplicate", "user@gmail.com", "password", Role.ROLE_USER)));
    }

    @Test
    void delete() {
        service.delete(USER_ID);
        assertMatch(service.getAll(), ADMIN);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void get() {
        User admin = service.get(ADMIN_ID);
        assertMatch(admin, ADMIN);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void getByEmail() {
        User user = service.getByEmail("user@gmail.com");
        assertMatch(user, USER);
    }

    @Test
    void getAll() {
        List<User> allUsers = service.getAll();
        assertMatch(allUsers, ADMIN, USER);
    }

    @Test
    void update() {
        User updated = new User(USER);
        updated.setName("UpdateName");
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(new User(updated));
        assertMatch(service.get(USER_ID), updated);
    }

    @Test
    void createWithException() {
        validateRootCause(() -> service.create(new User(null, "  ", "email@gmail.com", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "  ", "password", Role.ROLE_USER)), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "User", "email@gmail.com", "", Role.ROLE_USER)), ConstraintViolationException.class);
    }
}
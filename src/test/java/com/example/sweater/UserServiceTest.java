package com.example.sweater;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;
import com.example.sweater.service.SmtpMailSender;
import com.example.sweater.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    // Моки, обертки
    private UserRepo userRepo = mock(UserRepo.class);
    private SmtpMailSender mailService = mock(SmtpMailSender.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    // Тестируемый класс (все зависимости, которые spring внедряед через @Autowired я прописал через конструктор тестироемого класса,
    // но можно из устанавливать, так же через: сеттеры или использовать Reflection)
    private UserService userService = new UserService(userRepo, mailService, passwordEncoder);

    @Test
    public void addUserTest() {
        User user = new User();
        user.setEmail("email@email.email");

        boolean isUserCreated = userService.addUser(user);

        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(mailService, Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        //ArgumentMatchers.anyString()
                        ArgumentMatchers.eq("Activation code"),
                        ArgumentMatchers.contains("Welcome to Sweater."));

    }

    @Test
    public void addUserFailedTest() {
        User user = new User();
        user.setUsername("John");
        user.setEmail("email@email.email");

        Mockito.doReturn(new User())
                .when(userRepo)
                .findByUsername("John");

        boolean isUserCreated = userService.addUser(user);

        assertFalse(isUserCreated);

        Mockito.verify(userRepo, Mockito.never()).save(ArgumentMatchers.any(User.class));
        Mockito.verify(mailService, Mockito.never())
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());
    }

    @Test
    public void activateUserTest() {
        User user = new User();
        user.setActivationCode("activate");

        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        assertTrue(isUserActivated);
        assertNull(user.getActivationCode());
        Mockito.verify(userRepo, Mockito.times(1)).save(user);

    }

    @Test
    public void activateUserFailTest() {
        boolean isUserActivated = userService.activateUser("activate");

        assertFalse(isUserActivated);
        Mockito.verify(userRepo, Mockito.never()).save(any(User.class));

    }
}
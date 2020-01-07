package ru.leovalter.votingballot;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.leovalter.votingballot.model.Role;
import ru.leovalter.votingballot.model.User;
import ru.leovalter.votingballot.model.Vote;
import ru.leovalter.votingballot.repository.UserRepository;
import ru.leovalter.votingballot.service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.format("Hello, Voting Ballot");

        try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

            System.out.println("Bean Difinition Names: " + Arrays.toString(context.getBeanDefinitionNames()));

            UserRepository repository = context.getBean(UserRepository.class);

            User user = new User();
            user.setName("Leo");
            user.setEmail("leo@gmail.com");
            user.setPassword("123321");
            user.setRegistered(LocalDateTime.now());
            user.setRoles(Collections.singleton(Role.ROLE_USER));

            System.out.println("User --- " + repository.save(user));

        }
    }
}

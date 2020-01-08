package ru.leovalter.votingballot;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.leovalter.votingballot.model.Role;
import ru.leovalter.votingballot.model.User;
import ru.leovalter.votingballot.repository.DishRepository;
import ru.leovalter.votingballot.repository.RestaurantRepository;
import ru.leovalter.votingballot.repository.UserRepository;
import ru.leovalter.votingballot.repository.VoteRepository;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) {
        System.out.format("Hello, Voting Ballot");

        try(ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml")) {

            System.out.println("Bean Difinition Names: " + Arrays.toString(context.getBeanDefinitionNames()));

            VoteRepository repository = context.getBean(VoteRepository.class);

//            User user = new User();
//            user.setName("Vasya");
//            user.setEmail("vas@gmail.com");
//            user.setPassword("123321");
//            user.setRegistered(Date.from(Instant.now()));
//            user.setRoles(EnumSet.of(Role.ROLE_USER));
//
//            System.out.println("User --- " + repository.save(user));

            System.out.println("Vote === " + repository.getAllByUserId(100000));

//            RestaurantRepository repository = context.getBean(RestaurantRepository.class);
//
//            System.out.println("Rest -- " + repository.getByName("Караван"));

        }
    }
}

package ru.leovalter.votingballot.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.leovalter.votingballot.model.User;
import ru.leovalter.votingballot.to.UserTo;
import ru.leovalter.votingballot.model.Role;

import java.util.List;
import java.util.stream.Collectors;

public class UserUtil {

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password) ? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static UserTo createNewFromUser(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static List<UserTo> createNewFromUser(List<User> userList) {
        return userList.stream().map(UserUtil::createNewFromUser).collect(Collectors.toList());
    }

    public static User createNewFromTO(UserTo userTo) {
        return new User(userTo.getId(), userTo.getName(), userTo.getEmail(), userTo.getPassword(), Role.ROLE_USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
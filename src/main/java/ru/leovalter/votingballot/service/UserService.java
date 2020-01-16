package ru.leovalter.votingballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.leovalter.votingballot.AuthorizedUser;
import ru.leovalter.votingballot.model.User;
import ru.leovalter.votingballot.repository.CrudUserRepository;
import ru.leovalter.votingballot.to.UserTo;
import ru.leovalter.votingballot.util.UserUtil;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.util.List;

import static ru.leovalter.votingballot.util.UserUtil.prepareToSave;
import static ru.leovalter.votingballot.util.ValidationUtil.checkNotFound;
import static ru.leovalter.votingballot.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {

    private final CrudUserRepository crudUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(CrudUserRepository crudUserRepository, PasswordEncoder passwordEncoder) {
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int userId) throws NotFoundException {
        checkNotFoundWithId(crudUserRepository.delete(userId) != 0, userId);
    }

    public User get(int userId) throws NotFoundException {
        return checkNotFoundWithId(crudUserRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Not found user with id = " + userId)), userId);
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(crudUserRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return crudUserRepository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        crudUserRepository.save(prepareToSave(user, passwordEncoder));
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(UserTo userTo) {
        Assert.notNull(userTo, "user must not be null");
        User user = get(userTo.getId());
        crudUserRepository.save(prepareToSave(UserUtil.updateFromTo(user, userTo), passwordEncoder));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = crudUserRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
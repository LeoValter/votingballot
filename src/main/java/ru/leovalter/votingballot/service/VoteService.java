package ru.leovalter.votingballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.leovalter.votingballot.model.Vote;
import ru.leovalter.votingballot.repository.CrudVoteRepository;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.leovalter.votingballot.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final CrudVoteRepository crudVoteRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public VoteService(CrudVoteRepository crudVoteRepository,
                       RestaurantService restaurantService, UserService userService) {
        this.crudVoteRepository = crudVoteRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    public void delete(int voteId) throws NotFoundException {
        checkNotFoundWithId(crudVoteRepository.delete(voteId) != 0, voteId);
    }

    public Vote createOrUpdate(Vote vote, int userId, int restaurantId) {
        vote.setRestaurant(restaurantService.get(restaurantId));
        vote.setUser(userService.get(userId));
        return crudVoteRepository.save(vote);
    }

    public Vote get(int voteId) {
        return checkNotFoundWithId(crudVoteRepository.findById(voteId).orElseThrow(() ->
                new NotFoundException("Not found vote with id = " + voteId)), voteId);
    }

    public Vote getTodayByUserId(int userId) {
        return crudVoteRepository.findByUserIdAndDate(userId, LocalDate.now());
    }

    public List<Vote> getAll() {
        return crudVoteRepository.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    public List<Vote> getTodayVotes() {
        return crudVoteRepository.getAllByDate(LocalDate.now());
    }
}
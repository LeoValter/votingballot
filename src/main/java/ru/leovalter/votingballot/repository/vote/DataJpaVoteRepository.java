package ru.leovalter.votingballot.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.leovalter.votingballot.model.Vote;
import ru.leovalter.votingballot.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private static final Sort SORT_BY_DATE = Sort.by(Sort.Direction.ASC, "date");

    @Autowired
    private final CrudVoteRepository crudRepository;

    @Autowired
    public DataJpaVoteRepository(CrudVoteRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAllByUserId(int userId) {
        return crudRepository.getAllByUserId(userId);
    }

    @Override
    public List<Vote> getAllByUserEmail(String email) {
        return crudRepository.getAllByUserEmail(email);
    }

    @Override
    public List<Vote> getAllByRestaurantId(int restaurantId) {
        return crudRepository.getAllByRestaurantId(restaurantId);
    }

    @Override
    public List<Vote> getAllByRestaurantName(String restaurantName) {
        return crudRepository.getAllByRestaurantName(restaurantName);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate localDate) {
        return crudRepository.getAllByDate(localDate);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll(SORT_BY_DATE);
    }
}

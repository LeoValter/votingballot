package ru.leovalter.votingballot.repository;

import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    // null if not found, when updated
    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    // null if not found
    List<Vote> getAllByUserId(int userId);

    // null if not found
    List<Vote> getAllByUserEmail(String email);

    // null if not found
    List<Vote> getAllByRestaurantId(int restaurantId);

    // null if not found
    List<Vote> getAllByRestaurantName(String restaurantName);

    // null if not found
    List<Vote> getAllByDate(LocalDate localDate);

    List<Vote> getAll();
}

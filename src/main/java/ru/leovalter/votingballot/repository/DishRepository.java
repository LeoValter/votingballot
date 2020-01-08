package ru.leovalter.votingballot.repository;

import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.model.Restaurant;
import ru.leovalter.votingballot.model.User;

import java.util.List;

public interface DishRepository {

    // null if not found, when updated
    Dish save(Dish dish);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    // null if not found
    List<Dish> getAllByRestaurantId(int restaurantId);

    List<Dish> getAll();
}

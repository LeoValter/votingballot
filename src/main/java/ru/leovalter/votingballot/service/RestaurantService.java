package ru.leovalter.votingballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.model.Restaurant;
import ru.leovalter.votingballot.repository.CrudDishRepository;
import ru.leovalter.votingballot.repository.CrudRestaurantRepository;
import ru.leovalter.votingballot.to.RestaurantTo;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.util.*;

import static ru.leovalter.votingballot.util.RestaurantUtil.createNewFromRestaurant;
import static ru.leovalter.votingballot.util.RestaurantUtil.updateNewFromTo;
import static ru.leovalter.votingballot.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }

    public List<RestaurantTo> getWithDailyDishes() throws NotFoundException {
        List<Dish> dishes = crudDishRepository.getDishesWithRestaurantToday();
        List<Restaurant> restaurantWithDailyDishes = new ArrayList<>();

        Map<Integer, List<Dish>> map = new HashMap<>();
        for (Dish dish : dishes) {
            Restaurant restaurant = dish.getRestaurant();
            int restaurantId = restaurant.getId();
            if (map.containsKey(restaurantId)) {
                List<Dish> dishList = map.get(restaurantId);
                dishList.add(dish);
                map.put(restaurantId, dishList);
            } else {
                List<Dish> newList = new ArrayList<>();
                newList.add(dish);
                map.put(restaurantId, newList);
            }
        }
        for (Dish dish : dishes) {
            Restaurant restaurant = dish.getRestaurant();
            if (!restaurantWithDailyDishes.contains(restaurant)) {
                restaurant.setDishes(Collections.emptyList());
                restaurant.setDishes(map.get(restaurant.getId()));
                restaurantWithDailyDishes.add(restaurant);
            }
        }
        return createNewFromRestaurant(restaurantWithDailyDishes);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.getAll();
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int restaurantId) throws NotFoundException {
        checkNotFoundWithId(crudRestaurantRepository.delete(restaurantId) != 0, restaurantId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Restaurant update(RestaurantTo restaurantTo, Restaurant restaurant) {
        return crudRestaurantRepository.save(updateNewFromTo(restaurant, restaurantTo));
    }

    public Restaurant get(int restaurant1Id) {
        return checkNotFoundWithId(crudRestaurantRepository.findById(restaurant1Id).orElse(null), restaurant1Id);
    }
}
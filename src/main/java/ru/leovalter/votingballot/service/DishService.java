package ru.leovalter.votingballot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.repository.CrudDishRepository;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.util.List;

import static ru.leovalter.votingballot.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final RestaurantService restaurantService;

    private final CrudDishRepository crudDishRepository;

    @Autowired
    public DishService(CrudDishRepository crudDishRepository, RestaurantService restaurantService) {
        this.crudDishRepository = crudDishRepository;
        this.restaurantService = restaurantService;
    }

    public Dish get(int dishId, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(crudDishRepository.getByIdAndRestaurantId(dishId, restaurantId), dishId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public void delete(int dishId, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(crudDishRepository.delete(dishId, restaurantId) != 0, dishId);
    }

    @Cacheable("dishes")
    public List<Dish> getAll(int restaurantId) {
        return crudDishRepository.getAllByRestaurantId(restaurantId);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    public Dish createOrUpdate(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurantService.get(restaurantId));
        return crudDishRepository.save(dish);
    }

    public List<Dish> getDishesWithRestaurantToday() {
        return crudDishRepository.getDishesWithRestaurantToday();
    }
}
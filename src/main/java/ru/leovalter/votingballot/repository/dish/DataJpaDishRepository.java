package ru.leovalter.votingballot.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.repository.DishRepository;

import java.util.List;

@Repository
public class DataJpaDishRepository implements DishRepository {

    @Autowired
    private final CrudDishRepository crudRepository;

    @Autowired
    public DataJpaDishRepository(CrudDishRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAllByRestaurantId(int restaurantId) {
        return crudRepository.getAllByRestaurantId(restaurantId);
    }

    @Override
    public List<Dish> getAll() {
        return crudRepository.getAll();
    }
}

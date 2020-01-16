package ru.leovalter.votingballot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.leovalter.votingballot.model.Dish;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.leovalter.votingballot.data.DishTestData.*;
import static ru.leovalter.votingballot.data.DishTestData.assertMatch;
import static ru.leovalter.votingballot.data.RestaurantTestData.*;

class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("dishes").clear();
        jpaUtil.clear2ndLevelCache();
    }

    @Test
    void get() {
        assertMatch(service.get(START_DISH_ID, RESTAURANT_ID_1), DISH_1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(1, 1));
    }

    @Test
    void delete() {
        service.delete(START_DISH_ID + 1, RESTAURANT_ID_1);
        assertMatch(service.getAll(RESTAURANT_ID_1), DISH_3, DISH_1);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1, 1));
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(RESTAURANT_ID_2), DISH_5, DISH_4);
    }

    @Test
    void create() {
        Dish dish = new Dish(null, "NewDish", 100, RESTAURANT_5, LocalDate.of(2020, 1, 16));
        Dish created = service.createOrUpdate(dish, RESTAURANT_ID_5);
        assertMatch(service.getAll(RESTAURANT_ID_5), created, DISH_12, DISH_11);
    }

    @Test
    void update() {
        Dish updated = new Dish(DISH_1);
        updated.setPrice(333);
        updated.setDate(LocalDate.of(2020, 1, 15));
        service.createOrUpdate(new Dish(updated), RESTAURANT_ID_1);
        assertMatch(service.get(START_DISH_ID, RESTAURANT_ID_1), updated);
    }
}
package ru.leovalter.votingballot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.leovalter.votingballot.model.Restaurant;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.leovalter.votingballot.data.RestaurantTestData.*;

class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @BeforeEach
    void setUp() {
        cacheManager.getCache("dishes").clear();
        jpaUtil.clear2ndLevelCache();
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), RESTAURANT_4, RESTAURANT_5, RESTAURANT_3, RESTAURANT_2, RESTAURANT_1);
    }

    @Test
    void delete() {
        service.delete(RESTAURANT_ID_2);
        service.delete(RESTAURANT_ID_4);
        assertMatch(service.getAll(), RESTAURANT_5, RESTAURANT_3, RESTAURANT_1);
    }

    @Test
    void deleteNodFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void create() {
        Restaurant newRestaurant = new Restaurant("NewRestaurant");
        Restaurant created = service.create(new Restaurant(newRestaurant));
        newRestaurant.setId(created.getId());
        assertMatch(created, newRestaurant);
        assertMatch(service.getAll(), RESTAURANT_4, newRestaurant, RESTAURANT_5, RESTAURANT_3, RESTAURANT_2, RESTAURANT_1);
    }

    @Test
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant("Шарманка")));
    }

    @Test
    void update() {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("UpdatedName");
        service.update(new Restaurant(updated));
        assertMatch(service.get(RESTAURANT_ID_1), updated);
    }

    @Test
    void get() {
        Restaurant restaurant = service.get(RESTAURANT_ID_5);
        assertMatch(restaurant, RESTAURANT_5);
    }

    @Test
    void getNodFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }
}
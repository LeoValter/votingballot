package ru.leovalter.votingballot.web.rest.admin;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.leovalter.votingballot.model.Restaurant;
import ru.leovalter.votingballot.service.RestaurantService;
import ru.leovalter.votingballot.to.RestaurantTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.leovalter.votingballot.util.RestaurantUtil.createNewFromRestaurant;
import static ru.leovalter.votingballot.util.RestaurantUtil.createNewFromTo;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestaurantController {
    private static final Logger log = getLogger(AdminRestaurantController.class);

    static final String REST_URL = "/admin/restaurants";

    private final RestaurantService restaurantService;

    @Autowired
    public AdminRestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return createNewFromRestaurant(restaurantService.getAll());
    }

    @GetMapping(value = "/{id}")
    public RestaurantTo get(@PathVariable("id") int id) {
        log.info("get restaurant by id {}", id);
        return createNewFromRestaurant(restaurantService.get(id));
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int restaurantId) {
        log.info("delete restaurant {}", restaurantId);
        restaurantService.delete(restaurantId);
    }

    @PutMapping("/{restaurantId}")
    public void update(@PathVariable int restaurantId,
                       @Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("update restaurant {}", restaurantId);
        restaurantService.update(restaurantTo, restaurantService.get(restaurantId));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody RestaurantTo restaurantTo) {
        log.info("create restaurant {}", restaurantTo.getName());
        Restaurant created = restaurantService.create(createNewFromTo(restaurantTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping("/today")
    public List<RestaurantTo> getWithDailyDishes() {
        log.info("get restaurant with dishes");
        return restaurantService.getWithDailyDishes();
    }
}
package ru.leovalter.votingballot.data;

import ru.leovalter.votingballot.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static final int RESTAURANT_ID_1 = 100002;
    public static final int RESTAURANT_ID_2 = 100003;
    public static final int RESTAURANT_ID_3 = 100004;
    public static final int RESTAURANT_ID_4 = 100005;
    public static final int RESTAURANT_ID_5 = 100006;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Шарманка");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_2, "Тарас Бульба");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID_3, "Караван");
    public static final Restaurant RESTAURANT_4 = new Restaurant(RESTAURANT_ID_4, "8 SECONDS PUB");
    public static final Restaurant RESTAURANT_5 = new Restaurant(RESTAURANT_ID_5, "Ёлки Палки");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "votes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "votes").isEqualTo(expected);
    }
}

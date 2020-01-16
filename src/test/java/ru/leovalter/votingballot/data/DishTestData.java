package ru.leovalter.votingballot.data;

import ru.leovalter.votingballot.model.Dish;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.leovalter.votingballot.data.RestaurantTestData.*;

public class DishTestData {

    public static final int START_DISH_ID = 100007;
    public static final Dish DISH_1 = new Dish(START_DISH_ID, "Шашлык из Каре Ягненка", 450, RESTAURANT_1, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_2 = new Dish(START_DISH_ID + 1, "Домашний суп", 200, RESTAURANT_1, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_3 = new Dish(START_DISH_ID + 2, "Узбекский плов", 330, RESTAURANT_1, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_4 = new Dish(START_DISH_ID + 3, "Фирменный борщ Корчма", 310, RESTAURANT_2, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_5 = new Dish(START_DISH_ID + 4, "Вареники Староукраинские", 310, RESTAURANT_2, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_6 = new Dish(START_DISH_ID + 5, "Шурпа", 485, RESTAURANT_3, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_7 = new Dish(START_DISH_ID + 6, "Самса с тыквой", 145, RESTAURANT_3, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_8 = new Dish(START_DISH_ID + 7, "Салат с осьминогом", 590, RESTAURANT_4, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_9 = new Dish(START_DISH_ID + 8, "Кальмар", 530, RESTAURANT_4, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_10 = new Dish(START_DISH_ID + 9, "Говядина велингтон", 950, RESTAURANT_4, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_11 = new Dish(START_DISH_ID + 10, "Суп из белых грибов", 175, RESTAURANT_5, LocalDate.of(2020, 1, 16));
    public static final Dish DISH_12 = new Dish(START_DISH_ID + 11, "Блины с семгой", 227, RESTAURANT_5, LocalDate.of(2020, 1, 16));


    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

}

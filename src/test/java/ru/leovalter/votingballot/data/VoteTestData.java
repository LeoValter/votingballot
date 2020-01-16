package ru.leovalter.votingballot.data;

import ru.leovalter.votingballot.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.leovalter.votingballot.data.RestaurantTestData.*;
import static ru.leovalter.votingballot.data.UserTestData.ADMIN;
import static ru.leovalter.votingballot.data.UserTestData.USER;

public class VoteTestData {

    public static final int START_VOTE_ID = 100019;
    public static final Vote VOTE_1 = new Vote(START_VOTE_ID, LocalDate.of(2020, 1, 15), USER, RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(START_VOTE_ID + 1, LocalDate.of(2020, 1, 16), USER, RESTAURANT_4);
    public static final Vote VOTE_3 = new Vote(START_VOTE_ID + 2, LocalDate.of(2020, 1, 16), ADMIN, RESTAURANT_3);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }
}

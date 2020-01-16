package ru.leovalter.votingballot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.leovalter.votingballot.model.Vote;
import ru.leovalter.votingballot.util.exception.NotFoundException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.leovalter.votingballot.data.RestaurantTestData.*;
import static ru.leovalter.votingballot.data.UserTestData.USER;
import static ru.leovalter.votingballot.data.UserTestData.USER_ID;
import static ru.leovalter.votingballot.data.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void delete() {
        service.delete(START_VOTE_ID);
        assertMatch(service.getAll(), VOTE_2, VOTE_3);
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1));
    }

    @Test
    void create() {
        Vote newVote = new Vote(null, LocalDate.of(2020, 1, 14), USER, RESTAURANT_1);
        Vote created = service.createOrUpdate(new Vote(newVote), USER_ID, RESTAURANT_ID_1);
        assertMatch(service.getAll(), created, VOTE_1, VOTE_2, VOTE_3);
    }

    @Test
    void createVoteInSameDay() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Vote newVote = new Vote(null, LocalDate.of(2020, 1, 16), USER, RESTAURANT_1);
            Vote created = service.createOrUpdate(new Vote(newVote), USER_ID, RESTAURANT_ID_1);
            assertMatch(service.getAll(), VOTE_1, VOTE_2, VOTE_3, created);
        });
    }

    @Test
    void update() {
        Vote updated = new Vote(VOTE_1);
        updated.setDate(LocalDate.of(2020, 1, 10));
        service.createOrUpdate(new Vote(updated), USER_ID, RESTAURANT_ID_1);
        assertMatch(service.get(START_VOTE_ID), updated);
    }

    @Test
    void get() {
        assertMatch(service.get(START_VOTE_ID + 1), VOTE_2);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(1));
    }

    @Test
    void getAll() {
        assertMatch(service.getAll(), VOTE_1, VOTE_2, VOTE_3);
    }
}
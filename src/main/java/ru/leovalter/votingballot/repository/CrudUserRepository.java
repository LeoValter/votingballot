package ru.leovalter.votingballot.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.leovalter.votingballot.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    User getByEmail(String email);

    //    https://stackoverflow.com/a/46013654/548473
    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithVotes(int id);
}

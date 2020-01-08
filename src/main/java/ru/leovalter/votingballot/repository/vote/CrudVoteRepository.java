package ru.leovalter.votingballot.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.leovalter.votingballot.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Vote v WHERE v.user.id = ?1 ORDER BY v.date")
    List<Vote> getAllByUserId(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.email =:email ORDER BY v.date")
    List<Vote> getAllByUserEmail(@Param("email") String email);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id = ?1 ORDER BY v.date")
    List<Vote> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.name =:restaurantName ORDER BY v.date")
    List<Vote> getAllByRestaurantName(@Param("restaurantName") String restaurantName);

    List<Vote> getAllByDate(LocalDate localDate);
}

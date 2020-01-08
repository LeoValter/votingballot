package ru.leovalter.votingballot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurants_unique_name_idx")})
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("date DESC")
    @JsonIgnore
    private List<Dish> dishes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "restaurant")
    @OrderBy("date DESC")
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        this(r.getId(), r.getName());
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    protected List<Dish> getDishes() {
        return dishes;
    }

    protected void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    protected List<Vote> getVotes() {
        return votes;
    }

    protected void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

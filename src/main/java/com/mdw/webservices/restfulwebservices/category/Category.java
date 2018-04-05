package com.mdw.webservices.restfulwebservices.category;

import com.mdw.webservices.restfulwebservices.event.Event;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import javax.validation.constraints.Size;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;


@ApiModel(description="All details about the user. ")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String name;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String descripion;

    //@OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    //@OneToMany(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER, mappedBy = "category")
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "category")
    private Set<Event> events = new HashSet<Event>();

    public Category(){
        super();
    }

    public Category(@Size(min = 2, message = "Title should have at least two characters") String name, @Size(min = 2, message = "Title should have at least two characters") String descripion) {
        super();
        this.name = name;
        this.descripion = descripion;
    }

    public Category(@Size(min = 2, message = "Title should have at least two characters") String name, @Size(min = 2, message = "Title should have at least two characters") String descripion, Set<Event> events) {
        super();
        this.name = name;
        this.descripion = descripion;
        this.events = events;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripion() {
        return descripion;
    }

    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descripion='" + descripion + '\'' +
                ", events=" + events +
                '}';
    }
}

package com.mdw.webservices.restfulwebservices.event;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mdw.webservices.restfulwebservices.category.Category;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user. ")
@Entity
public class Event {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String name;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String description;

    private String source;

    private String author;

    @Past(message="Date cannot be in the future")
    @ApiModelProperty(notes="Birth date should be in the past")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE, d MMM yyyy HH:mm:ss Z")
    private Date date;

    //@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    //@JoinColumn(name="category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;

    public Event() {
        super();
    }

    public Event(@Size(min = 2, message = "Title should have at least two characters") String name, @Size(min = 2, message = "Title should have at least two characters") String description, String source, String author, @Past(message = "Date cannot be in the future") Date date) {
        super();
        this.name = name;
        this.description = description;
        this.source = source;
        this.author = author;
        this.date = date;
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
        return description;
    }

    public void setDescripion(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", category=" + category +
                '}';
    }
}

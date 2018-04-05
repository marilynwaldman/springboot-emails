package com.mdw.webservices.restfulwebservices.article;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user. ")
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String title;

    private String source;

    private String author;

    @Past(message="Date cannot be in the future")
    @ApiModelProperty(notes="Birth date should be in the past")
    private Date date;

    public Article() {
        super();
    }

    public Article(Integer id, String title, String source, String author, Date date) {
        super();
        this.id = id;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }
}

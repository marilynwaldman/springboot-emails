package com.mdw.webservices.restfulwebservices.Sender;


import com.mdw.webservices.restfulwebservices.email.Email;

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


@ApiModel(description="All details about the sender. ")
@Entity

public class Sender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String name;

    @Size(min=2, message="Title should have at least two characters")
    @ApiModelProperty(notes="Name should have atleast 2 characters")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "sender")
    private Set<Email> emails = new HashSet<>();

    public Sender(){
        super();
    }

    public Sender(@Size(min = 2, message = "Title should have at least two characters") String name, @Size(min = 2, message = "Title should have at least two characters") String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public Sender(@Size(min = 2, message = "Title should have at least two characters") String name, @Size(min = 2, message = "Title should have at least two characters") String email, Set<Email> emails) {
        super();
        this.name = name;
        this.email = email;
        this.emails = emails;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", emails=" + emails +
                '}';
    }
}

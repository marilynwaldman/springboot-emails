package com.mdw.webservices.restfulwebservices.email;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mdw.webservices.restfulwebservices.email.Email;
import com.mdw.webservices.restfulwebservices.email.EmailNotFoundException;
import com.mdw.webservices.restfulwebservices.email.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmailJPAResource {



    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/jpa/emails")
    public List<Email> retrieveAllEmails() {
        return emailRepository.findAll();
    }

    @GetMapping("/jpa/emails/{id}")
    public Resource<Email> retrieveEmail(@PathVariable Integer id) {
        Optional<Email> email = emailRepository.findById((int)id);

        if (!email.isPresent())
            throw new EmailNotFoundException("Email Not Found id :" + id);

        // "all-emails", SERVER_PATH + "/emails"
        // retrieveAllEmails
        Resource<Email> resource = new Resource<Email>(email.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllEmails());

        resource.add(linkTo.withRel("all-emails"));

        // HATEOAS

        return resource;
    }



}

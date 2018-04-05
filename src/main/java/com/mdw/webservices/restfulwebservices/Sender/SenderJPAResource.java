package com.mdw.webservices.restfulwebservices.Sender;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import javax.validation.Valid;

import com.mdw.webservices.restfulwebservices.Sender.Sender;
import com.mdw.webservices.restfulwebservices.Sender.SenderNotFoundException;
import com.mdw.webservices.restfulwebservices.Sender.SenderRepository;
import com.mdw.webservices.restfulwebservices.email.Email;
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
public class SenderJPAResource {



    @Autowired
    private SenderRepository senderRepository;

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/jpa/senders")
    public List<Sender> retrieveAllSenders() {
        return senderRepository.findAll();
    }

    @GetMapping("/jpa/senders/{id}")
    public Resource<Sender> retrieveSender(@PathVariable Integer id) {

        Optional<Sender> sender = senderRepository.findById((int)id);

        if (!sender.isPresent())
            throw new SenderNotFoundException("Sender Not Found id-" + id);


        // "all-senders", SERVER_PATH + "/senders"
        // retrieveAllSenders
        Resource<Sender> resource = new Resource<Sender>(sender.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllSenders());

        resource.add(linkTo.withRel("all-senders"));

        // HATEOAS

        return resource;

    }

    @DeleteMapping("/jpa/senders/{id}")
    public void deleteSender(@PathVariable Integer id) {
        senderRepository.deleteById((int)id);
    }

    //
    // input - details of sender
    // output - CREATED & Return the created URI

    // HATEOAS

    @PostMapping("/jpa/senders")
    public ResponseEntity<Object> createSender(@Valid @RequestBody Sender sender) {
        Sender savedSender = senderRepository.save(sender);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedSender.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/jpa/senders/{id}/emails")
    public List<Sender> retrieveAllSenders(@PathVariable Integer id) {
        Optional<Sender> senderOptional = senderRepository.findById((int)id);

        if(!senderOptional.isPresent()) {
            throw new SenderNotFoundException("id-" + id);
        }

        //return senderOptional.get().getEmails();
        List list = new ArrayList<Email>(senderOptional.get().getEmails());
        return list;

    }

    @PostMapping("/jpa/senders/{id}/emails")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Email email) {

        Optional<Sender> senderOptional = senderRepository.findById((int)id);

        if(!senderOptional.isPresent()) {
            throw new SenderNotFoundException(" Can't post id-" + id);
        }

        Sender sender = senderOptional.get();



        email.setSender(sender);
        
        emailRepository.save(email);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(email.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    
}

package com.mdw.webservices.restfulwebservices.event;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.mdw.webservices.restfulwebservices.event.Event;
import com.mdw.webservices.restfulwebservices.event.EventNotFoundException;
import com.mdw.webservices.restfulwebservices.event.EventRepository;
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

public class EventJPAResource {


    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/jpa/events")
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @GetMapping("/jpa/events/{id}")
    public Resource<Event> retrieveEvent(@PathVariable Integer id) {
        Optional<Event> event = eventRepository.findById((int)id);

        if (!event.isPresent())
            throw new EventNotFoundException("Event Not Found id :" + id);

        // "all-events", SERVER_PATH + "/events"
        // retrieveAllEvents
        Resource<Event> resource = new Resource<Event>(event.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllEvents());

        resource.add(linkTo.withRel("all-events"));

        // HATEOAS

        return resource;
    }




}

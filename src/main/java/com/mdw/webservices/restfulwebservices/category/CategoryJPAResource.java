package com.mdw.webservices.restfulwebservices.category;

import com.mdw.webservices.restfulwebservices.event.Event;
import com.mdw.webservices.restfulwebservices.event.EventNotFoundException;
import com.mdw.webservices.restfulwebservices.event.EventRepository;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import javax.validation.Valid;

import com.mdw.webservices.restfulwebservices.category.Category;
import com.mdw.webservices.restfulwebservices.category.CategoryNotFoundException;
import com.mdw.webservices.restfulwebservices.category.CategoryRepository;
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
public class CategoryJPAResource {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/jpa/categories")
    public List<Category> retrieveAllCategorys() {
        return categoryRepository.findAll();
    }

    @GetMapping("/jpa/categories/{id}")
    public Resource<Category> retrieveCategory(@PathVariable Integer id) {

        Optional<Category> category = categoryRepository.findById((int)id);

        if (!category.isPresent())
            throw new CategoryNotFoundException("Category Not Found id-" + id);


        // "all-categorys", SERVER_PATH + "/categorys"
        // retrieveAllCategorys
        Resource<Category> resource = new Resource<Category>(category.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllCategorys());

        resource.add(linkTo.withRel("all-categorys"));

        // HATEOAS

        return resource;

    }

    @DeleteMapping("/jpa/categories/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById((int)id);
    }

    //
    // input - details of category
    // output - CREATED & Return the created URI

    // HATEOAS

    @PostMapping("/jpa/categories")
    public ResponseEntity<Object> createCategory(@Valid @RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedCategory.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/jpa/categories/{id}/events")
    public List<Category> retrieveAllUsers(@PathVariable Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById((int)id);

        if(!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException("id-" + id);
        }

        //return categoryOptional.get().getEvents();
        List list = new ArrayList<Event>(categoryOptional.get().getEvents());
        return list;

    }

    @PostMapping("/jpa/categories/{id}/events")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @RequestBody Event event) {

        Optional<Category> categoryOptional = categoryRepository.findById((int)id);

        if(!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException(" Can't post id-" + id);
        }

        Category category = categoryOptional.get();

        event.setCategory(category);

        eventRepository.save(event);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(event.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }



}

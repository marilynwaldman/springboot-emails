package com.mdw.webservices.restfulwebservices.article;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@RestController
public class ArticleResource {

    @Autowired
    private ArticleDoaService service;

    @GetMapping("/articles")
    public List<Article> retrieveAllArticles(){
        return service.findAll();
    }

    @GetMapping("/articles/{id}")
    public Resource<Article> retrieveArticle(@PathVariable Integer id){

        Article article = service.findOne(id);
        if(article == null){
            throw new ArticleNotFoundException("id-" + id);
        }



        //"all-users", SERVER_PATH + "/users"
        //retrieveAllUsers
        Resource<Article> resource = new Resource<Article>(article);

        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllArticles());

        resource.add(linkTo.withRel("all-articles"));

        //HATEOAS

        return resource;

    }


    @DeleteMapping("/articles/{id}")
    public void deleteUser(@PathVariable int id) {
        Article article= service.deleteById(id);

        if(article==null)
            throw new ArticleNotFoundException("id-"+ id);
    }


    @PostMapping("/articles")
    public ResponseEntity<Object> createArticle(@Valid @RequestBody Article article){
        Article savedArticle = service.save(article);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedArticle.getId()).toUri();

        return ResponseEntity.created(location).build();

    }





}

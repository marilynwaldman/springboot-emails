package com.mdw.webservices.restfulwebservices.article;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

public class ArticleJPAResource {


    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/jpa/articles")
    public List<Article> retrieveAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/jpa/articles/{id}")
    public Resource<Article> retrieveArticle(@PathVariable int id) {
        Optional<Article> article = articleRepository.findById(id);

        if (!article.isPresent())
            throw new ArticleNotFoundException("id-" + id);

        // "all-articles", SERVER_PATH + "/articles"
        // retrieveAllArticles
        Resource<Article> resource = new Resource<Article>(article.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllArticles());

        resource.add(linkTo.withRel("all-articles"));

        // HATEOAS

        return resource;
    }

    @DeleteMapping("/jpa/articles/{id}")
    public void deleteArticle(@PathVariable int id) {
        articleRepository.deleteById(id);
    }

    //
    // input - details of article
    // output - CREATED & Return the created URI

    // HATEOAS

    @PostMapping("/jpa/articles")
    public ResponseEntity<Object> createArticle(@Valid @RequestBody Article article) {
        Article savedArticle = articleRepository.save(article);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedArticle.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }


}

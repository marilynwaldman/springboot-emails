package com.mdw.webservices.restfulwebservices.article;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component

public class ArticleDoaService {

    private static List<Article> articles = new ArrayList<>();

    private static Integer articlesCount = 3;

    static {
        articles.add(new Article(1, "Cambridge Analytica offers new defense of 2016 practices",
                "CNN","Katelyn Polantz" , new Date()));
        articles.add(new Article(2,"Cambridge Analytica reportedly still hasn’t deleted Facebook user data as promised",
               "The Verge", "Nick Statt", new Date() ));
        articles.add(new Article(3, "Cambridge Analytica parent company had access to secret MoD information",
                "The Guardian", "Dan Sabbagh", new Date()));
    }

    public List<Article> findAll(){
        return articles;
    }

    public Article save(Article article){
        if(article.getId()==null){
            article.setId(++articlesCount);
        }
        articles.add(article);
        return article;
    }

    public Article findOne(Integer id){
        for(Article article : articles){
            if(article.getId() == id){
                return article;
            }
        }
        return null;
    }

    public Article deleteById(int id) {
        Iterator<Article> iterator = articles.iterator();
        while (iterator.hasNext()) {
            Article article = iterator.next();
            if (article.getId() == id) {
                iterator.remove();
                return article;
            }
        }
        return null;
    }    


}

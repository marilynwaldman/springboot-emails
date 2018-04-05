package com.mdw.webservices.restfulwebservices.category;

import com.mdw.webservices.restfulwebservices.category.Category;
import com.mdw.webservices.restfulwebservices.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
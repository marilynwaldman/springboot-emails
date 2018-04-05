package com.mdw.webservices.restfulwebservices.event;


import com.mdw.webservices.restfulwebservices.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EventRepository extends JpaRepository<Event, Integer> {


}
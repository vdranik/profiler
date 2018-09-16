package com.profiler.ProfileApp.repositories;

import com.profiler.ProfileApp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Integer> {
}

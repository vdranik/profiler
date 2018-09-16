package com.profiler.ProfileApp.bootstrap;

import com.profiler.ProfileApp.domain.Person;
import com.profiler.ProfileApp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class ProfileManagementApplicationLoader implements ApplicationListener<ContextRefreshedEvent> {

    private PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Person person1 = new Person("Yevgen Polukov", "Senior developer");
        Person person2 = new Person("Yulia Baranovska", "AQA");
        Person person3 = new Person("Kirill Lyubin", "Middle developer");
        Person person4 = new Person("Yulia Frishko", "Middle developer");
        Person person5 = new Person("Ievgen Kuibida", "Senior developer");
        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
        personRepository.save(person5);
    }
}

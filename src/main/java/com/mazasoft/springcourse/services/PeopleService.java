package com.mazasoft.springcourse.services;

import com.mazasoft.springcourse.models.Mood;
import com.mazasoft.springcourse.models.Person;
import com.mazasoft.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Collection<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(Integer id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    public Optional<Person> findByEmail(String email) {
        Collection<Person> people = peopleRepository.findByEmail(email);
        return people.stream().findAny();
    }

    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        person.setMood(Mood.CALM);
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Integer id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(Integer id) {
        peopleRepository.deleteById(id);
    }


}

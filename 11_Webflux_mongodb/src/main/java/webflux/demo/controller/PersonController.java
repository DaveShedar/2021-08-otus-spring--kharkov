package webflux.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.model.Person;
import webflux.demo.repositories.PersonRepository;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/persons")
    public Flux<Person> getAll() {
        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public Mono<Person> save(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/persons/{id}")
    public Mono<Person> get(@PathVariable String id) {
        return personRepository.findById(id);
    }

    @DeleteMapping("/persons/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return personRepository.deleteById(id);
    }
}

package webflux.demo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import webflux.demo.model.Person;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
}

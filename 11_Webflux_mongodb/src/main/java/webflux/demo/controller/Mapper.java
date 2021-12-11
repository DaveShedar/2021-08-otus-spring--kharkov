package webflux.demo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import webflux.demo.model.Person;
import webflux.demo.repositories.PersonRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class Mapper {

    @Bean
    public RouterFunction<ServerResponse> pathways(PersonRepository personRepository) {
        return route()
                .GET("/persons", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(personRepository.findAll(), Person.class))
                .POST("/persons", accept(APPLICATION_JSON),
                        serverRequest -> serverRequest.bodyToMono(Person.class)
                                .map(personRepository::save)
                                .flatMap(result -> ok().body(result, Person.class)))
                .GET("/persons/{id}", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(personRepository.findById(serverRequest.pathVariable("id")), Person.class))
                .DELETE("/persons/{id}", accept(APPLICATION_JSON),
                        serverRequest -> ok().contentType(APPLICATION_JSON)
                                .body(personRepository.deleteById(serverRequest.pathVariable("id")), Person.class))
                .build();
    }
}

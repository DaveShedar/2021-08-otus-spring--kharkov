package webflux.demo.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import webflux.demo.model.Role;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {
}

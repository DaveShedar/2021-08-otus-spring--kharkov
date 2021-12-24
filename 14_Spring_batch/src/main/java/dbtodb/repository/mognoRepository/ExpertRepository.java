package dbtodb.repository.mognoRepository;

import dbtodb.model.mongodb.Expert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends MongoRepository<Expert, String> {
}

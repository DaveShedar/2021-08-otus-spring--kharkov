package dbtodb.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import dbtodb.model.mongodb.Expert;
import dbtodb.repository.mognoRepository.ExpertRepository;

@ChangeLog
public class MongoDatabaseChangelog {

    @ChangeSet(order = "000", id = "dropDb", author = "kharkov", runAlways = true)
    public void dropDb(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initStudents", author = "kharkov", runAlways = true)
    public void initBooks(ExpertRepository expertRepository) {

       Expert expert = new Expert(null, "Lev", "engines", "id_0034");
       expertRepository.save(expert);

    }
}

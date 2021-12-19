package webflux.demo.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import webflux.demo.model.Person;
import webflux.demo.model.Role;

@ChangeLog
public class DatabaseChangeLog {

    @Autowired
    MongockTemplate mongoTemplate;

    @ChangeSet(order = "001", id = "dropDb", author = "dmitry", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertData", author = "kharkov")
    public void insertData(MongockTemplate template) {
        Role role1 = new Role(null, "ADMIN");
        Role role2 = new Role(null, "STAFF");
        Role role3 = new Role(null, "USER");

        template.save(role1);
        template.save(role2);
        template.save(role3);

        Person person1 = new Person(null, "Alex Magenta", "alex@mail.ru", role1);
        Person person2 = new Person(null, "Olga Silver", "olga@mail.ru", role2);
        Person person3 = new Person(null, "Kira Black", "kira@mail.ru", role3);
        Person person4 = new Person(null, "Andrey Yellow", "andrey@mail.ru", role3);
        Person person5 = new Person(null, "Luda Blue", "Luda@mail.ru", role3);

        template.save(person1);
        template.save(person2);
        template.save(person3);
        template.save(person4);
        template.save(person5);
    }
}

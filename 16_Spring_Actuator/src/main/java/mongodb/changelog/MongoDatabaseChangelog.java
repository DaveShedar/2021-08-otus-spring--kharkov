//package mongodb.changelog;
//
//import com.github.cloudyrock.mongock.ChangeLog;
//import com.github.cloudyrock.mongock.ChangeSet;
//import com.mongodb.client.MongoDatabase;
//import mongodb.model.Author;
//import mongodb.model.Book;
//import mongodb.model.Comment;
//import mongodb.model.Genre;
//import mongodb.model.Role;
//import mongodb.model.User;
//import mongodb.repository.AuthorRepository;
//import mongodb.repository.BookRepository;
//import mongodb.repository.GenreRepository;
//import mongodb.repository.RoleRepository;
//import mongodb.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//
//@ChangeLog
//public class MongoDatabaseChangelog {
//
//    @ChangeSet(order = "000", id = "dropDb", author = "kharkov", runAlways = true)
//    public void dropDb(MongoDatabase database) {
//    }
//
//    @ChangeSet(order = "000", id = "initBooks", author = "kharkov", runAlways = true)
//    public void initBooks(UserRepository userRepository, RoleRepository roleRepository) {
////        Role roleUser = new Role(null, "ROLE_USER");
////        Role roleAdmin = new Role(null, "ROLE_ADMIN");
////
////        Set<Role> set1 = new LinkedHashSet<>();
////        Set<Role> set2 = new LinkedHashSet<>();
////        set1.add(roleAdmin);
////        set1.add(roleUser);
////        set2.add(roleUser);
////
////        User user1 = new User(null, "alex", "pushkin", 33, "pushkin@mail.ru", "123",  set1);
////        User user2 = new User(null, "lev", "tolstoy", 48, "tolstoy@mail.ru", "123",  set2);
////
////        roleRepository.save(roleAdmin);
////        roleRepository.save(roleUser);
////        userRepository.save(user1);
////        userRepository.save(user2);
//    }
//}

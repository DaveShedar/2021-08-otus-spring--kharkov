package books;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class BooksApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BooksApplication.class, args);
		Console.main(args);
	}
}

package dbtodb.util;

import dbtodb.model.h2.Student;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomReader extends JdbcCursorItemReader<Student> implements ItemReader<Student> {

    public CustomReader(@Autowired DataSource primaryDataSource) {
        setDataSource(primaryDataSource);
        setSql("SELECT id, name, course, grade, specialization FROM Student");
        setFetchSize(10);
        setRowMapper(new StudentRowMapper());
    }

    public class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();

            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCourse(rs.getInt("grade"));
            student.setSpecialization(rs.getString("specialization"));
            return student;
        }
    }


}

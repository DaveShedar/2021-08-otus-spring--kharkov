package dbtodb.util;

import dbtodb.model.h2.Student;
import dbtodb.model.mongodb.Expert;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomProcessor implements ItemProcessor<Student, Expert> {

    private final String SPECIALIZATION_DEFENSE_TOWER = "defenseTowers";
    private final String SPECIALIZATION_SHIPS = "starShips";
    private final String DEPARTMENT_DEFENSE_ID = "defense_0045";
    private final String DEPARTMENT_SHIPS_ID = "ships_0011";

    @Override
    public Expert process(Student student) {
        System.out.println("MyBatchProcessor : Processing data : " + student);
        Expert expert = new Expert();

        expert.setId(null);
        expert.setName(student.getName());
        expert.setSpecialization(student.getSpecialization());

        if(student.getSpecialization().equals(SPECIALIZATION_DEFENSE_TOWER)) {
            expert.setDepartment(DEPARTMENT_DEFENSE_ID);
        } else if(student.getSpecialization().equals(SPECIALIZATION_SHIPS)){
            expert.setDepartment(DEPARTMENT_SHIPS_ID);
        }

        return expert;
    }
}

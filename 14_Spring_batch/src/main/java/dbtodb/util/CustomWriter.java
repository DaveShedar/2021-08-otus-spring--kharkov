package dbtodb.util;

import dbtodb.model.mongodb.Expert;
import dbtodb.repository.mognoRepository.ExpertRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CustomWriter implements ItemWriter<Expert> {

    private final ExpertRepository expertRepository;

    @Override
    public void write(List<? extends Expert> list) {
        for (Expert expert : list) {
            System.out.println("MyCustomWriter : Writing expert : " + expert.getId() + ", " + expert.getName() + ", " + expert.getSpecialization() + ", " + expert.getDepartment());
            expertRepository.save(expert);
        }
    }
}

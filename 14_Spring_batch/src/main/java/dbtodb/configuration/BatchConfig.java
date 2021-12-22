package dbtodb.configuration;

import dbtodb.model.h2.Student;
import dbtodb.model.mongodb.Expert;
import dbtodb.util.CustomProcessor;
import dbtodb.util.CustomReader;
import dbtodb.util.CustomWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomReader customReader;
    private final CustomWriter customWriter;
    private final CustomProcessor customProcessor;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJobFromDbToDb")
                .incrementer(new RunIdIncrementer())
                .flow(createStep())
                .end()
                .build();
    }

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("MyStep")
                .<Student, Expert> chunk(10)
                .reader(customReader)
                .processor(customProcessor)
                .writer(customWriter)
                .build();
    }
}

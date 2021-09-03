package model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tests {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private String question;

    @CsvBindByName
    private String answer1;

    @CsvBindByName
    private String answer2;

    @CsvBindByName
    private String answer3;

    @CsvBindByName
    private String answer4;

    @CsvBindByName
    private int rightAnswer;
}

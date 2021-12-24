package si;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import si.model.ButterflyState;
import si.model.Month;
import si.utils.ButterflyLife;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@EnableIntegration
@IntegrationComponentScan
@SpringBootApplication
public class SiApplication {

    private static String[] MONTHS = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    private static ArrayList<Month> randomChooseMonth() {
        Month randomMonth1 = new Month(MONTHS[(int) Math.random() * MONTHS.length]);
        Month randomMonth2 = new Month(MONTHS[(int) Math.random() * MONTHS.length]);

//        Month randomMonth1 = new Month("Май");
//        Month randomMonth2 = new Month("Июнь");

        ArrayList<Month> monthList = new ArrayList<>();
        monthList.add(randomMonth1);
        monthList.add(randomMonth2);

        return monthList;
    }

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SiApplication.class);
        ButterflyLife butterflyLife = ctx.getBean(ButterflyLife.class);
        ForkJoinPool pool = ForkJoinPool.commonPool();

        pool.execute(() -> {
            ArrayList<Month> months = new ArrayList<>();
            String stringMonth = months.stream().map(month -> month.getName()).collect(Collectors.joining(", "));

            System.out.println("Необходимо уточнить состояние бабочки в следующих месяцах: " + stringMonth);

            ArrayList<ButterflyState> butterflyStates = butterflyLife.process(months);
            for (int i = 0; i < butterflyStates.size(); i++) {
                System.out.println("Состояние бабочки в: " + months.get(i).getName() + " ---> " + butterflyStates.get(i).getStateName());
            }
            ctx.close();
        });




    }


}

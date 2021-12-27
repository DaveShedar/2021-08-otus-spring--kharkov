package si.utils;

import org.springframework.stereotype.Service;
import si.model.ButterflyState;
import si.model.Month;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class Transformation {

    private final HashMap<String, String> states = new LinkedHashMap<>();
    {
        states.put("Январь", "Куколка");
        states.put("Февраль", "Куколка");
        states.put("Март", "Куколка");
        states.put("Апрель", "Имаго");
        states.put("Май", "Яйцо");
        states.put("Июнь", "Гусеница");
        states.put("Июль", "Гусеница");
        states.put("Август", "Гусеница");
        states.put("Сентябрь", "Куколка");
        states.put("Октябрь", "Куколка");
        states.put("Ноябрь", "Куколка");
        states.put("Декабрь", "Куколка");
    }

    public ButterflyState transform(Month month) throws InterruptedException {
        System.out.println("Уточняю состояние бабочки месяце: " + month.getName());
        Thread.sleep(3000);
        System.out.println("Поиск состояния завершен");
        return new ButterflyState(states.get(month.getName()));
    }
}

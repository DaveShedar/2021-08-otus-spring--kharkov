package books.Util;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class IoStreamHelper {
    private final Scanner scanner = new Scanner(System.in);

    public <T> T chooseThing(List<T> thingsList, String message) {
        System.out.println(message);
        int chooseThingId = scanner.nextInt() - 1;
        System.out.println("Вы выбрали: " + thingsList.get(chooseThingId));
        return thingsList.get(chooseThingId);
    }
}

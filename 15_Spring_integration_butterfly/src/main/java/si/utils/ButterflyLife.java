package si.utils;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import si.model.ButterflyState;
import si.model.Month;

import java.util.ArrayList;

@MessagingGateway
public interface ButterflyLife {

    @Gateway(requestChannel = "monthChannel", replyChannel = "butterflyStateChannel")
    ArrayList<ButterflyState> process(ArrayList<Month> monthList);
}

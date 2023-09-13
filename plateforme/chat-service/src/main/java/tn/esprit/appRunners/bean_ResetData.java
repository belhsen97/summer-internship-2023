package tn.esprit.appRunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=1)//Register BeanRunnerOne bean
@Component
public class bean_ResetData implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {


    }
}






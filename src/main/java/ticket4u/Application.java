package ticket4u;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ticket4u.sqlConnection.PostConstruction;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        //PostConstruction p = new PostConstruction();
        //p.initDB();


        SpringApplication.run(Application.class, args);


    }
}
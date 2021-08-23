package zipFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {
    private static final Logger LOGGER = LogManager.getLogger(ApiApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        LOGGER.info("*********************************Api Application has started from main-Thread*********************************");
    }
}
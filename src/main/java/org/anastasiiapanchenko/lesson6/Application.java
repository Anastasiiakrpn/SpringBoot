package org.anastasiiapanchenko.lesson6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class Application {

    private Application() {
    }

    /**
     * The entry point of the application.
     *
     * @param args the input arguments passed to the application
     */
    public static void main(final String[] args) {

        SpringApplication.run(Application.class, args);
    }
}

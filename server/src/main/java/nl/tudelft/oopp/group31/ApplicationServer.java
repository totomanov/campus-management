package nl.tudelft.oopp.group31;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationServer {

    /**
     * The entry point for executing the server.
     * @param args An optional string array of arguments passed before running
     */
    public static void main(String[] args) {
        SpringApplication.run(ApplicationServer.class, args);
    }
}

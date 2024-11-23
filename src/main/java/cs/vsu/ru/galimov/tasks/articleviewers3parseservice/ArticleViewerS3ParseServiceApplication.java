package cs.vsu.ru.galimov.tasks.articleviewers3parseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArticleViewerS3ParseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleViewerS3ParseServiceApplication.class, args);
    }
}

package cs.vsu.ru.galimov.tasks.articleviewers3parseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableConfigurationProperties
@EnableKafka
public class ArticleViewerS3ParseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleViewerS3ParseServiceApplication.class, args);
    }
}

package cs.vsu.ru.galimov.tasks.articleviewers3parseservice;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ArticleViewerS3ParseServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ArticleViewerS3ParseServiceApplication.class, args);

        Runner runner = context.getBean(Runner.class);

        runner.run();
    }

}

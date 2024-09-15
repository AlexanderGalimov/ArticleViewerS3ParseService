package cs.vsu.ru.galimov.tasks.articleviewers3parseservice;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer.SummaryProducer;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl.ArticleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class ArticleViewerS3ParseServiceApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ArticleViewerS3ParseServiceApplication.class, args);
        SummaryProducer producer = context.getBean(SummaryProducer.class);
        ArticleServiceImpl service = context.getBean(ArticleServiceImpl.class);
        List<Article> articleList = service.findAll();
        for (Article a: articleList) {
            producer.send("summaryTopic", a.getUniqUIIDS3());
        }
    }

}

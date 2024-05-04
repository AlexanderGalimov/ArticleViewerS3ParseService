package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer.GraphProducer;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.topic.GraphTopic;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl.ArticleServiceImpl;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
@Configuration
public class Runner {
    private final GraphProducer producer;

    private final GraphTopic topic;

    private final ArticleServiceImpl articleService;

    @Autowired
    public Runner(GraphProducer producer, GraphTopic topic, ArticleServiceImpl articleService) {
        this.producer = producer;
        this.topic = topic;
        this.articleService = articleService;
    }

    public void run() {
        List<Article> articles = articleService.findAll();
        sendMessagesToKafka(articles);
    }

    public void sendMessagesToKafka(List<Article> articles) {
        for (Article article : articles) {
            System.out.println("sent");
            producer.send(topic.getTopicName(), article);
        }
    }
}

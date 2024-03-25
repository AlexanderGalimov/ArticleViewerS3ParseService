package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer;

import com.google.gson.Gson;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GraphProducer {

    @Qualifier("kafkaTemplate")
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Gson gson;

    @Autowired
    public GraphProducer(KafkaTemplate<String, String> kafkaTemplate, Gson gson) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    public void send(String topic, Article article) {
        String articleToJson = convertArticleToJson(article);
        kafkaTemplate.send(topic, articleToJson);
    }

    private String convertArticleToJson(Article article) {
        return gson.toJson(article);
    }
}


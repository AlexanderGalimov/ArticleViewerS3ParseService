package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SummaryProducer {

    @Qualifier("kafkaTemplate")
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Gson gson;

    @Autowired
    public SummaryProducer(KafkaTemplate<String, String> kafkaTemplate, Gson gson) {
        this.kafkaTemplate = kafkaTemplate;
        this.gson = gson;
    }

    public void send(String topic, String articleId) {
        String articleToJson = convertArticleToJson(articleId);
        kafkaTemplate.send(topic, articleToJson);
    }

    private String convertArticleToJson(String articleId) {
        return gson.toJson(articleId);
    }
}


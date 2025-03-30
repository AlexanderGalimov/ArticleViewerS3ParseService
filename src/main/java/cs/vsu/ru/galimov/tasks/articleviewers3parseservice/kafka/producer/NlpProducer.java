package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NlpProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String uniqueName) {
        kafkaTemplate.send(topic, uniqueName);
    }
}


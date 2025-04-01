package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.topic;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.properties.KafkaNlpTopicProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@RequiredArgsConstructor
@Configuration
public class NlpTopic {

    private final KafkaNlpTopicProperties properties;

    @Bean
    public NewTopic createNlpTopic() {
        return new NewTopic(properties.getName(),
                properties.getPartitions(),
                properties.getReplicationFactor());
    }
}

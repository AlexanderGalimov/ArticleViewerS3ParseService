package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "kafka.nlp-topic")
@Component
public class KafkaNlpTopicProperties {

    private String name;

    private int partitions;

    private short replicationFactor;
}

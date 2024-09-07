package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.topic;


import lombok.Getter;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
public class SummaryTopic {

    @Value("${kafka.topic.name.for-summary-topic}")
    private String topicName;

    @Value("${kafka.topic.partitions.for-summary-topic}")
    private int numPartitions;

    @Value("${kafka.topic.replication-factor.for-summary-topic}")
    private short replicationFactor;

    @Value("${kafka.producer.servers}")
    private String servers;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);

        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, numPartitions, replicationFactor);
    }
}

package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.consumer;

import com.google.gson.Gson;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component.PdfToTextExtractor;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer.SummaryProducer;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.topic.SummaryTopic;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio.MinioTemplate;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
public class S3FileParser {

    private final ArticleService articleService;

    private final MinioTemplate template;

    private final PdfToTextExtractor extractor;

    private final Gson gson;

    private final SummaryProducer producer;

    private final SummaryTopic topic;

    @Autowired
    public S3FileParser(ArticleService articleService, MinioTemplate template, PdfToTextExtractor extractor, Gson gson, SummaryProducer producer, SummaryTopic topic) {
        this.articleService = articleService;
        this.template = template;
        this.extractor = extractor;
        this.gson = gson;
        this.producer = producer;
        this.topic = topic;
    }

    @KafkaListener(topics = "${kafka.topic.name.for-s3-topic}", containerFactory = "kafkaListenerContainerFactory", concurrency = "${kafka.topic.partitions.for-s3-topic}")
    public void receive(String jsonName) {
        try {
            String name = convertJsonToUuid(jsonName);

            Article article = articleService.findByUniqUIIDS3(name);

            if(article != null && Objects.equals(article.getFullText(), "")){
                String fullText = extractor.extractTextFromPdf(name);

                article.setFullText(fullText);

                articleService.update(article);

                template.deleteFile(name);

                System.out.println("updated article");

                //producer.send(topic.getTopicName(), article.getId());
            }
            else {
                System.out.println("s3 file parser cannot find article");
            }

        } catch (Exception e) {
            System.out.println("Error in kafka listen" + e.getMessage());
        }
    }

    private String convertJsonToUuid(String uuid) {
        return gson.fromJson(uuid, String.class);
    }
}

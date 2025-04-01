package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.consumer;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.extractor.PdfToTextExtractor;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.producer.NlpProducer;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.properties.KafkaNlpTopicProperties;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl.ArticleServiceImpl;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl.SearchDocumentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileParser {

    private final ArticleServiceImpl articleService;

    private final PdfToTextExtractor extractor;

    private final NlpProducer producer;

    private final KafkaNlpTopicProperties nlpTopicProperties;

    private final SearchDocumentServiceImpl searchDocumentService;

    @KafkaListener(topics = "${kafka.s3-topic.name}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "${kafka.s3-topic.partitions}")
    public void receive(String uniqueName) {
        try {
            Article article = articleService.findByUniqUIIDS3(uniqueName);

            if (article != null && Objects.equals(article.getFullText(), "")) {
                String fullText = extractor.extractTextFromPdf(uniqueName);

                article.setFullText(fullText);

                articleService.update(article);

                searchDocumentService.save(new SearchDocument(article.getId(), fullText));

                log.info("Updated article with name: {}", uniqueName);

                producer.send(nlpTopicProperties.getName(), article.getUniqUIIDS3());

            } else {
                log.error("s3 file parser cannot find article with name: {}", uniqueName);
            }
        } catch (Exception e) {
            log.error("Error in kafka listen: " + e.getMessage());
        }
    }
}

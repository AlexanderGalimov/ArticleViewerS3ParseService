package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.kafka.consumer;

import com.google.gson.Gson;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component.NameParser;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component.PdfToTextExtractor;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio.MinioTemplate;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class S3FileParser {

    private final ArticleService articleService;

    private final MinioTemplate template;

    private final NameParser parser;

    private final PdfToTextExtractor extractor;

    private final Gson gson;

    @Autowired
    public S3FileParser(ArticleService articleService, MinioTemplate template, NameParser parser, PdfToTextExtractor extractor, Gson gson) {
        this.articleService = articleService;
        this.template = template;
        this.parser = parser;
        this.extractor = extractor;
        this.gson = gson;
    }

    @KafkaListener(topics = "${kafka.topic.name.for-s3-topic}", containerFactory = "kafkaListenerContainerFactory", concurrency = "${kafka.topic.partitions.for-s3-topic}")
    public void receive(String jsonName) {
        try {
            String name = convertJsonToArticle(jsonName);

            Article article = articleService.findByUniqUIIDS3(name);

            if(article != null){
                String link = parser.getS3Link(name);

                String fullText = extractor.extractTextFromPdf(link);

                article.setFullText(fullText);

                articleService.update(article);

                template.deleteFile(name);

                System.out.println("updated article " + article);
            }
            else {
                System.out.println("s3 file parser cannot find article");
            }

        } catch (Exception e) {
            System.out.println("Error in kafka listen" + e.getMessage());
        }
    }

    private String convertJsonToArticle(String articleJson) {
        return gson.fromJson(articleJson, String.class);
    }
}

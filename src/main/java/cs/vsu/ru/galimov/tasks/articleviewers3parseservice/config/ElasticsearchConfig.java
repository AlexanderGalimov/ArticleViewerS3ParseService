package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.config;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig {

    private final ElasticsearchOperations elasticsearchOperations;

    @Bean
    public IndexOperations createIndexIfNotExists() {
        IndexOperations indexOps = elasticsearchOperations.indexOps(IndexCoordinates.of("search_documents"));
        if (!indexOps.exists()) {
            indexOps.create();
            indexOps.putMapping(elasticsearchOperations.indexOps(SearchDocument.class).createMapping());
        }
        return indexOps;
    }
}


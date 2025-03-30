package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository.SearchDocumentRepository;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.SearchDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SearchDocumentServiceImpl implements SearchDocumentService {

    private final SearchDocumentRepository repository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public SearchDocument save(SearchDocument document) {
        return repository.save(document);
    }

    @Override
    public SearchDocument update(String id, SearchDocument updatedDocument) {
        SearchDocument existingDocument = repository.findById(id).orElse(null);
        if (existingDocument == null) {
            return null;
        }
        else {
            return repository.save(existingDocument);
        }
    }

    @Override
    public List<SearchDocument> search(String query) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(QueryBuilders.match(m -> m
                        .field("fullText")
                        .query(query)))
                .build();

        SearchHits<SearchDocument> searchHits = elasticsearchOperations.search(searchQuery, SearchDocument.class);
        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }
}


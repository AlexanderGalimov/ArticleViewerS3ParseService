package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository;


import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDocumentRepository extends ElasticsearchRepository<SearchDocument, String> {
}

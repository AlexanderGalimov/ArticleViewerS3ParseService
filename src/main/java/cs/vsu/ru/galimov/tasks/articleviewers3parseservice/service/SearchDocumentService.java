package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import org.springframework.stereotype.Component;

@Component
public interface SearchDocumentService {
    void save(SearchDocument document);
}

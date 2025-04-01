package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.SearchDocument;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository.SearchDocumentRepository;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.SearchDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchDocumentServiceImpl implements SearchDocumentService {

    private final SearchDocumentRepository repository;

    @Override
    public void save(SearchDocument document) {
        repository.save(document);
    }
}


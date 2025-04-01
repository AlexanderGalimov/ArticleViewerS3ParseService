package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Author;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository.AuthorRepository;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    public Author findById(String id) {
        return repository.findById(id).orElse(null);
    }
}

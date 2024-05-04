package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service;


import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Author;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface AuthorService {
    Author insert(Author author);

    List<Author> findAll();

    void delete(String id);

    Author findById(String id);

    Author update(Author author);

    Author findByName(String name);
}

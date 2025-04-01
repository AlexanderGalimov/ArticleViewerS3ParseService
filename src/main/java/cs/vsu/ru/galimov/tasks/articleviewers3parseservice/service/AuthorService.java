package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Author;

public interface AuthorService {

    Author findById(String id);
}

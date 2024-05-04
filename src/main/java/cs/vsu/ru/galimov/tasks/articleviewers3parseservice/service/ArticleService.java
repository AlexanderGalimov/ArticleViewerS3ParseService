package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service;


import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleService {
    Article insert(Article article);

    List<Article> findAll();

    void delete(String id);

    Article findById(String id);

    Article findByUniqUIIDS3(String uniqUIIDS3);

    Article update(Article object);
}

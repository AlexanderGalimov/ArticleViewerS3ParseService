package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;

public interface ArticleService {

    Article findById(String id);

    Article findByUniqUIIDS3(String uniqUIIDS3);

    void update(Article object);
}

package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    Article findByUniqUIIDS3(String uniqUIIDS3);
}

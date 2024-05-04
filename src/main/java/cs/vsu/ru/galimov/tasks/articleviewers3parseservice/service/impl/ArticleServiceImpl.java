package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository.ArticleRepository;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    private ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Article insert(Article article) {
        return articleRepository.insert(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Article archive = findById(id);
        articleRepository.delete(archive);
    }

    @Override
    public Article findById(String id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article findByUniqUIIDS3(String uniqUIIDS3) {
        return articleRepository.findByUniqUIIDS3(uniqUIIDS3);
    }

    @Override
    public Article update(Article object) {
        return articleRepository.save(object);
    }
}
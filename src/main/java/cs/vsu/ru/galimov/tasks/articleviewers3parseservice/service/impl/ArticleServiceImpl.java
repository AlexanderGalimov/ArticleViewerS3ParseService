package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.impl;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model.Article;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.repository.ArticleRepository;
import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;


    @Override
    public Article findById(String id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article findByUniqUIIDS3(String uniqUIIDS3) {
        return articleRepository.findByUniqUIIDS3(uniqUIIDS3);
    }

    @Override
    public void update(Article object) {
        articleRepository.save(object);
    }
}
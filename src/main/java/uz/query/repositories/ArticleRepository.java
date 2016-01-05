package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.Article;
import uz.query.models.Tag;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by sherali on 01/05/16.
 */
@Repository
public interface ArticleRepository extends BaseRepository<Article, Long> {

    List<Article> findByisDeleted(boolean isDeleted);

    List<Article> findAll();

    List<Article> findFirst10ByTitleContaining(String key);

    List<Article> findByIdIn(List<Long> idList);

}

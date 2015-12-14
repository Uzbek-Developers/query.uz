package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.Question;
import uz.query.repositories.base.BaseRepository;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question, Long> {

}

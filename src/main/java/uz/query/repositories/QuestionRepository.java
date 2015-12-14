package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.Question;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by sherali on 12/13/15.
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question, Long> {

    List<Question> findByisDeleted(boolean isDeleted);

}

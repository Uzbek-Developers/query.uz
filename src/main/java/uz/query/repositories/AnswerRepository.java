package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.Answer;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by sherali on 12/13/15.
 */
@Repository
public interface AnswerRepository extends BaseRepository<Answer, Long> {

    List<Answer> findByisDeleted(boolean isDeleted);
    List<Answer> findAllByOwnerId(Long id);
}

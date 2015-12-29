package uz.query.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import uz.query.models.Question;
import uz.query.models.Tag;
import uz.query.repositories.base.BaseRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by sherali on 12/13/15.
 */
@Repository
public interface QuestionRepository extends BaseRepository<Question, Long> {

    List<Question> findByisDeleted(boolean isDeleted);

    Page<Question> findAllByTagsIn(Collection<Tag> tags, Pageable pageable);

}

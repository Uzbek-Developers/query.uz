package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.Question;
import uz.query.models.Tag;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by sherali on 12/13/15.
 */
@Repository
public interface TagRepository extends BaseRepository<Tag, Long> {

    List<Tag> findByisDeleted(boolean isDeleted);

}

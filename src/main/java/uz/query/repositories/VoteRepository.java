package uz.query.repositories;

import uz.query.models.Vote;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by Mumin on 29.12.2015.
 */
public interface VoteRepository extends BaseRepository<Vote, Long> {
    List<Vote> findAllByOwnerId(Long id);

}

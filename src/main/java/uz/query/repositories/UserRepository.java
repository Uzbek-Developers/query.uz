package uz.query.repositories;

import org.springframework.stereotype.Repository;
import uz.query.models.User;
import uz.query.repositories.base.BaseRepository;

import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    List<User> findByisDeleted(boolean isDeleted);

    User findByUserName(String UserName);

    User findByEmail(String Email);
}

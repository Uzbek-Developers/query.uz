package uz.query.repositories.base;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import uz.query.models.base.BaseModel;

import java.io.Serializable;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseModel, ID extends Serializable>
        extends PagingAndSortingRepository<T, ID> {

    T checkAsDeleted(T t);

}

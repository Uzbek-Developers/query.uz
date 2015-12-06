package uz.query.repositories.base;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.query.models.base.BaseModel;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Transactional(readOnly = false)
public class BaseRepositoryImpl<T extends BaseModel, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public T checkAsDeleted(T t) {
        t.setIsDeleted(true);
        return this.save(t);
    }

    @Override
    public <S extends T> S save(S entity) {
        Date now = new Date();
        if (entity.getId() == null) {
            entity.setCreationDate(now);
        }
        entity.setModifiedDate(now);
        return super.save(entity);
    }
}

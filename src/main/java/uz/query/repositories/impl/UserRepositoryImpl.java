package uz.query.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uz.query.models.User;
import uz.query.repositories.UserRepositoryCustom;
import uz.query.security.SecurityUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Mirjalol Bahodirov on 12/28/15 with love.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public User getUserByPrincipial(String email, String password) {
        try {
            User user = entityManager
                    .createQuery("from my_user u where u.email=:email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return securityUtil.matches(password, user.getPassword()) ? user : null;
        } catch (NoResultException e) {
            return null;
        }
    }

}

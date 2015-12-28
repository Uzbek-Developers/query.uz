package uz.query.repositories;

import uz.query.models.User;

/**
 * Created by Mirjalol Bahodirov on 12/28/15 with love.
 */
public interface UserRepositoryCustom {

    User getUserByPrincipial(String name, String password);

}

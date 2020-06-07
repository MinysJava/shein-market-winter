package com.geekbrains.geekmarketwinter.Providers;

import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.interfaces.IUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class UserProvider implements IUserProvider {
    private final Sql2o sql2o;

    private static final String SELECT_USER_QUERY = "select id, username, password, first_name, last_name, email from users" +
            " where username = :u_name";

    public UserProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public User findByUserName(String userName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_QUERY, false)
                    .addParameter("u_name", userName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);
        }
    }
}

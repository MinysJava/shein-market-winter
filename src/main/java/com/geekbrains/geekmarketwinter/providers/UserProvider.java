package com.geekbrains.geekmarketwinter.providers;

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

    private static final String INSERT_USER = "insert into users(username, password, first_name, last_name, email) values(:v_username, :v_password," +
            " :v_first_name, :v_last_name, :v_email)";

    private static final String UPDATE_USER = "update users set username = :v_username, password = :v_password, first_name = :v_first_name," +
        " last_name = :v_last_name, email = :v_email where username = :u_name";

    private static final String CHECK_USER = "select username from users where username = :u_name";

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

    @Override
    public void save(User user) {
        try (Connection connection = sql2o.open()) {
            String userName = user.getUserName();
            if(connection.createQuery(CHECK_USER).addParameter("u_name", userName).setColumnMappings(User.COLUMN_MAPPINGS).executeAndFetchFirst(User.class).getId() != null){
                 connection.createQuery(UPDATE_USER, false)
                        .addParameter("v_username", user.getUserName())
                         .addParameter("v_password", user.getPassword())
                         .addParameter("v_first_name", user.getFirstName())
                         .addParameter("v_last_name", user.getLastName())
                         .addParameter("v_email", user.getEmail())
                         .setColumnMappings(User.COLUMN_MAPPINGS)
                         .executeUpdate();
            }else {
                 connection.createQuery(INSERT_USER, true)
                         .addParameter("v_username", user.getUserName())
                         .addParameter("v_password", user.getPassword())
                         .addParameter("v_first_name", user.getFirstName())
                         .addParameter("v_last_name", user.getLastName())
                         .addParameter("v_email", user.getEmail())
                         .setColumnMappings(User.COLUMN_MAPPINGS)
                         .executeUpdate();
            }
        }
    }
}

package com.geekbrains.geekmarketwinter.Providers;

import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.entites.User;
import com.geekbrains.geekmarketwinter.interfaces.IRoleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class RoleProvider implements IRoleProvider {
    private final Sql2o sql2o;

    private static final String SELECT_ROLE = "select * from roles where name = :r_name";

    public RoleProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Role findOneByName(String roleName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ROLE, false)
                    .addParameter("r_name", roleName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
        }
    }
}

package com.geekbrains.geekmarketwinter.providers;

import com.geekbrains.geekmarketwinter.entites.Role;
import com.geekbrains.geekmarketwinter.interfaces.IRoleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class RoleProvider implements IRoleProvider {
    private final Sql2o sql2o;

    private static final String SELECT_ROLE = "select * from roles where name = :r_name";

    private static final String SELECT_ROLE_BY_USER_ID = "select roles.id, roles.name from spring_part_2.roles join users_roles on users_roles.user_id = :u_id and users_roles.role_id = roles.id";

    public RoleProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Role findOneByName(String roleName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ROLE, false)
                    .addParameter("r_name", roleName)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
        }
    }

    @Override
    public List<Role> findByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ROLE_BY_USER_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetch(Role.class);
        }
    }
}

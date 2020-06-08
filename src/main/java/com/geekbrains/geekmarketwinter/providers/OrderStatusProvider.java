package com.geekbrains.geekmarketwinter.providers;

import com.geekbrains.geekmarketwinter.entites.OrderStatus;
import com.geekbrains.geekmarketwinter.interfaces.IOrderStatusProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class OrderStatusProvider implements IOrderStatusProvider {
    private final Sql2o sql2o;

    private static final String SELECT_BY_TITLE = "select * from order_statuses where title = :s_title";

    public OrderStatusProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public OrderStatus findByTitle(String title) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_BY_TITLE, false)
                    .addParameter("s_title", title)
                    .setColumnMappings(OrderStatus.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(OrderStatus.class);
        }
    }
}

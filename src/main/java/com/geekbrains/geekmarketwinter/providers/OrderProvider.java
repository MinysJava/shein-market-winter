package com.geekbrains.geekmarketwinter.providers;

import com.geekbrains.geekmarketwinter.entites.Order;
import com.geekbrains.geekmarketwinter.interfaces.IOrderProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class OrderProvider implements IOrderProvider {
    private final Sql2o sql2o;

    private static final String SELECT_ORDER_QUERY = "select * from orders where id = :o_id";

    private static final String SELECT_ORDER_BY_USER_QUERY = "select * from orders where user_id = :u_id";

    private static final String INSERT_ORDER = "insert into orders(user_id, price, delivery_price, phone_number, status_id, delivery_date) values(:v_user_id, :v_price," +
            " :v_delivery_price, :v_phone_number, :v_status_id, :delivery_date)";

    private static final String UPDATE_ORDER = "update orders set user_id = :v_user_id, price = :v_price, delivery_price = :v_delivery_price," +
            " phone_number = :v_phone_number, status_id = :v_status_id, delivery_date = :delivery_date" +
            " where id = :o_id";

    private static final String CHECK_ORDER = "select id from orders where id = :o_id";


    public OrderProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Order findOrderById(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ORDER_QUERY, false)
                    .addParameter("o_id", id)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Order.class);
        }
    }

    @Override
    public List<Order> findAllByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_ORDER_BY_USER_QUERY, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(Order.COLUMN_MAPPINGS)
                    .executeAndFetch(Order.class);
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = sql2o.open()) {

            if(connection.createQuery(CHECK_ORDER).addParameter("o_id", order.getId()).setColumnMappings(Order.COLUMN_MAPPINGS).executeAndFetchFirst(Order.class).getId() != null){
                connection.createQuery(UPDATE_ORDER, false)
                        .addParameter("o_id", order.getId())
                        .addParameter("v_user_id", order.getUser())
                        .addParameter("v_price", order.getPrice())
                        .addParameter("v_delivery_price", order.getDeliveryPrice())
                        .addParameter("v_phone_number", order.getPhoneNumber())
                        .addParameter("v_status_id", order.getStatus())
                        .addParameter("delivery_date", order.getDeliveryDate())
                        .setColumnMappings(Order.COLUMN_MAPPINGS)
                        .executeUpdate();
            }else {
                connection.createQuery(INSERT_ORDER, true)
                        .addParameter("v_user_id", order.getUser())
                        .addParameter("v_price", order.getPrice())
                        .addParameter("v_delivery_price", order.getDeliveryPrice())
                        .addParameter("v_phone_number", order.getPhoneNumber())
                        .addParameter("v_status_id", order.getStatus())
                        .addParameter("delivery_date", order.getDeliveryDate())
                        .setColumnMappings(Order.COLUMN_MAPPINGS)
                        .executeUpdate();
            }
        }

    }
}

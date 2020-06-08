package com.geekbrains.geekmarketwinter.providers;

import com.geekbrains.geekmarketwinter.entites.OrderItem;
import com.geekbrains.geekmarketwinter.interfaces.IOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Component
public class OrderItemProvider implements IOrderItem {
    private final Sql2o sql2o;

    private static final String INSERT_ORDERITEM = "insert into orders_item(product_id, order_id, quantity, item_price, total_price) values(:v_product_id, :v_order_id," +
            " :v_quantity, :v_item_price, :v_total_price)";

    private static final String UPDATE_ORDERITEM = "update orders_item set product_id = :v_product_id, order_id = :v_order_id, quantity = :v_quantity," +
            " item_price = :v_item_price, total_price = :v_total_price where id = :oi_id";

    private static final String CHECK_ORDERITEM = "select id from orders_item where id = :oi_id";

    public OrderItemProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void save(OrderItem orderItem) {
        try (Connection connection = sql2o.open()) {
            Long orderItem_id = orderItem.getId();
            if(connection.createQuery(CHECK_ORDERITEM).addParameter("oi_id", orderItem_id).setColumnMappings(OrderItem.COLUMN_MAPPINGS).executeAndFetchFirst(OrderItem.class).getId() != null){
                connection.createQuery(UPDATE_ORDERITEM, false)
                        .addParameter("v_product_id", orderItem.getProduct())
                        .addParameter("v_order_id", orderItem.getOrder())
                        .addParameter("v_quantity", orderItem.getQuantity())
                        .addParameter("v_item_price", orderItem.getItemPrice())
                        .addParameter("v_total_price", orderItem.getTotalPrice())
                        .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                        .executeUpdate();
            }else {
                connection.createQuery(INSERT_ORDERITEM, true)
                        .addParameter("v_product_id", orderItem.getProduct())
                        .addParameter("v_order_id", orderItem.getOrder())
                        .addParameter("v_quantity", orderItem.getQuantity())
                        .addParameter("v_item_price", orderItem.getItemPrice())
                        .addParameter("v_total_price", orderItem.getTotalPrice())
                        .setColumnMappings(OrderItem.COLUMN_MAPPINGS)
                        .executeUpdate();
            }
        }

    }
}

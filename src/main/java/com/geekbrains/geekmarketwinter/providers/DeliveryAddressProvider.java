package com.geekbrains.geekmarketwinter.providers;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.interfaces.IDeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Component
public class DeliveryAddressProvider implements IDeliveryAddress {

    private final Sql2o sql2o;

    private static final String SELECT_USER_BY_ID = "select id, user_id, address from delivery_addresses" +
            " where user_id = :u_id";

    public DeliveryAddressProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<DeliveryAddress> findByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_BY_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(DeliveryAddress.COLUMN_MAPPINGS)
                    .executeAndFetch(DeliveryAddress.class);
        }
    }

    @Override
    public DeliveryAddress findFirstByUserId(Long id) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(SELECT_USER_BY_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(DeliveryAddress.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(DeliveryAddress.class);
        }
    }
}

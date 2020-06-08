package com.geekbrains.geekmarketwinter.interfaces;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;

import java.util.List;

public interface IDeliveryAddress {

   List<DeliveryAddress> findByUserId(Long id);

   DeliveryAddress findFirstByUserId(Long id);
}

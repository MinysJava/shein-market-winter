package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.interfaces.IDeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {

    private IDeliveryAddress deliveryAddress;


    @Autowired
    public DeliveryAddressService(IDeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<DeliveryAddress> findByUserId(Long id){
        return deliveryAddress.findByUserId(id);
    }

    public DeliveryAddress findFirstByUserId(Long id) {
        return deliveryAddress.findFirstByUserId(id);
    }
}

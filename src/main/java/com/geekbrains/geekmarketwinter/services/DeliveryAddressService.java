package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.interfaces.IDeliveryAddressProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {

    private IDeliveryAddressProvider deliveryAddressProvider;

    @Autowired
    public void setDeliveryAddress(IDeliveryAddressProvider deliveryAddress) {
        this.deliveryAddressProvider = deliveryAddress;
    }

    public List<DeliveryAddress> findByUserId(Long id){
        return deliveryAddressProvider.findByUserId(id);
    }

    public DeliveryAddress findFirstByUserId(Long id) {
        return deliveryAddressProvider.findFirstByUserId(id);
    }
}

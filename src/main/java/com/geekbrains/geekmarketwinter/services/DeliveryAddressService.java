package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.DeliveryAddress;
import com.geekbrains.geekmarketwinter.repositories.DeliveryAddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAddressService {

    private DeliveryAddressRepository deliveryAddressRepository;

    public DeliveryAddressService(DeliveryAddressRepository deliveryAddressRepository){
        this.deliveryAddressRepository = deliveryAddressRepository;
    }

    public List<DeliveryAddress> findByUserId(Long id){
        return deliveryAddressRepository.findByUserId(id);
    }

    public DeliveryAddress findFirstByUserId(Long id) {
        return deliveryAddressRepository.findFirstByUserId(id);
    }
}

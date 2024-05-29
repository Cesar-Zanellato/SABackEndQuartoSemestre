package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.deliveries.DeliveryInput;
import com.back.fortesupermercados.dtos.deliveries.DeliveryOutput;
import com.back.fortesupermercados.entities.Delivery;
import com.back.fortesupermercados.repositories.DeliveryRepository;

@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository repository;

    @Transactional
    public DeliveryOutput create(DeliveryInput input){
        Delivery delivery = convertInputToDelivery(input);
        delivery = repository.save(delivery);

        return convertDeliveryToOutput(delivery);
    }

    public List<DeliveryOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(delivery -> convertDeliveryToOutput(delivery))
        .toList();
    }

    public DeliveryOutput read(Long id){
        Delivery delivery = repository.findById(id).orElse(null);
        return convertDeliveryToOutput(delivery);
    }

    @Transactional
    public DeliveryOutput update(Long id, DeliveryInput input){
        if(repository.existsById(id)){
            Delivery delivery = convertInputToDelivery(input);
            delivery.setId(id);
            delivery = repository.save(delivery);
            return convertDeliveryToOutput(delivery);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private DeliveryOutput convertDeliveryToOutput(Delivery delivery){
        if(delivery == null){
            return null;
        }
        DeliveryOutput output = new DeliveryOutput(
            delivery.getId(), 
            delivery.getDataTime(), 
            delivery.getDeliveryTime(), 
            delivery.getFeeDelivery(), 
            delivery.getStatus(),
            delivery.getUser()
        );

        return output;
    }

    private Delivery convertInputToDelivery(DeliveryInput input){
        Delivery delivery = new Delivery();
        delivery.setShopping(input.shopping());
        delivery.setDataTime(input.dataTime());
        delivery.setDeliveryTime(input.deliveryTime());
        delivery.setFeeDelivery(input.feeDelivery());
        delivery.setStatus(input.status());
        delivery.setUser(input.user());

        return delivery;
    }
}
package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.address.AddressInput;
import com.back.fortesupermercados.dtos.address.AddressOutput;
import com.back.fortesupermercados.entities.Address;
import com.back.fortesupermercados.repositories.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    AddressRepository repository;

    @Transactional
    public AddressOutput create(AddressInput input){
        Address address = convertInputToAddress(input);
        address = repository.save(address);

        return convertAddressToOutput(address);
    }

    public List<AddressOutput> list(){
        return repository
        .findAll()
        .stream()
        .map(address -> convertAddressToOutput(address))
        .toList();
    }

    public AddressOutput read(Long id){
        Address address = repository.findById(id).orElse(null);
        return convertAddressToOutput(address);
    }

    @Transactional
    public AddressOutput update(Long id, AddressInput input){
        if(repository.existsById(id)){
            Address address = convertInputToAddress(input);
            address.setId(id);
            address = repository.save(address);
            return convertAddressToOutput(address);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private AddressOutput convertAddressToOutput(Address address){
        if(address == null){
            return null;
        }
        AddressOutput output = new AddressOutput(
            address.getId(), 
            address.getNameStreet(), 
            address.getNumberStreet(), 
            address.getNumberHouse(), 
            address.getCep(), 
            address.getPointReference(), 
            address.getComplement()
        );

        return output;
    }

    private Address convertInputToAddress(AddressInput input){
        Address address = new Address();
        address.setNameStreet(input.nameStreet());
        address.setNumberStreet(input.numberStreet());
        address.setNumberHouse(input.numberHouse());
        address.setCep(input.cep());
        address.setPointReference(input.pointReference());
        address.setComplement(input.complement());

        return address;
    }
}
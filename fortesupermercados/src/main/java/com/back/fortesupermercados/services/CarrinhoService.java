package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.carrinhos.CarrinhoEntrada;
import com.back.fortesupermercados.dtos.carrinhos.CarrinhoSaida;
import com.back.fortesupermercados.entities.Carrinho;
import com.back.fortesupermercados.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {
    @Autowired
    CarrinhoRepository repository;

    @Transactional
    public CarrinhoSaida create(CarrinhoEntrada entrada){
        Carrinho carrinho = convertEntradaParaCarrinho(entrada);
        carrinho = repository.save(carrinho);

        return convertCarrinhoParaSaida(carrinho);
    }

    public List<CarrinhoSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(carrinho -> convertCarrinhoParaSaida(carrinho))
        .toList();
    }

    public CarrinhoSaida read(Long id){
        Carrinho carrinho = repository.findById(id).orElse(null);
        return convertCarrinhoParaSaida(carrinho);
    }

    @Transactional
    public CarrinhoSaida update(Long id, CarrinhoEntrada entrada){
        if(repository.existsById(id)){
            Carrinho carrinho = convertEntradaParaCarrinho(entrada);
            carrinho.setId(id);
            carrinho = repository.save(carrinho);
            return convertCarrinhoParaSaida(carrinho);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private CarrinhoSaida convertCarrinhoParaSaida(Carrinho carrinho){
        if(carrinho == null){
            return null;
        }
        CarrinhoSaida saida = new CarrinhoSaida(
            carrinho.getId(), 
            carrinho.getProdutos(), 
            carrinho.getPrecoTotal(), 
            carrinho.getQuantidadeProdutos()
        );

        return saida;
    }

    private Carrinho convertEntradaParaCarrinho(CarrinhoEntrada entrada){
        Carrinho carrinho = new Carrinho();
        carrinho.setProdutos(entrada.produto());
        carrinho.setPrecoTotal(entrada.precoTotal());
        carrinho.setQuantidadeProdutos(entrada.quantidadeProdutos());

        return carrinho;
    }
}
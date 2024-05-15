package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.deliveries.PedidoEntrada;
import com.back.fortesupermercados.dtos.deliveries.PedidoSaida;
import com.back.fortesupermercados.entities.Pedido;
import com.back.fortesupermercados.repositories.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository repository;

    @Transactional
    public PedidoSaida create(PedidoEntrada entrada){
        Pedido pedido = convertEntradaParaPedido(entrada);
        pedido = repository.save(pedido);

        return convertPedidoParaSaida(pedido);
    }

    public List<PedidoSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(pedido -> convertPedidoParaSaida(pedido))
        .toList();
    }

    public PedidoSaida read(Long id){
        Pedido pedido = repository.findById(id).orElse(null);
        return convertPedidoParaSaida(pedido);
    }

    @Transactional
    public PedidoSaida update(Long id, PedidoEntrada entrada){
        if(repository.existsById(id)){
            Pedido pedido = convertEntradaParaPedido(entrada);
            pedido.setId(id);
            pedido = repository.save(pedido);
            return convertPedidoParaSaida(pedido);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private PedidoSaida convertPedidoParaSaida(Pedido pedido){
        if(pedido == null){
            return null;
        }
        PedidoSaida saida = new PedidoSaida(
            pedido.getId(), 
            pedido.getDataCriacao(), 
            pedido.getHoraEntrega(), 
            pedido.getTaxaEntrega(), 
            pedido.getStatus()
        );

        return saida;
    }

    private Pedido convertEntradaParaPedido(PedidoEntrada entrada){
        Pedido pedido = new Pedido();
        pedido.setCarrinho(entrada.carrinho());
        pedido.setDataCriacao(entrada.dataCriacao());
        pedido.setHoraEntrega(entrada.horaEntrega());
        pedido.setTaxaEntrega(entrada.taxaEntrega());
        pedido.setStatus(entrada.status());

        return pedido;
    }
}
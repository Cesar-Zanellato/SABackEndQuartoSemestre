package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.address.EnderecoEntrada;
import com.back.fortesupermercados.dtos.address.EnderecoSaida;
import com.back.fortesupermercados.entities.Endereco;
import com.back.fortesupermercados.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;

    @Transactional
    public EnderecoSaida create(EnderecoEntrada entrada){
        Endereco endereco = convertEntradaParaEndereco(entrada);
        endereco = repository.save(endereco);

        return convertEnderecoParaSaida(endereco);
    }

    public List<EnderecoSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(endereco -> convertEnderecoParaSaida(endereco))
        .toList();
    }

    public EnderecoSaida read(Long id){
        Endereco endereco = repository.findById(id).orElse(null);
        return convertEnderecoParaSaida(endereco);
    }

    @Transactional
    public EnderecoSaida update(Long id, EnderecoEntrada entrada){
        if(repository.existsById(id)){
            Endereco endereco = convertEntradaParaEndereco(entrada);
            endereco.setId(id);
            endereco = repository.save(endereco);
            return convertEnderecoParaSaida(endereco);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private EnderecoSaida convertEnderecoParaSaida(Endereco endereco){
        if(endereco == null){
            return null;
        }
        EnderecoSaida saida = new EnderecoSaida(
            endereco.getId(), 
            endereco.getNomeRua(), 
            endereco.getNumeroRua(), 
            endereco.getNumeroCasa(), 
            endereco.getCep(), 
            endereco.getPontoReferencia(), 
            endereco.getComplemento()
        );

        return saida;
    }

    private Endereco convertEntradaParaEndereco(EnderecoEntrada entrada){
        Endereco endereco = new Endereco();
        endereco.setNomeRua(entrada.nomeRua());
        endereco.setNumeroRua(entrada.numeroRua());
        endereco.setNumeroCasa(entrada.numeroCasa());
        endereco.setCep(entrada.cep());
        endereco.setPontoReferencia(entrada.pontoReferencia());
        endereco.setComplemento(entrada.complemento());

        return endereco;
    }
}
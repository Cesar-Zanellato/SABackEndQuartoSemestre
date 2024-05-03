package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.subcategoria.SubcategoriaEntrada;
import com.back.fortesupermercados.dtos.subcategoria.SubcategoriaSaida;
import com.back.fortesupermercados.entities.Subcategoria;
import com.back.fortesupermercados.repositories.SubcategoriaRepository;

@Service
public class SubcategoriaService {
    @Autowired
    SubcategoriaRepository repository;

    @Transactional
    public SubcategoriaSaida create(SubcategoriaEntrada entrada){
        Subcategoria subcategoria = convertEntradaParaSubcategoria(entrada);
        subcategoria = repository.save(subcategoria);

        return convertSubcategoriaParaSaida(subcategoria);
    }

    public List<SubcategoriaSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(subcategoria -> convertSubcategoriaParaSaida(subcategoria))
        .toList();
    }

    public SubcategoriaSaida read(Long id){
        Subcategoria subcategoria = repository.findById(id).orElse(null);
        return convertSubcategoriaParaSaida(subcategoria);
    }

    @Transactional
    public SubcategoriaSaida update(Long id, SubcategoriaEntrada entrada){
        if(repository.existsById(id)){
            Subcategoria subcategoria = convertEntradaParaSubcategoria(entrada);
            subcategoria.setId(id);
            subcategoria = repository.save(subcategoria);
            return convertSubcategoriaParaSaida(subcategoria);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private SubcategoriaSaida convertSubcategoriaParaSaida(Subcategoria subcategoria){
        if(subcategoria == null){
            return null;
        }
        SubcategoriaSaida saida = new SubcategoriaSaida(
            subcategoria.getId(), 
            subcategoria.getNome()
        );

        return saida;
    }

    private Subcategoria convertEntradaParaSubcategoria(SubcategoriaEntrada entrada){
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setNome(entrada.nome());

        return subcategoria;
    }
}
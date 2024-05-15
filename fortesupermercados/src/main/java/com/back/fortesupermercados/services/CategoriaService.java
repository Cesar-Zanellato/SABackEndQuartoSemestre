package com.back.fortesupermercados.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.fortesupermercados.dtos.categories.CategoriaEntrada;
import com.back.fortesupermercados.dtos.categories.CategoriaSaida;
import com.back.fortesupermercados.entities.Categoria;
import com.back.fortesupermercados.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository repository;

    @Transactional
    public CategoriaSaida create(CategoriaEntrada entrada){
        Categoria categoria = convertEntradaParaCategoria(entrada);
        categoria = repository.save(categoria);

        return convertCategoriaParaSaida(categoria);
    }

    public List<CategoriaSaida> list(){
        return repository
        .findAll()
        .stream()
        .map(categoria -> convertCategoriaParaSaida(categoria))
        .toList();
    }

    public CategoriaSaida read(Long id){
        Categoria categoria = repository.findById(id).orElse(null);
        return convertCategoriaParaSaida(categoria);
    }

    @Transactional
    public CategoriaSaida update(Long id, CategoriaEntrada entrada){
        if(repository.existsById(id)){
            Categoria categoria = convertEntradaParaCategoria(entrada);
            categoria.setId(id);
            categoria = repository.save(categoria);
            return convertCategoriaParaSaida(categoria);
        }else{
            return null;
        }
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    private CategoriaSaida convertCategoriaParaSaida(Categoria categoria){
        if(categoria == null){
            return null;
        }
        CategoriaSaida saida = new CategoriaSaida(
            categoria.getId(), 
            categoria.getNome()
        );

        return saida;
    }

    private Categoria convertEntradaParaCategoria(CategoriaEntrada entrada){
        Categoria categoria = new Categoria();
        categoria.setNome(entrada.nome());

        return categoria;
    }
}
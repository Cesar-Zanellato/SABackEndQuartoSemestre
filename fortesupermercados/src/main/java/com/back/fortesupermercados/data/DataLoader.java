package com.back.fortesupermercados.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.back.fortesupermercados.entities.Categoria;
import com.back.fortesupermercados.entities.Subcategoria;
import com.back.fortesupermercados.repositories.CategoriaRepository;
import com.back.fortesupermercados.repositories.SubcategoriaRepository;

@Component
public class DataLoader implements ApplicationRunner{

    private CategoriaRepository categoriaRepository;
    private SubcategoriaRepository subcategoriaRepository;

    List<String> categorias = List.of(
        "ALIMENTOSBASICOS", "BEBIDAS", "MATINAIS", "HIGIENEPESSOAL", "LIMPEZA", "PADARIA", "HORTIFRUIT", "ACOUGUE"
    );
    List<String> subcategorias = List.of(
        "ACUCAR", "ARROZ", "AZEITE", "FARINHA", "FAROFA", "FEIJAO", "GRAOS", "MASSAS", "OLEO", "OVO",
        "SAL", "VINAGRE", "OUTROS_ALIMENTOS_BASICOS", "AGUA_COM_GAS", "AGUA_SEM_GAS", "AGUA_TONICA",
        "BEBIDAS_DIVERSAS", "ENEGETICO_GARRAFA", "ENEGETICO_LATA", "REFRIGERANTE_DOIS_LITROSORIGINAL",
        "REFRIGERANTE_DOIS_LITROSZERO", "REFRIGERANTE_MEIO_LITROORIGINAL", "REFRIGERANTE_MEIO_LITROZERO",
        "REFRIGERANTE_LATA_ORIGINAL", "REFRIGERANTE_LATA_ZERO", "REFRIGERANTE_PEQUENO_ORIGINAL",
        "REFRIGERANTE_PEQUENO_ZERO", "REFRIGERANTE_UM_E_MEIO_LITRO_ORIGINAL",
        "REFRIGERANTE_UM_E_MEIO_LITRO_ZERO", "REFRIGERANTE_UM_LITRO_ORIGINAL", "REFRIGERANTE_UM_LITRO_ZERO",
        "REFRESCO", "SUCO", "CACHACA", "CERVEJA_GARRAFAO", "CERVEJA_GARRAFAPEQUENA", "CERVEJA_LATA",
        "CERVEJA_LATA_PEQUENA", "CERVEJA_LATAO", "CERVEJA_LATA_ZERO", "CERVEJA_LONG", "CERVEJA_LONG_ZERO",
        "CHOPP", "DESTILADO", "ESPUMANTE", "VINHO", "VINHO_BRANCO", "VINHO_ROSE", "VINHO_TINTO", "VODKA",
        "WHISKY", "AVES", "AVES_CONGELADOS", "BOVINOS", "BOVINOS_CONGELADOS", "CONGELADOS", "FRUTOS_DO_MAR",
        "LINGUICA", "PEIXES", "SALSICHA", "SUINOS", "SUINOS_CONGELADOS", "ABASORVENTE", "APARELHO_DEPILADOR",
        "CONDICIONADOR", "CREME_DENTAL", "CREMES", "DESODORANTE", "ESCOVA_CAPILAR", "ESCOVA_DENTAL",
        "ESPONJA_BANHO", "FRALDA", "HIGIENE_BOCAL", "PAPEL_HIGIENICO", "PRESERVATIVO", "SABONETE_BARRA",
        "SABONETE_LIQUIDO", "SHAMPOO", "TALCOS", "TOALHA_UMEDECIDA", "PRODUTOS_DIVERSOS_HIGIENE", "REFRIGERADO",
        "TEMPERATURA_AMBIENTE", "AGUA_SANITARIA", "ALCOOL_GEL", "ALCOOL_LIQUIDO", "AMACIANTE",
        "DETERGENTE_EM_PO", "DETERGENTE_LIQUIDO", "DESENGORDURANTE", "DESINFETANTE", "DESODORIZADOR",
        "LAVAROUPAS_LIQUIDO", "LIMPADORES_DIVERSOS", "MULTIINSETICIDA", "MULTIUSO", "PANO", "PRODUTOS_CARRO",
        "REPELENTE", "SABAO_EM_BARRA", "SABAO_EM_PO", "SABAO_LIQUIDO", "SACO_LIXO", "UTENCILIOS_PARA_LIMPEZA",
        "PRODUTOS_DIVERSOS_LIMPEZA", "ACHOCOLATADO_EM_PO", "BEBIDALACTEA", "BOLACHAS", "CAFE_A_VACO",
        "CAFE_CAPSULA", "CAFE_GRAOS", "CAFE_SOLUVEL", "CAPPUCCINO", "CEREAL", "CHA", "CHOCOLATES_EM_BARRA",
        "DOCES_E_SOBREMESA", "ERVA_MATE", "GELEIAS_E_PASTAS", "IOGURTE", "LEITE", "LEITE_EM_PO", "LEITE_FERMENTADO",
        "TAPIOCA", "TORRADA", "BAURU_CALABREZA", "BAURU_FRANGORE_QUEIJAO", "BOLO", "BOMBA_SABORES", "COXINHA",
        "CUPCAKE", "ENROLADO_SALSICHA", "FERMENTO", "KIBE", "MINI_COXINHA", "MINI_ENROLADINHO_SALSICHA",
        "MINI_KIBE", "MINI_PASTEL", "MINI_SALGADOS_DIVERSOS", "MINI_CHURROS", "PAES_DIVERSOS", "PAO_BISNAGUINHA",
        "PAO_DE_FORMA", "PAO_DE_LEITE", "PAO_DE_QUEIJO", "PAO_FOLHADO", "PAO_HAMBURGUER", "PAO_HOTDOG",
        "PAO_INTEGRAL", "PAO_TORTILHA", "PASTEL", "RISOLES", "SONHO"
    );
    
    @Autowired
    public DataLoader(SubcategoriaRepository subcategoriaRepository, CategoriaRepository categoriaRepository) {
        this.subcategoriaRepository = subcategoriaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public void run(ApplicationArguments args) {
        Long idSubcategoria = 1L;
        Long idCategoria = 1L;
        for (String categoriaNome: categorias) {
            Categoria categoria = new Categoria();
            categoria.setNome(categoriaNome);
            categoria.setId(idCategoria);
            categoriaRepository.save(categoria);
            idCategoria++;
        }
        for (String subcategoriaNome: subcategorias) {
            Subcategoria subcategoria = new Subcategoria();
            subcategoria.setNome(subcategoriaNome);
            subcategoria.setId(idSubcategoria);
            subcategoriaRepository.save(subcategoria);
            idSubcategoria++;
        }
    }
    
}


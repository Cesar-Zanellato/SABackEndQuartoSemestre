package com.back.fortesupermercados.data;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.back.fortesupermercados.entities.Category;
import com.back.fortesupermercados.entities.Product;
import com.back.fortesupermercados.entities.ProductStock;
import com.back.fortesupermercados.entities.Subcategory;
import com.back.fortesupermercados.repositories.CategoryRepository;
import com.back.fortesupermercados.repositories.ProductRepository;
import com.back.fortesupermercados.repositories.SubcategoryRepository;

@Component
public class DataLoader implements ApplicationRunner{

    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;
    private ProductRepository productRepository;

    @Autowired
    public DataLoader(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    List<String> categories = List.of(
        "ALIMENTOSBASICOS", "BEBIDAS", "MATINAIS", "HIGIENEPESSOAL", "LIMPEZA", "PADARIA", "HORTIFRUIT", "ACOUGUE"
    );

    Map<String, List<String>> subcategoriesMap = Map.of(
        "ALIMENTOSBASICOS", List.of("ACUCAR", "ARROZ", "AZEITE", "FARINHA", "FAROFA", "FEIJAO", "GRAOS", "MASSAS", "OLEO", "OVO", "SAL", "VINAGRE", "OUTROS_ALIMENTOS_BASICOS"),
        "BEBIDAS", List.of("AGUA_COM_GAS", "AGUA_SEM_GAS", "AGUA_TONICA", "BEBIDAS_DIVERSAS", "ENEGETICO_GARRAFA", "ENEGETICO_LATA","REFRIGERANTE_DOIS_LITROSORIGINAL", "REFRIGERANTE_DOIS_LITROSZERO", "REFRIGERANTE_MEIO_LITROORIGINAL", "REFRIGERANTE_MEIO_LITROZERO", "REFRIGERANTE_LATA_ORIGINAL", "REFRIGERANTE_LATA_ZERO", "REFRIGERANTE_PEQUENO_ORIGINAL", "REFRIGERANTE_PEQUENO_ZERO","REFRIGERANTE_UM_E_MEIO_LITRO_ORIGINAL", "REFRIGERANTE_UM_E_MEIO_LITRO_ZERO", "REFRIGERANTE_UM_LITRO_ORIGINAL", "REFRIGERANTE_UM_LITRO_ZERO", "REFRESCO", "SUCO", "CACHACA", "CERVEJA_GARRAFAO", "CERVEJA_GARRAFAPEQUENA", "CERVEJA_LATA", "CERVEJA_LATA_PEQUENA", "CERVEJA_LATAO","CERVEJA_LATA_ZERO", "CERVEJA_LONG", "CERVEJA_LONG_ZERO", "CHOPP", "DESTILADO", "ESPUMANTE", "VINHO", "VINHO_BRANCO", "VINHO_ROSE", "VINHO_TINTO", "VODKA", "WHISKY"),
        "MATINAIS", List.of("ACHOCOLATADO_EM_PO", "BEBIDALACTEA", "BOLACHAS", "CAFE_A_VACO", "CAFE_CAPSULA", "CAFE_GRAOS", "CAFE_SOLUVEL", "CAPPUCCINO", "CEREAL", "CHA", "CHOCOLATES_EM_BARRA", "DOCES_E_SOBREMESA", "ERVA_MATE", "GELEIAS_E_PASTAS", "IOGURTE", "LEITE", "LEITE_EM_PO", "LEITE_FERMENTADO", "TAPIOCA", "TORRADA"),
        "HIGIENEPESSOAL", List.of("ABASORVENTE", "APARELHO_DEPILADOR", "CONDICIONADOR", "CREME_DENTAL", "CREMES", "DESODORANTE", "ESCOVA_CAPILAR", "ESCOVA_DENTAL", "ESPONJA_BANHO", "FRALDA", "HIGIENE_BOCAL", "PAPEL_HIGIENICO", "PRESERVATIVO", "SABONETE_BARRA", "SABONETE_LIQUIDO", "SHAMPOO", "TALCOS", "TOALHA_UMEDECIDA", "PRODUTOS_DIVERSOS_HIGIENE"),
        "LIMPEZA", List.of("AGUA_SANITARIA", "ALCOOL_GEL", "ALCOOL_LIQUIDO", "AMACIANTE", "DETERGENTE_EM_PO", "DETERGENTE_LIQUIDO", "DESENGORDURANTE", "DESINFETANTE", "DESODORIZADOR", "LAVAROUPAS_LIQUIDO", "LIMPADORES_DIVERSOS", "MULTIINSETICIDA", "MULTIUSO", "PANO", "PRODUTOS_CARRO", "REPELENTE", "SABAO_EM_BARRA", "SABAO_EM_PO", "SABAO_LIQUIDO", "SACO_LIXO", "UTENCILIOS_PARA_LIMPEZA", "PRODUTOS_DIVERSOS_LIMPEZA"),
        "PADARIA", List.of("BAURU_CALABREZA", "BAURU_FRANGORE_QUEIJAO", "BOLO", "BOMBA_SABORES", "COXINHA", "CUPCAKE", "ENROLADO_SALSICHA", "FERMENTO", "KIBE", "MINI_COXINHA", "MINI_ENROLADINHO_SALSICHA", "MINI_KIBE", "MINI_PASTEL", "MINI_SALGADOS_DIVERSOS", "MINI_CHURROS", "PAES_DIVERSOS", "PAO_BISNAGUINHA", "PAO_DE_FORMA", "PAO_DE_LEITE", "PAO_DE_QUEIJO", "PAO_FOLHADO", "PAO_HAMBURGUER", "PAO_HOTDOG", "PAO_INTEGRAL", "PAO_TORTILHA", "PASTEL", "RISOLES", "SONHO"),
        "HORTIFRUIT", List.of("REFRIGERADO", "TEMPERATURA_AMBIENTE"),
        "ACOUGUE", List.of("AVES", "AVES_CONGELADOS", "BOVINOS", "BOVINOS_CONGELADOS", "CONGELADOS", "FRUTOS_DO_MAR", "LINGUICA", "PEIXES", "SALSICHA", "SUINOS", "SUINOS_CONGELADOS")
    );           

    @Override
    public void run(ApplicationArguments args) {
        Map<String, Category> categoryMap = new HashMap<>();
        Map<String, Subcategory> subcategoryMap = new HashMap<>();
        ProductStock productStock = new ProductStock();
        productStock.setQuantity(100);

        for (String categoryName : categories) {
            Category category = new Category();
            category.setName(categoryName);
            categoryRepository.save(category);
            categoryMap.put(categoryName, category);

            List<String> subcategories = subcategoriesMap.get(categoryName);
            if (subcategories != null) {
                for (String subcategoryName : subcategories) {
                    Subcategory subcategory = new Subcategory();
                    subcategory.setName(subcategoryName);
                    subcategory.setCategory(category);
                    subcategoryRepository.save(subcategory);
                    subcategoryMap.put(subcategoryName, subcategory);
                }
            }
        }

        List<Product> products = List.of(
            new Product(null, "Arroz Tipo 1 Parboilizado Urbano 1kg", "6.81", "2.04", null, null, "Un", categoryMap.get("ALIMENTOSBASICOS"), subcategoryMap.get("ARROZ"), productStock)
            // new Product(null, "Arroz Branco Tio João 1kg", "11.34", "3.40", null, null, "Un", categoryMap.get("ALIMENTOSBASICOS"), subcategoryMap.get("ARROZ"), null),
            // new Product(null, "Arroz Parbolizado Tio João 1kg", "10.59", "3.17", null, null, "Un", categoryMap.get("ALIMENTOSBASICOS"), subcategoryMap.get("ARROZ"), null),
            // new Product(null, "Arroz Parboilizado Kika Embalagem 1kg", "7.34", "2.20", null, null, "Un", categoryMap.get("ALIMENTOSBASICOS"), subcategoryMap.get("ARROZ"), null),
            // new Product(null, "Arroz Tipo 1 Tio Urbano Embalagem 2kg", "16.68", "5.00", "14.58", null, "Un", categoryMap.get("ALIMENTOSBASICOS"), subcategoryMap.get("ARROZ"), null)
            
        );

        for (Product product : products) {
            productRepository.save(product);
        }
    }
}


package com.back.fortesupermercados.data;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.back.fortesupermercados.entities.Address;
import com.back.fortesupermercados.entities.Category;
import com.back.fortesupermercados.entities.Delivery;
import com.back.fortesupermercados.entities.Product;
import com.back.fortesupermercados.entities.ProductStock;
import com.back.fortesupermercados.entities.Shopping;
import com.back.fortesupermercados.entities.Subcategory;
import com.back.fortesupermercados.entities.User;
import com.back.fortesupermercados.entities.enums.FeeDelivery;
import com.back.fortesupermercados.entities.enums.Status;
import com.back.fortesupermercados.repositories.AddressRepository;
import com.back.fortesupermercados.repositories.CategoryRepository;
import com.back.fortesupermercados.repositories.DeliveryRepository;
import com.back.fortesupermercados.repositories.ProductRepository;
import com.back.fortesupermercados.repositories.ShoppingRepository;
import com.back.fortesupermercados.repositories.SubcategoryRepository;
import com.back.fortesupermercados.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class DataLoader implements ApplicationRunner{

    private CategoryRepository categoryRepository;
    private SubcategoryRepository subcategoryRepository;
    private ProductRepository productRepository;
    private ShoppingRepository shoppingRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;

    @Autowired
    public DataLoader(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository, ProductRepository productRepository, ShoppingRepository shoppingRepository,AddressRepository addressRepository, UserRepository userRepository, DeliveryRepository deliveryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.shoppingRepository = shoppingRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.deliveryRepository = deliveryRepository;

    }

    List<String> categories = List.of(
        "ALIMENTOS_BASICOS", "BEBIDAS", "MATINAIS", "HIGIENE_PESSOAL", "LIMPEZA", "PADARIA", "HORTIFRUIT", "ACOUGUE"
    );

    Map<String, List<String>> subcategoriesMap = new HashMap<>(Map.of(
        "ALIMENTOS_BASICOS", List.of("ACUCAR", "ARROZ", "AZEITE", "FARINHA", "FAROFA", "FEIJAO", "GRAOS", "MASSAS", "OLEO", "OVO", "SAL", "VINAGRE"),
        "BEBIDAS", List.of("AGUA_COM_GAS", "AGUA_SEM_GAS", "AGUA_TONICA", "BEBIDAS_DIVERSAS", "ENEGETICO_GARRAFA", "ENEGETICO_LATA","REFRIGERANTE_DOIS_LITROSORIGINAL", "REFRIGERANTE_DOIS_LITROSZERO", "REFRIGERANTE_MEIO_LITROORIGINAL", "REFRIGERANTE_MEIO_LITROZERO", "REFRIGERANTE_LATA_ORIGINAL", "REFRIGERANTE_LATA_ZERO", "REFRIGERANTE_PEQUENO_ORIGINAL", "REFRIGERANTE_PEQUENO_ZERO","REFRIGERANTE_UM_E_MEIO_LITRO_ORIGINAL", "REFRIGERANTE_UM_E_MEIO_LITRO_ZERO", "REFRIGERANTE_UM_LITRO_ORIGINAL", "REFRIGERANTE_UM_LITRO_ZERO", "REFRESCO", "SUCO", "CACHACA", "CERVEJA_GARRAFAO", "CERVEJA_GARRAFAPEQUENA", "CERVEJA_LATA", "CERVEJA_LATA_PEQUENA", "CERVEJA_LATAO","CERVEJA_LATA_ZERO", "CERVEJA_LONG", "CERVEJA_LONG_ZERO", "CHOPP", "DESTILADO", "ESPUMANTE", "VINHO", "VINHO_BRANCO", "VINHO_ROSE", "VINHO_TINTO", "VODKA", "WHISKY"),
        "MATINAIS", List.of("ACHOCOLATADO_EM_PO", "BEBIDALACTEA", "BOLACHAS", "CAFE_A_VACO", "CAFE_CAPSULA", "CAFE_GRAOS", "CAFE_SOLUVEL", "CAPPUCCINO", "CEREAL", "CHA", "CHOCOLATES_EM_BARRA", "DOCES_E_SOBREMESA", "ERVA_MATE", "GELEIAS_E_PASTAS", "IOGURTE", "LEITE", "LEITE_EM_PO", "LEITE_FERMENTADO", "TAPIOCA", "TORRADA"),
        "HIGIENE_PESSOAL", List.of("ABASORVENTE", "APARELHO_DEPILADOR", "CONDICIONADOR", "CREME_DENTAL", "CREMES", "DESODORANTE", "ESCOVA_CAPILAR", "ESCOVA_DENTAL", "ESPONJA_BANHO", "FRALDA", "HIGIENE_BOCAL", "PAPEL_HIGIENICO", "PRESERVATIVO", "SABONETE_BARRA", "SABONETE_LIQUIDO", "SHAMPOO", "TALCOS", "TOALHA_UMEDECIDA", "PRODUTOS_DIVERSOS_HIGIENE"),
        "LIMPEZA", List.of("AGUA_SANITARIA", "ALCOOL_GEL", "ALCOOL_LIQUIDO", "AMACIANTE", "DETERGENTE_EM_PO", "DETERGENTE_LIQUIDO", "DESENGORDURANTE", "DESINFETANTE", "DESODORIZADOR", "LAVAROUPAS_LIQUIDO", "LIMPADORES_DIVERSOS", "MULTIINSETICIDA", "MULTIUSO", "PANO", "PRODUTOS_CARRO", "REPELENTE", "SABAO_EM_BARRA", "SABAO_EM_PO", "SABAO_LIQUIDO", "SACO_LIXO", "UTENCILIOS_PARA_LIMPEZA", "PRODUTOS_DIVERSOS_LIMPEZA"),
        "PADARIA", List.of("BAURU_CALABREZA", "BAURU_FRANGORE_QUEIJAO", "BOLO", "BOMBA_SABORES", "COXINHA", "CUPCAKE", "ENROLADO_SALSICHA", "FERMENTO", "KIBE", "MINI_COXINHA", "MINI_ENROLADINHO_SALSICHA", "MINI_KIBE", "MINI_PASTEL", "MINI_SALGADOS_DIVERSOS", "MINI_CHURROS", "PAES_DIVERSOS", "PAO_BISNAGUINHA", "PAO_DE_FORMA", "PAO_DE_LEITE", "PAO_DE_QUEIJO", "PAO_FOLHADO", "PAO_HAMBURGUER", "PAO_HOTDOG", "PAO_INTEGRAL", "PAO_TORTILHA", "PASTEL", "RISOLES", "SONHO"),
        "HORTIFRUIT", List.of("REFRIGERADO", "TEMPERATURA_AMBIENTE"),
        "ACOUGUE", List.of("AVES", "AVES_CONGELADOS", "BOVINOS", "BOVINOS_CONGELADOS", "CONGELADOS", "FRUTOS_DO_MAR", "LINGUICA", "PEIXES", "SALSICHA", "SUINOS", "SUINOS_CONGELADOS")
    ));           

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        Map<String, Category> categoryMap = new HashMap<>();
        Map<String, Subcategory> subcategoryMap = new HashMap<>();

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
            new Product(null, "Açúcar União Refinado 1kg", "6.67", null, "Acucar_Uniao_Refinado_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ACUCAR"), null),
            
            new Product(null, "Farinha de Mandioca Yoki 500g", "10.59", null, "Farinha_de_Mandioca_Torrada_Yoki_Pacote_500g", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FAROFA"), null),
            new Product(null, "Arroz Branco Tio João 1kg", "11.34", null, "Arroz_Branco_Tio_Joao_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ARROZ"), null),
            new Product(null, "Massa com Ovos Parafuso Barilla 500g", "5.56", null, "Macarrao_Parafuso_Orquidea_Semola_500g", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("MASSAS"), null),
            new Product(null, "Açúcar Cristal Alto Alegre Pacote 2kg", "10.70", null, "Acucar_Cristal_Alto_Alegre_Pacote_2kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ACUCAR"), null),
            new Product(null, "Arroz Branco Kika 1kg", "7.82", null, "Arroz_Branco_Kika_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ARROZ"), null),
            
            new Product(null, "Azeite Terrano Extra Virgem 500ml", "55.64", null, "Azeite_Terrano_Extra_Virgem_500ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Arroz Branco Kika 5kg", "38.94", null, "Arroz_Branco_Kika_5kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ARROZ"), null),
            new Product(null, "Azeite de Oliva Tipo Unico Gallo 500ml", "66.21", null, "Azeite_de_Oliva_Tipo_Unico_Gallo_500ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Azeite Andorinha 500ml", "66.77", null, "Azeite_Andorinha_500ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Azeite Dende Cepera 100ml", "15.42", null, "Azeite_Dende_Cepera_100ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Azeite de Dendê Hemmer Vidro 200ml", "24.35", null, "Azeite_de_Dende_Hemmer_Vidro_200ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Óleo de Soja Coamo 900ml", "7.02", null, "Oleo_de_Soja_Coamo_900ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            new Product(null, "Açúcar Refinado Caravelas 5kg", "35.79", null, "Acucar_Refinado_Caravelas_5kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ACUCAR"), null),
            new Product(null, "Vinagre de Álcool Heinig 900ml", "3.52", null, "Vinagre_de_Alcool_Heinig_900ml", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("AZEITE"), null),
            
            new Product(null, "Farinha de Trigo Orquídea 1kg", "5.34", null, "Farinha_de_Trigo_Orquidea_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FARINHA"), null),
            new Product(null, "Farinha de Arroz Urbano 1kg", "8.14", null, "Farinha_de_Arroz_Urbano_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FARINHA"), null),
            new Product(null, "Farinha de Rosca Marsala 500g", "10.45", null, "Farinha_de_Rosca_Marsala_500g", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FARINHA"), null),
            new Product(null, "Açúcar Demerara Naturale União 1kg", "8.24", null, "Acucar_Demerara_Naturale_Uniao_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ACUCAR"), null),
            
            new Product(null, "Arroz Tipo 1 Tio Urbano Embalagem 1kg", "16.68", null, "Arroz_Branco_Tipo_1_Tio_Urbano_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ARROZ"), null),
            new Product(null, "Arroz Parboilizado Kika Embalagem 1kg", "7.34", null, "Arroz_Parboilizado_Kika_Embalagem_1kg", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("ARROZ"), null),
            new Product(null, "Farofa de Soja Pinduca Embalagem 250g", "5.13", null, "Farofa_de_Soja_Pinduca_Embalagem_250g", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FAROFA"), null),
            new Product(null, "Farofa de Milho Pinduca 400g", "7.23", null, "Farofa_de_Milho_Pinduca_400g", "Un", categoryMap.get("ALIMENTOS_BASICOS"), subcategoryMap.get("FAROFA"), null),
            new Product(null, "Farofa Kanty Suave 250g", "5.51", null, "Farofa_Kanty_Suave_250g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FAROFA"), null),
            new Product(null, "Farofa Kanty Calabresa 250g", "5.51", null, "Farofa_Kanty_Calabresa_250g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FAROFA"), null),
            new Product(null, "Farofa Kanty Picanha 250g", "5.51", null, "Farofa_Kanty_Picanha_250g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FAROFA"), null),
            
            new Product(null, "Feijão Preto Rico Caldo 1kg", "7.67", null, "Feijao_Preto_Rico_Caldo_1kg", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FEIJAO"), null),
            new Product(null, "Feijão Carioca Rico Caldo 1kg", "10.03", null, "Feijao_Carioca_Rico_Caldo_1kg", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FEIJAO"), null),
            new Product(null, "Feijão Vermelho Rico Caldo 1kg", "14.79", null, "Feijao_Vermelho_Rico_Caldo_1kg", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FEIJAO"), null),
            new Product(null, "Feijão Branco Sinhá 500g", "13.70", null, "Feijao_Branco_Sinha_500g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("FEIJAO"), null),
            
            new Product(null, "Milho para Pipoca Kanty 400g", "4.04", null, "Milho_para_Pipoca_Kanty_400g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            new Product(null, "Canjica Branca Kanty 400g", "6.56", null, "Canjica_Branca_Kanty_400g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            new Product(null, "Açúcar Refinado Alto Alegre Pacote 1kg", "5.55", null, "Acucar_Refinado_Alto_Alegre_Pacote_1kg", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("ACUCAR"), null),
            new Product(null, "Milho para Pipoca Tipo 1 Pacote Yoki 400g", "6.14", null, "Milho_para_Pipoca_Tipo_1_Pacote_Yoki_400g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            new Product(null, "Canjica Amarela Yoki 500g", "10.03", null, "Canjica_Amarela_Yoki_500g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            new Product(null, "Lentilha Canadense Kanty 400g", "10.70", null, "Lentilha_Canadense_Kanty_400g", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            new Product(null, "Lentilha Yoki 500g", "11.24", null, "Lentilha_Yoki_500", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("GRAOS"), null),
            
            new Product(null, "Arroz Parbolizado Tio João 1kg", "10.59", null, "Arroz_Parbolizado_Tio_Joao_1kg", "Un", categoryMap.get("MATINAIS"), subcategoryMap.get("ARROZ"), null)
        );

        productRepository.saveAll(products);

        products.forEach(product -> {
            ProductStock productStock = new ProductStock(null, 100, product);
            product.setProductStock(productStock);
            productRepository.save(product);
        });
    }
}


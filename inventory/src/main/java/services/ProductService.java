package services;

import entities.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> listProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public boolean checkAvailability(Long id, Integer quantity) {
        Optional<Product> product = productRepository.findById(id);
        Product addproduct;

        if (product.isPresent()) {
            addproduct = product.get();
            return addproduct.getAmountInStock() >= quantity;
        }

        return false;
    }

    @Transactional
    public Boolean updateStock(Long id, Integer quantity) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int newAmount = product.getAmountInStock() - quantity;

            if (newAmount >= 0) {
                product.setAmountInStock(newAmount);
                productRepository.save(product);

                return true;
            }
        }
        return false;
    }
}

package repository.interfacial;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import entity.Product;

public interface ProductRepository extends AutoCloseable {

	public Boolean registerProduct(Product newProduct);

	public Optional<Product> findProductByName(String name);

	public List<Product> findProductByPrice(BigDecimal price);

	public List<Product> listAllProducts();

	public Boolean updateProductInfo(Product updatedProduct, String originalName);

	public Boolean removeRegisteredProduct(String name);

}

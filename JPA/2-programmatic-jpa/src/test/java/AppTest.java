import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import entity.Product;
import factory.implementation.HibernateEntityManagerBuilder;
import repository.implementation.ProductJPARepository;
import repository.interfacial.ProductRepository;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static ProductRepository repository = null;
	private static Product newProduct = null;

	@BeforeAll
	public static void init() {

		repository = new ProductJPARepository(new HibernateEntityManagerBuilder().getEntityManager());

		newProduct = new Product("VIDEOGAME", new BigDecimal(5000));

	}

	@AfterAll
	public static void terminate() {

		repository.close();

	}

	@Test
	@Order(1)
	public void should_register_a_new_product() {

		Assertions.assertTrue(repository.registerProduct(newProduct));

	}

	@Test
	@Order(2)
	public void should_find_a_registered_product_by_its_name() {

		Optional<Product> result = repository.findProductByName("VIDEOGAME");

		Assertions.assertTrue(result.isPresent());

		Product foundProduct = result.get();

		Assertions.assertEquals(newProduct.getName(), foundProduct.getName());

		Assertions.assertEquals(0, newProduct.getPrice().compareTo(foundProduct.getPrice()));

	}

	@Test
	@Order(3)
	public void should_list_all_registered_product_with_the_same_price() {

		repository.registerProduct(new Product("TV", newProduct.getPrice()));

		List<Product> productList = repository.findProductByPrice(new BigDecimal(5000));

		Assertions.assertFalse(productList.isEmpty());

		Assertions.assertEquals(2, productList.size());

	}

	@Test
	@Order(4)
	public void should_list_all_registered_product() {

		repository.registerProduct(new Product("COMPUTER", new BigDecimal(3500)));

		List<Product> productList = repository.listAllProducts();

		Assertions.assertFalse(productList.isEmpty());

		Assertions.assertEquals(5, productList.size());

	}

	@Test
	@Order(5)
	public void should_update_the_information_of_a_registered_product() {

		String originalName = newProduct.getName();

		newProduct = new Product("PLAYSTATION", new BigDecimal(5500));

		Assertions.assertTrue(repository.updateProductInfo(newProduct, originalName));

		Optional<Product> result = repository.findProductByName(newProduct.getName());

		Assertions.assertTrue(result.isPresent());

		Product foundProduct = result.get();

		Assertions.assertEquals(newProduct.getName(), foundProduct.getName());

		Assertions.assertEquals(0, newProduct.getPrice().compareTo(foundProduct.getPrice()));

	}

	@Test
	@Order(6)
	public void should_remove_the_register_of_a_product() {

		Assertions.assertTrue(repository.removeRegisteredProduct(newProduct.getName()));

		Optional<Product> result = repository.findProductByName(newProduct.getName());

		Assertions.assertTrue(result.isEmpty());

		List<Product> productList = repository.listAllProducts();

		Assertions.assertEquals(4, productList.size());

	}

}

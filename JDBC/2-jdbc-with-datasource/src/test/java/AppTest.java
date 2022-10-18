import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import factory.ConnectionFactory;
import repository.implementation.ProductJDBCRepository;
import repository.interfacial.ProductRepository;

@TestMethodOrder(OrderAnnotation.class)
class AppTest {

	private static Connection connection = null;
	private static ProductRepository repository = null;
	private static Product newProduct = null;

	@BeforeAll
	public static void init() {

		connection = ConnectionFactory.getConnection();

		repository = new ProductJDBCRepository();

		newProduct = new Product("VIDEOGAME", new BigDecimal(5000));

	}

	@AfterAll
	public static void terminate() {

		try {

			connection.close();

			repository.close();

		} catch (Exception e) {

			Assertions.fail();

		}

	}

	private String readFile(String filePath) {

		StringBuffer fileContent = null;

		try (InputStream fileStream = new FileInputStream(filePath)) {

			try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileStream, "UTF-8"))) {

				fileContent = new StringBuffer();

				String readedLine;

				while ((readedLine = fileReader.readLine()) != null) {

					fileContent.append(readedLine);

				}

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

		return fileContent.toString();

	}

	@Test
	@Order(1)
	public void the_connection_should_be_valid() {

		Assertions.assertNotNull(connection);

		try {

			Assertions.assertTrue(connection.isValid(0));

		} catch (SQLException e) {

			Assertions.fail();

		}

	}

	@Test
	@Order(2)
	public void the_product_table_should_be_able_to_be_created() {

		String sql = readFile(AppTest.class.getResource("/script/sql/table-creation.sql").getPath());

		try (PreparedStatement query = connection.prepareStatement(sql)) {

			query.executeUpdate();

			connection.commit();

		} catch (SQLException e) {

			Assertions.fail();

		}

		sql = readFile(AppTest.class.getResource("/script/sql/primary-inserts.sql").getPath());

		try (PreparedStatement query = connection.prepareStatement(sql)) {

			Integer hadEfect = query.executeUpdate();

			connection.commit();

			Assertions.assertEquals(1, hadEfect);

		} catch (SQLException e) {

			Assertions.fail();

		}

	}

	@Test
	@Order(3)
	public void should_register_a_new_product() {

		Assertions.assertTrue(repository.registerProduct(newProduct));

	}

	@Test
	@Order(4)
	public void should_find_a_registered_product_by_its_name() {

		Optional<Product> result = repository.findProductByName("VIDEOGAME");

		Assertions.assertTrue(result.isPresent());

		Product foundProduct = result.get();

		Assertions.assertEquals(newProduct.getName(), foundProduct.getName());

		Assertions.assertEquals(0, newProduct.getPrice().compareTo(foundProduct.getPrice()));

	}

	@Test
	@Order(5)
	public void should_list_all_registered_product_with_the_same_price() {

		repository.registerProduct(new Product("TV", newProduct.getPrice()));

		List<Product> productList = repository.findProductByPrice(new BigDecimal(5000));

		Assertions.assertFalse(productList.isEmpty());

		Assertions.assertEquals(2, productList.size());

	}

	@Test
	@Order(6)
	public void should_list_all_registered_product() {

		repository.registerProduct(new Product("COMPUTER", new BigDecimal(3500)));

		List<Product> productList = repository.listAllProducts();

		Assertions.assertFalse(productList.isEmpty());

		Assertions.assertEquals(5, productList.size());

	}

	@Test
	@Order(7)
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
	@Order(8)
	public void should_remove_the_register_of_a_product() {

		Assertions.assertTrue(repository.removeRegisteredProduct(newProduct.getName()));

		Optional<Product> result = repository.findProductByName(newProduct.getName());

		Assertions.assertTrue(result.isEmpty());

		List<Product> productList = repository.listAllProducts();

		Assertions.assertEquals(4, productList.size());

	}

}

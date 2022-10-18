package repository.implementation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import entity.Product;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import repository.interfacial.ProductRepository;
import util.Defaults;
import util.converter.implementation.ProductJDBCConverter;

@Dependent
public class ProductJDBCRepository implements ProductRepository {

	private Connection connection = null;
	private ProductJDBCConverter entityConverter = null;

	@Inject
	public ProductJDBCRepository(Connection connection, ProductJDBCConverter entityConverter) {

		this.connection = connection;

		this.entityConverter = entityConverter;

	}

	@Override
	public Boolean registerProduct(Product newProduct) {

		StringBuffer sql = new StringBuffer();
		Integer hadEffect = null;

		sql.append("INSERT INTO product(name, price) ").append("VALUES(?, ?)");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString())) {

			query.setString(1, newProduct.getName());
			query.setBigDecimal(2, newProduct.getPrice());

			hadEffect = query.executeUpdate();

			this.connection.commit();

		} catch (SQLException e) {

			try {

				this.connection.rollback();

			} catch (SQLException ex) {

				e.setNextException(ex);

			}

			throw new RuntimeException("The product could not be registered.", e);

		}

		if (hadEffect > 0) {

			return Boolean.TRUE;

		}

		return Boolean.FALSE;

	}

	@Override
	public Optional<Product> findProductByName(String name) {

		StringBuffer sql = new StringBuffer();
		Optional<Product> searchResult = null;

		sql.append("SELECT p.name AS ").append(ProductJDBCConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJDBCConverter.PRICE_ALIAS).append(" FROM product p ").append("WHERE p.name = ?");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			query.setString(1, name);

			try (ResultSet queryResult = query.executeQuery()) {

				if (!(queryResult.next())) {

					return Defaults.getEmptyOptional();

				}

				searchResult = Optional.of(this.entityConverter.toEntity(queryResult));

			}

		} catch (SQLException e) {

			throw new RuntimeException("The search for the product could not be executed.", e);

		}

		return searchResult;

	}

	@Override
	public List<Product> findProductByPrice(BigDecimal price) {

		StringBuffer sql = new StringBuffer();
		List<Product> products = null;

		sql.append("SELECT p.name AS ").append(ProductJDBCConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJDBCConverter.PRICE_ALIAS).append(" FROM product p ").append("WHERE p.price = ?");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			query.setString(1, price.toPlainString());

			try (ResultSet queryResult = query.executeQuery()) {

				if (!(queryResult.next())) {

					return Defaults.getEmptyList();

				}

				queryResult.previous();

				products = this.entityConverter.toEntities(queryResult);

			}

		} catch (SQLException e) {

			throw new RuntimeException("The product list could not be created.", e);

		}

		return products;

	}

	@Override
	public List<Product> listAllProducts() {

		StringBuffer sql = new StringBuffer();
		List<Product> products = null;

		sql.append("SELECT p.name AS ").append(ProductJDBCConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJDBCConverter.PRICE_ALIAS).append(" FROM product p");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString(),
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			try (ResultSet queryResult = query.executeQuery()) {

				if (!(queryResult.next())) {

					return Defaults.getEmptyList();

				}

				queryResult.previous();

				products = this.entityConverter.toEntities(queryResult);

			}

		} catch (SQLException e) {

			throw new RuntimeException("The product list could not be created.", e);

		}

		return products;

	}

	@Override
	public Boolean updateProductInfo(Product updatedProduct, String originalName) {

		StringBuffer sql = new StringBuffer();
		Integer hadEffect = null;

		sql.append("UPDATE product p ").append("SET p.name = ?, p.price = ? ").append("WHERE p.name = ?");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString())) {

			query.setString(1, updatedProduct.getName());
			query.setBigDecimal(2, updatedProduct.getPrice());
			query.setString(3, originalName);

			hadEffect = query.executeUpdate();

			this.connection.commit();

		} catch (SQLException e) {

			try {

				this.connection.rollback();

			} catch (SQLException ex) {

				e.setNextException(ex);

			}

			throw new RuntimeException("The product register could not be updated.", e);

		}

		if (hadEffect > 0) {

			return Boolean.TRUE;

		}

		return Boolean.FALSE;

	}

	@Override
	public Boolean removeRegisteredProduct(String name) {

		StringBuffer sql = new StringBuffer();
		Integer hadEffect = null;

		sql.append("DELETE FROM product p ").append("WHERE p.name = ?");

		try (PreparedStatement query = this.connection.prepareStatement(sql.toString())) {

			query.setString(1, name);

			hadEffect = query.executeUpdate();

			this.connection.commit();

		} catch (SQLException e) {

			try {

				this.connection.rollback();

			} catch (SQLException ex) {

				e.setNextException(ex);

			}

			throw new RuntimeException("The product could not be removed from the registers.", e);

		}

		if (hadEffect > 0) {

			return Boolean.TRUE;

		}

		return Boolean.FALSE;

	}

	@Override
	public void close() throws SQLException {

		this.connection.close();

	}

}

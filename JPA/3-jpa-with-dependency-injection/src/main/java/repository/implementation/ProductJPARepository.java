package repository.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import entity.Product;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Tuple;
import repository.interfacial.ProductRepository;
import util.Defaults;
import util.converter.implementation.ProductJPAConverter;

@Dependent
public class ProductJPARepository implements ProductRepository {

	private EntityManager connection = null;
	private ProductJPAConverter entityConverter = null;

	@Inject
	public ProductJPARepository(EntityManager connection, ProductJPAConverter entityConverter) {

		this.connection = connection;

		this.entityConverter = entityConverter;

	}

	@Override
	public Boolean registerProduct(Product newProduct) {

		StringBuffer sql = new StringBuffer();
		Integer hadEffect = null;

		sql.append("INSERT INTO product(name, price) ").append("VALUES(:pName, :pPrice)");

		try {

			this.connection.getTransaction().begin();

			hadEffect = this.connection.createNativeQuery(sql.toString()).setParameter("pName", newProduct.getName())
					.setParameter("pPrice", newProduct.getPrice()).executeUpdate();

			this.connection.getTransaction().commit();

		} catch (PersistenceException e) {

			this.connection.getTransaction().rollback();

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
		Optional<Product> foundProduct = null;

		sql.append("SELECT p.name AS ").append(ProductJPAConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJPAConverter.PRICE_ALIAS).append(" FROM product p ").append("WHERE p.name = :pName");

		try {

			Tuple searchResult = (Tuple) this.connection.createNativeQuery(sql.toString(), Tuple.class)
					.setParameter("pName", name).getSingleResult();

			foundProduct = Optional.of(entityConverter.toEntity(searchResult));

		} catch (NoResultException e) {

			foundProduct = Defaults.getEmptyOptional();

		} catch (PersistenceException e) {

			throw new RuntimeException("The search for the product could not be executed.", e);

		}

		return foundProduct;

	}

	@Override
	public List<Product> findProductByPrice(BigDecimal price) {

		StringBuffer sql = new StringBuffer();
		List<Product> products = null;

		sql.append("SELECT p.name AS ").append(ProductJPAConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJPAConverter.PRICE_ALIAS).append(" FROM product p ").append("WHERE p.price = :pPrice");

		try {

			List<?> searchResult = this.connection.createNativeQuery(sql.toString(), Tuple.class)
					.setParameter("pPrice", price).getResultList();

			products = entityConverter.toEntities(searchResult);

		} catch (PersistenceException e) {

			throw new RuntimeException("The search for the product(s) could not be executed.", e);

		}

		return products;

	}

	@Override
	public List<Product> listAllProducts() {

		StringBuffer sql = new StringBuffer();
		List<Product> products = null;

		sql.append("SELECT p.name AS ").append(ProductJPAConverter.NAME_ALIAS).append(", p.price AS ")
				.append(ProductJPAConverter.PRICE_ALIAS).append(" FROM product p");

		try {

			List<?> searchResult = this.connection.createNativeQuery(sql.toString(), Tuple.class).getResultList();

			products = entityConverter.toEntities(searchResult);

		} catch (PersistenceException e) {

			throw new RuntimeException("The product list could not be created.", e);

		}

		return products;

	}

	@Override
	public Boolean updateProductInfo(Product updatedProduct, String originalName) {

		StringBuffer sql = new StringBuffer();
		Integer hadEffect = null;

		sql.append("UPDATE product p ").append("SET p.name = :pName, p.price = :pPrice ")
				.append("WHERE p.name = :pOriginalName");

		try {

			this.connection.getTransaction().begin();

			hadEffect = this.connection.createNativeQuery(sql.toString())
					.setParameter("pName", updatedProduct.getName()).setParameter("pPrice", updatedProduct.getPrice())
					.setParameter("pOriginalName", originalName).executeUpdate();

			this.connection.getTransaction().commit();

		} catch (PersistenceException e) {

			this.connection.getTransaction().rollback();

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

		sql.append("DELETE FROM product p ").append("WHERE p.name = :pName");

		try {

			this.connection.getTransaction().begin();

			hadEffect = this.connection.createNativeQuery(sql.toString()).setParameter("pName", name).executeUpdate();

			this.connection.getTransaction().commit();

		} catch (PersistenceException e) {

			this.connection.getTransaction().rollback();

			throw new RuntimeException("The product could not be removed from the registers.", e);

		}

		if (hadEffect > 0) {

			return Boolean.TRUE;

		}

		return Boolean.FALSE;

	}

	@Override
	public void close() {

		this.connection.close();

	}

}

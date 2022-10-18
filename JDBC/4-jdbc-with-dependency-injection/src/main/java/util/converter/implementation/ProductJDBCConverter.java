package util.converter.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import jakarta.enterprise.context.Dependent;
import util.converter.interfacial.EntityConverter;

@Dependent
public class ProductJDBCConverter implements EntityConverter<ResultSet, Product> {

	public static final String NAME_ALIAS = "productName";
	public static final String PRICE_ALIAS = "productPrice";

	@Override
	public Product toEntity(ResultSet queryResult) throws SQLException {

		return new Product(queryResult.getString(NAME_ALIAS), queryResult.getBigDecimal(PRICE_ALIAS));

	}

	@Override
	public List<Product> toEntities(ResultSet queryResult) throws SQLException {

		List<Product> products = new ArrayList<Product>();

		while (queryResult.next()) {

			products.add(new Product(queryResult.getString(PRICE_ALIAS), queryResult.getBigDecimal(PRICE_ALIAS)));

		}

		return products;

	}

}

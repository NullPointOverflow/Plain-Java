package util.converter.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.Tuple;
import util.Defaults;
import util.converter.interfacial.EntityCollectionConverter;
import util.converter.interfacial.EntityConverter;

@Dependent
public class ProductJPAConverter
		implements EntityConverter<Tuple, Product>, EntityCollectionConverter<List<?>, Product> {

	public static final String NAME_ALIAS = "productName";
	public static final String PRICE_ALIAS = "productPrice";

	@Override
	public Product toEntity(Tuple pseudoProduct) {

		String name = pseudoProduct.get(NAME_ALIAS, String.class);
		BigDecimal price = pseudoProduct.get(PRICE_ALIAS, BigDecimal.class);

		return new Product(name, price);

	}

	@Override
	public List<Product> toEntities(List<?> pseudoProducts) {

		if (pseudoProducts.size() == 0) {

			return Defaults.getEmptyList();

		}

		List<Product> products = new ArrayList<Product>(pseudoProducts.size());

		for (Object pseudoProduct : pseudoProducts) {

			Tuple tupleProduct = (Tuple) pseudoProduct;

			String name = tupleProduct.get(NAME_ALIAS, String.class);
			BigDecimal price = tupleProduct.get(PRICE_ALIAS, BigDecimal.class);

			products.add(new Product(name, price));

		}

		return products;

	}

}

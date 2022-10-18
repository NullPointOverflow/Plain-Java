package entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Deprecated
	@SuppressWarnings("unused")
	private Product() {
	}

	public Product(String name, BigDecimal price) {

		this.name = name;

		this.price = price;

	}

	public String getName() {

		return this.name;

	}

	public BigDecimal getPrice() {

		return this.price;

	}

}

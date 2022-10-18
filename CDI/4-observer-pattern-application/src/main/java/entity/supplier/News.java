package entity.supplier;

import java.util.Objects;
import java.util.function.Supplier;

public class News implements Supplier<String> {

	private String text;

	private News(String text) {

		this.text = text;

	}
	
	public static News of(String text) {
		
		return new News(Objects.requireNonNull(text));
		
	}

	@Override
	public String get() {

		return this.text;

	}

}

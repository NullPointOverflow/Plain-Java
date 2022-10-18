package supplier;

import java.util.function.Supplier;

import annotation.Timed;
import jakarta.enterprise.context.Dependent;

@Dependent
public class FastSupplier implements Supplier<String> {
	
	@Timed
	@Override
	public String get() {

		return "FAST RESPONSE.";

	}

}

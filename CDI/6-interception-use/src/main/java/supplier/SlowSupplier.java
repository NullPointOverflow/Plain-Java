package supplier;

import java.util.function.Supplier;

import annotation.Timed;
import jakarta.enterprise.context.Dependent;

@Dependent
public class SlowSupplier implements Supplier<String> {

	@Timed
	@Override
	public String get() {

		try {

			Thread.sleep(200L);

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

		return "SLOW RESPONSE.";

	}

}

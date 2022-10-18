package implementation;

import interfacial.Vehicle;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Car implements Vehicle {

	@Override
	public String move() {

		return "DRIVING...";

	}

}

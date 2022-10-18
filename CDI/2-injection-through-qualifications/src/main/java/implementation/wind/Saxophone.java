package implementation.wind;

import annotation.Instrument;
import interfacial.WindInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

@Dependent
@Alternative
@Instrument(WindInstrument.class)
public class Saxophone implements WindInstrument {

	@Override
	public String sound() {

		return "SAXOPHONE SOUND...";

	}

}

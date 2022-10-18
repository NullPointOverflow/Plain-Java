package implementation.wind;

import annotation.Instrument;
import interfacial.WindInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
@Default
@Instrument(WindInstrument.class)
public class Trumpet implements WindInstrument {

	@Override
	public String sound() {

		return "TRUMPET SOUND...";

	}

}

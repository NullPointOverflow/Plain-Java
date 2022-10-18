package implementation.string;

import annotation.Instrument;
import interfacial.StringInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
@Default
@Instrument(StringInstrument.class)
public class Violin implements StringInstrument {

	@Override
	public String sound() {

		return "VIOLIN SOUND...";

	}

}

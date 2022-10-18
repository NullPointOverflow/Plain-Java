package implementation.string;

import annotation.Instrument;
import interfacial.StringInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

@Dependent
@Alternative
@Instrument(StringInstrument.class)
public class Piano implements StringInstrument {

	@Override
	public String sound() {

		return "PIANO SOUND...";

	}

}

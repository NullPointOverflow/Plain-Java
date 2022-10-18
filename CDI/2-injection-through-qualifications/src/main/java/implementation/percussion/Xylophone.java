package implementation.percussion;

import annotation.Instrument;
import interfacial.PercussionInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
@Default
@Instrument(PercussionInstrument.class)
public class Xylophone implements PercussionInstrument {

	@Override
	public String sound() {

		return "XYLOPHONE SOUND...";

	}

}

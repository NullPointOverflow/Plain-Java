package implementation.percussion;

import annotation.Instrument;
import interfacial.PercussionInstrument;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

@Dependent
@Alternative
@Instrument(PercussionInstrument.class)
public class Bell implements PercussionInstrument {

	@Override
	public String sound() {

		return "BELL SOUND...";

	}

}

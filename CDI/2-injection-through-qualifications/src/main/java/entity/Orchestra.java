package entity;

import java.util.stream.Collectors;

import annotation.Instrument;
import interfacial.MusicalInstrument;
import interfacial.PercussionInstrument;
import interfacial.StringInstrument;
import interfacial.WindInstrument;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@ApplicationScoped
public class Orchestra {

	@Inject
	@Alternative
	@Instrument(StringInstrument.class)
	private MusicalInstrument string;

	@Inject
	@Alternative
	@Instrument(PercussionInstrument.class)
	private MusicalInstrument percussion;

	@Inject
	@Alternative
	@Instrument(WindInstrument.class)
	private MusicalInstrument wind;

	@Inject
	private StringInstrument stringSolo;

	@Inject
	private PercussionInstrument percussionSolo;

	@Inject
	private WindInstrument windSolo;

	@Inject
	private Instance<MusicalInstrument> instruments;

	public String playString() {

		return this.string.sound();

	}

	public String playPercussion() {

		return this.percussion.sound();

	}

	public String playWind() {

		return this.wind.sound();

	}

	public String playStringSolo() {

		return this.stringSolo.sound();

	}

	public String playPercussionSolo() {

		return this.percussionSolo.sound();

	}

	public String playWindSolo() {

		return this.windSolo.sound();

	}

	public String playAllTogether() {

		String sounds = this.instruments.stream().map(MusicalInstrument::sound).collect(Collectors.joining(", "));

		return sounds;

	}

}

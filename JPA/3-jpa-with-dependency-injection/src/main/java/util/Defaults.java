package util;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Defaults {

	public static final <T> Optional<T> getEmptyOptional() {

		return Optional.empty();

	}

	public static final <T> List<T> getEmptyList() {

		return Collections.emptyList();

	}

}

package implementation;

import interfacial.Worker;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;

@Dependent
@Alternative
public class Programmer implements Worker {

	@Override
	public String work(String job) {

		StringBuffer resultado = new StringBuffer();

		resultado.append(System.lineSeparator()).append("DAILY REPORT: Programmer").append(System.lineSeparator())
				.append("REQUESTED JOB: \"" + job + "\"").append(System.lineSeparator()).append("STATUS: ")
				.append(System.lineSeparator()).append("- System debug: DONE.").append(System.lineSeparator())
				.append("- Create new functions for the system: DONE.").append(System.lineSeparator());

		return resultado.toString();

	}

}

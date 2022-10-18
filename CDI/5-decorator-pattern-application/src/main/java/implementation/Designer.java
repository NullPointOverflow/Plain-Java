package implementation;

import interfacial.Worker;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;

@Dependent
@Default
public class Designer implements Worker {

	@Override
	public String work(String job) {

		StringBuffer resultado = new StringBuffer();

		resultado.append(System.lineSeparator()).append("DAILY REPORT: Designer").append(System.lineSeparator())
				.append("REQUESTED JOB: \"" + job + "\"").append(System.lineSeparator()).append("STATUS: ")
				.append(System.lineSeparator()).append("- Brainstorm for new ideas of design: DONE.")
				.append(System.lineSeparator()).append("- Create a new design for the company site: DONE.\"")
				.append(System.lineSeparator());

		return resultado.toString();

	}

}

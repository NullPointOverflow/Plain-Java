package implementation;

import interfacial.Worker;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;

@Decorator
@Priority(Interceptor.Priority.APPLICATION)
public class Manager implements Worker {

	@Inject
	@Delegate
	@Any
	private Worker worker;

	@Override
	public String work(String job) {

		StringBuffer resultado = new StringBuffer();

		resultado.append(System.lineSeparator()).append("DAILY REPORT: Manager").append(System.lineSeparator())
				.append("REQUESTED JOB: \"" + job + "\"").append(System.lineSeparator()).append("STATUS: ")
				.append(System.lineSeparator()).append("- Delegate the job to others workers: DONE.")
				.append(System.lineSeparator());

		return resultado.toString() + worker.work(job);

	}

}

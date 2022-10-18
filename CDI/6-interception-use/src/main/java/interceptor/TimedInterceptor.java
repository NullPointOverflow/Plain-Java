package interceptor;

import java.nio.file.Files;
import java.nio.file.Paths;

import annotation.Timed;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
@Timed
public class TimedInterceptor {

	private static final String LOG_FILE = "log.txt";

	@AroundInvoke
	public Object auditMethod(InvocationContext context) {

		Object result = null;
		StringBuffer textualResult = null;

		try {

			long begin = System.currentTimeMillis();

			result = context.proceed();

			long end = System.currentTimeMillis() - begin;

			textualResult = new StringBuffer();

			textualResult.append("Audit result:").append(System.lineSeparator())
					.append("- Class: " + context.getTarget().getClass()).append(System.lineSeparator())
					.append("- Invocated Method: " + context.getMethod()).append(System.lineSeparator())
					.append("- Runtime: " + end + " millisecond(s)");

			Files.deleteIfExists(Paths.get(LOG_FILE));

			Files.createFile(Paths.get(LOG_FILE));

			Files.write(Paths.get(LOG_FILE), textualResult.toString().getBytes());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;

	}

}

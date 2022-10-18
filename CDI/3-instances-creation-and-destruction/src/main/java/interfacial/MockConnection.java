package interfacial;

public interface MockConnection extends AutoCloseable {

	public void commit(String data);

	default void close() {

		System.out.println("CONNECTION CLOSED.");
		
	}

}

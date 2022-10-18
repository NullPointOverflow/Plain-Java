package implementation;

import interfacial.MockConnection;

public class Connection implements MockConnection {

	@Override
	public void commit(String data) {

		System.out.println("DATA COMMITED.");

	}

}

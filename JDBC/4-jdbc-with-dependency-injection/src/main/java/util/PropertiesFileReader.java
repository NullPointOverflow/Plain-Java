package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

	private Properties properties = new Properties();

	public PropertiesFileReader readPropertiesFile(String filePath) {

		try (InputStream fileStream = new FileInputStream(PropertiesFileReader.class.getResource(filePath).getPath())) {

			properties.load(fileStream);

		} catch (IOException e) {

			throw new RuntimeException("The specified file could not be read.", e);

		}

		return this;

	}

	public Properties getReadProperties() {

		return this.properties;

	}

	public Properties getPropertiesFromFile(String filePath) {

		Properties properties = new Properties();

		try (InputStream fileStream = new FileInputStream(PropertiesFileReader.class.getResource(filePath).getPath())) {

			properties.load(fileStream);

		} catch (IOException e) {

			throw new RuntimeException("The specified file could not be read.", e);

		}

		return properties;

	}

}

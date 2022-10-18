package factory.interfacial;

import jakarta.persistence.spi.PersistenceUnitInfo;

public interface PersistenceUnitInfoFactory {

	public PersistenceUnitInfo getPersistenceUnit();

}

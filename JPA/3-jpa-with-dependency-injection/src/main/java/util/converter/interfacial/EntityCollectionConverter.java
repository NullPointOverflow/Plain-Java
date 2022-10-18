package util.converter.interfacial;

import java.util.Collection;

public interface EntityCollectionConverter<RawObject, Entity> {

	public Collection<Entity> toEntities(RawObject object) throws Exception;

}

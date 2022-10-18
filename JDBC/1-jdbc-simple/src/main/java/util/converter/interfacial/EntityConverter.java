package util.converter.interfacial;

import java.util.Collection;

public interface EntityConverter<RawObject, Entity> {

	public Entity toEntity(RawObject object) throws Exception;

	public Collection<Entity> toEntities(RawObject object) throws Exception;

}

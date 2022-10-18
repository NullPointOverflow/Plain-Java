package util.converter.interfacial;

public interface EntityConverter<RawObject, Entity> {

	public Entity toEntity(RawObject object) throws Exception;

}

package org.eclipse.triquetrum.commands.api.services;

public class DirectorDescriptor extends EntityDescriptor {

	public DirectorDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCategory() {
		return "Director";
	}

}

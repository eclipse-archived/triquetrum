package org.eclipse.triquetrum.commands.api.services;

public class ParameterDescriptor extends EntityDescriptor {

	private String defaultValue="";
	
	public ParameterDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
	}

	@Override
	public String getCategory() {
		return "Parameter";
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	

}

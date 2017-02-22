package org.eclipse.triquetrum.commands.api.services;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityDescriptor {
	
	private String clazz;
	
	private String displayName;

	private String icon;
	
	private String sourceLibrary;

	private List<ParameterDescriptor> parameters = new ArrayList<>();

	public EntityDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super();
		this.sourceLibrary = sourceLibrary;
		this.displayName = displayName;
		this.clazz = clazz;
		this.icon = icon;
	}
	
	
	public String getSourceLibrary() {
		return sourceLibrary;
	}

	public String getClazz() {
		return clazz;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getIcon() {
		return icon;
	}
	
	public abstract String getCategory();


	public void addParameter(ParameterDescriptor parameter) {
		parameters.add(parameter);
	}


	public List<ParameterDescriptor> getParameters() {
		return parameters;
	}
	
	
	
}

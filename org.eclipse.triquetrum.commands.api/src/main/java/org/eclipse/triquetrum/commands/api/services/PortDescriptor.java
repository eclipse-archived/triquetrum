package org.eclipse.triquetrum.commands.api.services;

public class PortDescriptor extends EntityDescriptor {

	private boolean input;
	
	private boolean output;
	
	
	public PortDescriptor(String sourceLibrary,String displayName, String clazz, String icon,boolean input,boolean output) {
		super(sourceLibrary,displayName, clazz, icon);
		this.input = input;
		this.output = output;
	}

	@Override
	public String getCategory() {
		return "Port";
	}
	
	
	
	public boolean isInput() {
		return input;
	}
	
	public boolean isOutput() {
		return output;
	}

}

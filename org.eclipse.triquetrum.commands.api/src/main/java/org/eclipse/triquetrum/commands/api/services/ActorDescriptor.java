package org.eclipse.triquetrum.commands.api.services;

import java.util.ArrayList;
import java.util.List;

public class ActorDescriptor extends EntityDescriptor {

	private List<PortDescriptor> ports = new ArrayList<>();
	
	public ActorDescriptor(String sourceLibrary,String displayName, String clazz, String icon) {
		super(sourceLibrary,displayName, clazz, icon);
	}

	@Override
	public String getCategory() {
		return "Actor";
	}
	
	public void addPort(PortDescriptor port)
	{
		ports.add(port);
	}
	
	public List<PortDescriptor> getPorts() {
		return ports;
	}

}

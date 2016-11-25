package de.appsist.commons.event;

import java.util.Map;

/**
 * Events generated when all services are initialized. 
 * @author simon.schwantzer(at)im-c.de
 */
public class StartupCompleteEvent extends AppsistEvent {
	public static final String MODEL_ID = "startupComplete";
	
	/**
	 * Creates the event based on a content map.
	 * @param content Map with the event content.
	 * @throws IllegalArgumentException One or more required fields are missing.
	 */
	public StartupCompleteEvent(Map<String, Object> content) throws IllegalArgumentException {
		super(content, Type.SERVICE);
	}
	
	/**
	 * Creates a process event.
	 * @param id ID for the event instance.
	 */
	public StartupCompleteEvent(String id) {
		super(id, MODEL_ID, Type.SERVICE);
	}
}

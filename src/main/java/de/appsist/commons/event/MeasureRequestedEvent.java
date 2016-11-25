package de.appsist.commons.event;

import java.util.Map;

/**
 * Event requesting a measure to be deployed.
 *  
 * @author simon.schwantzer(at)im-c.de
 *
 */
public class MeasureRequestedEvent extends AppsistEvent {
	public static final String MODEL_ID = "measureRequested";
	
	/**
	 * Creates the event based on a content map.
	 * @param content Map with the event content.
	 * @throws IllegalArgumentException One or more required fields are missing.
	 */
	public MeasureRequestedEvent(Map<String, Object> content) throws IllegalArgumentException {
		super(content, Type.SERVICE);
		if (super.getPayload() == null) {
			throw new IllegalArgumentException("Missing field: payload");
		}
		
		Map<String, Object> payload = super.getPayload();
		if (!payload.containsKey("measureId")) throw new IllegalArgumentException("Missing measure identifier [measureId].");
		if (!payload.containsKey("targetGroup")) throw new IllegalArgumentException("Missing target group [targetGroup].");
	}
	
	/**
	 * Creates a new event.
	 * @param id Identifier for the event.
	 * @param measureId Vendor identifier.
	 * @param targetGroup Identifier for the target group.
	 * @param context Context for the execution. May be <code>null</code>.
	 */
	public MeasureRequestedEvent(String id, String measureId, String targetGroup, Map<String, Object> context) {
		super(id, MODEL_ID, Type.SERVICE);
		getPayload().put("measureId", measureId);
		getPayload().put("targetGroup", targetGroup);
		if (context != null) getPayload().put("context", context);
	}
	
	public String getMeasureId() {
		return (String) super.getPayload().get("measureId");
	}
	
	public String getTargetGroup() {
		return (String) super.getPayload().get("targetGroup");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getContext() {
		return (Map<String, Object>) super.getPayload().get("context");
	}
}

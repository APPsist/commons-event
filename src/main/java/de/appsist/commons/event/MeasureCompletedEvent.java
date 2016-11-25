package de.appsist.commons.event;

import java.util.Map;

/**
 * Event to be thrown when a measure is completed.
 *  
 * @author simon.schwantzer(at)im-c.de
 *
 */
public class MeasureCompletedEvent extends AppsistEvent {
	public static final String MODEL_ID = "measureCompleted";
	
	/**
	 * Creates the event based on a content map.
	 * @param content Map with the event content.
	 * @throws IllegalArgumentException One or more required fields are missing.
	 */
	public MeasureCompletedEvent(Map<String, Object> content) throws IllegalArgumentException {
		super(content, Type.SERVICE);
		if (super.getPayload() == null) {
			throw new IllegalArgumentException("Missing field: payload");
		}
		
		Map<String, Object> payload = super.getPayload();
		if (!payload.containsKey("measureId")) throw new IllegalArgumentException("Missing measure identifier [measureId].");
	}
	
	/**
	 * Creates a new event.
	 * @param id Identifier for the event.
	 * @param measureId Vendor identifier.
	 * @param targetGroup Identifier for the target group. May be <code>null</code>.
	 * @param context Context for the execution. May be <code>null</code>.
	 */
	public MeasureCompletedEvent(String id, String measureId, String targetGroup, Map<String, Object> context) {
		super(id, MODEL_ID, Type.SERVICE);
		getPayload().put("measureId", measureId);
		if (targetGroup != null) getPayload().put("targetGroup", targetGroup);
		if (context != null) getPayload().put("context", context);
	}
	
	/**
	 * Identifier for the completed measure.
	 * @return URI of the measure.
	 */
	public String getMeasureId() {
		return (String) super.getPayload().get("measureId");
	}
	
	/**
	 * Target group for the event.
	 * @return URI of a target group or <code>null</code> if no target group is set.
	 */
	public String getTargetGroup() {
		return (String) super.getPayload().get("targetGroup");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getContext() {
		return (Map<String, Object>) super.getPayload().get("context");
	}
}

package de.appsist.commons.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Event simulating machine data.
 *  
 * @author simon.schwantzer(at)im-c.de
 *
 */
public class SetMachineDataEvent extends AppsistEvent {
	public static final String MODEL_ID = "setMachineData";
	
	/**
	 * Model for a field entry.
	 * 
	 */
	public static class Field {
		private final Map<String, Object> map;
		
		
		/**
		 * Creates a field entry based on a content map.
		 * @param map Map representing the field entry.
		 * @throws IllegalArgumentException One or more required fields are missing.
		 */
		public Field(Map<String, Object> map) throws IllegalArgumentException {
			this.map = map;
			
			if (!map.containsKey("name")) throw new IllegalArgumentException("Missing field: name");
			if (!map.containsKey("machineValueType")) throw new IllegalArgumentException("Missing field: machineValueType");
			if (!map.containsKey("unit")) throw new IllegalArgumentException("Missing field: unit");
			if (!map.containsKey("visualizationType")) throw new IllegalArgumentException("Missing field: visualizationType");
			if (!map.containsKey("visualizationLevel")) throw new IllegalArgumentException("Missing field: visualizationLevel");
			if (!map.containsKey("value")) throw new IllegalArgumentException("Missing field: value");
		}
		
		/**
		 * Creates a new field entry.
		 */
		public Field(String name, String machineValueType, String unit, String visualizationType, String visualizationLevel, String value) {
			map = new LinkedHashMap<>();
			
			map.put("name", name);
			map.put("machineValueType", machineValueType);
			map.put("unit", unit);
			map.put("visualizationType", visualizationType);
			map.put("visualizationLevel", visualizationLevel);
			map.put("value", value);
			
		}
		
		public String getName() {
			return (String) map.get("name");
		}
		
		public String getMachineValueType() {
			return (String) map.get("machineValueType");
		}
		
		public String getUnit() {
			return (String) map.get("unit");
		}
		
		public String getVisualizationType() {
			return (String) map.get("visualizationType");
		}
		
		public String getVisualizationLevel() {
			return (String) map.get("visualizationLevel");
		}
		
		public String getValue() {
			return (String) map.get("value");
		}
		
		protected Map<String, Object> asMap() {
			return map;
		}
	}
	
	private final List<Field> fields; 

	/**
	 * Creates the event based on a content map.
	 * @param content Map with the event content.
	 * @throws IllegalArgumentException One or more required fields are missing.
	 */
	public SetMachineDataEvent(Map<String, Object> content) throws IllegalArgumentException {
		super(content, Type.MACHINE);
		if (super.getPayload() == null) {
			throw new IllegalArgumentException("Missing field: payload");
		}
		
		Map<String, Object> payload = super.getPayload();
		if (!payload.containsKey("vendorId")) throw new IllegalArgumentException("Missing field: vendorId");
		if (!payload.containsKey("machineId")) throw new IllegalArgumentException("Missing field: machineId");
		if (!payload.containsKey("serialNumber")) throw new IllegalArgumentException("Missing field: serialNumber");
		if (!payload.containsKey("stationId")) throw new IllegalArgumentException("Missing field: stationId");
		if (!payload.containsKey("siteId")) throw new IllegalArgumentException("Missing field: siteId");
		
		fields = new ArrayList<>();
		
		Object fieldsField = super.getPayload().get("fields");
		if (fieldsField != null && (fieldsField instanceof List)) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> fieldsArray = (List<Object>) fieldsField;
				for (Object fieldObject : fieldsArray) {
					@SuppressWarnings("unchecked")
					Field field = new Field((Map<String, Object>) fieldObject);
					fields.add(field);
				}
			} catch (ClassCastException e) {
				throw new IllegalArgumentException("Invalid fields list or fields entry.");
			}
			
		}
	}
	
	/**
	 * Creates a new event.
	 * @param id Identifier for the event.
	 * @param vendorId Vendor identifier.
	 * @param machineId Machine identifier.
	 * @param serialNumber Serial number.
	 * @param stationId Station identifier.
	 * @param siteId Site identifier.
	 * @param fields Fields for the machine state.
	 */
	public SetMachineDataEvent(String id, String vendorId, String machineId, String serialNumber, String stationId, String siteId, List<Field> fields) {
		super(id, MODEL_ID, Type.MACHINE);
		getPayload().put("vendorId", vendorId);
		getPayload().put("machineId", machineId);
		getPayload().put("serialNumber", serialNumber);
		getPayload().put("stationId", stationId);
		getPayload().put("siteId", siteId);
		
		this.fields = fields;
		if (fields != null) {
			List<Object> fieldsArray = new ArrayList<>();
			for (Field field : fields) {
				fieldsArray.add(field.asMap());
			}
			super.getPayload().put("fields", fieldsArray);
		}
	}
	
	public String getVendorId() {
		return (String) super.getPayload().get("vendorId");
	}
	
	public String getMachineId() {
		return (String) super.getPayload().get("machineId");
	}
	
	public String getSerialNumber() {
		return (String) super.getPayload().get("serialNumber");
	}
	
	public String getStationId() {
		return (String) super.getPayload().get("stationId");
	}
	
	public String getSiteId() {
		return (String) super.getPayload().get("siteId");
	}
	
	public List<Field> getFields() {
		return Collections.unmodifiableList(fields);
	}
}

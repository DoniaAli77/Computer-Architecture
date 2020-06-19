package Components;

import java.util.HashMap;

public class PipelineRegisters {
	private HashMap<String, Object> stageInfo;
	
	public PipelineRegisters() {
		stageInfo = new HashMap<String,Object>();
	}
	
	/**
	 * save an info of a stage
	 * @param name name of the info
	 * @param value the value of the info
	 */
	public void setInfo(String name,Object value) {
		stageInfo.put(name, value);
	}
	
	/**
	 * To get the value of an info about a specific stage
	 * @param name name of the info
	 * @return the value of the info
	 */
	public Object getInfo(String name) {
		return stageInfo.get(name);
	}
	
	public void deleteInfo(String name) {
		stageInfo.remove(name);
	}
}

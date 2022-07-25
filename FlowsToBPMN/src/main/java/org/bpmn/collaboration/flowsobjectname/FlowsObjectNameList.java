package org.bpmn.collaboration.flowsobjectname;

import java.util.ArrayList;
import java.util.HashMap;

public class FlowsObjectNameList extends AbstractFlowsObjectName {

	public HashMap<String, ArrayList<AbstractFlowsObjectName>> ObjectTypeActionLogs;

	@Override
	public String toString() {

		String retString = "";

		for (String name : ObjectTypeActionLogs.keySet()) {
			String key = name.toString();
			String value = ObjectTypeActionLogs.get(name).toString();
			retString += key + "= {" + value + "}" + "\n";
		}

		return retString;
	}

}

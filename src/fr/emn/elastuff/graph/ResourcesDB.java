package fr.emn.elastuff.graph;

import java.util.HashMap;

@SuppressWarnings("serial")
public class ResourcesDB extends HashMap<Integer, CloudResource> {

	private static ResourcesDB instance;

	/*
	 * HashMap<Integer, Object> hmapPM = new HashMap<Integer, Object>();
	 * HashMap<Integer, Object> hmapTier = new HashMap<Integer, Object>();
	 * HashMap<Integer, Object> hmapVM = new HashMap<Integer, Object>() ;
	 * 
	 * public HashMap<Integer, Object> getHmapVM() { return hmapVM; }
	 * public void setHmapVM(HashMap<Integer, Object> hmapVM) { this.hmapVM = hmapVM; }
	 * 
	 */
	public static ResourcesDB getInstance() {
		if (instance == null) {
			instance = new ResourcesDB();
		}

		return instance;
	}

}

package fr.emn.elastuff.offering;


import java.util.LinkedHashMap;

@SuppressWarnings("serial")
public class OfferingsSoftDB extends LinkedHashMap<String, OffSoft> {

	private static OfferingsSoftDB instance;

	/*
	 * HashMap<Integer, Object> hmapPM = new HashMap<Integer, Object>();
	 * HashMap<Integer, Object> hmapTier = new HashMap<Integer, Object>();
	 * HashMap<Integer, Object> hmapVM = new HashMap<Integer, Object>() ;
	 * 
	 * public HashMap<Integer, Object> getHmapVM() { return hmapVM; }
	 * public void setHmapVM(HashMap<Integer, Object> hmapVM) { this.hmapVM = hmapVM; }
	 * 
	 */
	public static OfferingsSoftDB getInstance() {
		if (instance == null) {
			instance = new OfferingsSoftDB();
		}

		return instance;
	}

}

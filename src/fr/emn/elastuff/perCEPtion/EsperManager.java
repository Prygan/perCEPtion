package fr.emn.elastuff.perCEPtion;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;

public class EsperManager {
	private Configuration config;
	private EPServiceProvider epsp;
	private EPRuntime eprt;

	public EsperManager() {
		config = new Configuration();
		configAliases();
	}

	public void configAliases() {
		config.addEventType("Appli", Appli.class.getName());
		config.addEventType("Co", Co.class.getName());
		config.addEventType("PM", PM.class.getName());
		config.addEventType("VM", VM.class.getName());
		config.addEventType("Tier", Tier.class.getName());

	}
}

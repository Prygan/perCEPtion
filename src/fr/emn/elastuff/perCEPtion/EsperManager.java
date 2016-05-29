package fr.emn.elastuff.perCEPtion;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;

public class EsperManager {
	private static EsperManager instance = new EsperManager();
	private Configuration config;
	private EPServiceProvider epsp;
	private EPRuntime eprt;

	private EsperManager() {
		config = new Configuration();
		configAliases();
		epsp = EPServiceProviderManager.getProvider("myCEPEngine", config);
		eprt = epsp.getEPRuntime();
	}

	public EsperManager getInstance() {
		return instance;
	}

	public EPRuntime getRuntime() {
		return eprt;
	}

	public void configAliases() {
		config.addEventType("Appli", Appli.class.getName());
		config.addEventType("Co", Co.class.getName());
		config.addEventType("PM", PM.class.getName());
		config.addEventType("VM", VM.class.getName());
		config.addEventType("Tier", Tier.class.getName());
	}

	public void addStatements() {
		EPAdministrator epAdm = epsp.getEPAdministrator();
		// TODO use the xml parsing thing to gather all the statments
		EPStatement epStatement = epAdm
				.createEPL("select * from VM.win:time_batch(1 sec) having avg(ram_consumption) > 95");
		epStatement.addListener(new CEPListener());
	}
}

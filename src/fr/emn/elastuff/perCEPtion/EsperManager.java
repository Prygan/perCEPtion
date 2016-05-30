package fr.emn.elastuff.perCEPtion;

import java.io.IOException;
import java.util.Collection;

import org.jdom.JDOMException;

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
	private ParserXML parser;

	private EsperManager() {
		config = new Configuration();
		configAliases();
		epsp = EPServiceProviderManager.getProvider("myCEPEngine", config);
		parser = new ParserXML();
		addStatements();
		eprt = epsp.getEPRuntime();
	}

	public static EsperManager getInstance() {
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

		try {
			Collection<Request> requests = parser.read("Input/request.xml");
			for (Request r : requests) {
				EPStatement epStatement = epAdm.createEPL(r.getCommand());
				System.out.println("STMNT ADDED : " + r.getCommand());
				epStatement.addListener(new CEPListener(r.getName()));
			}

		} catch (JDOMException | IOException | ParserXMLException e) {
			// TODO better exception management
			e.printStackTrace();
		}
	}

	public void sendEvent(Object o) {
		eprt.sendEvent(o);
	}
}

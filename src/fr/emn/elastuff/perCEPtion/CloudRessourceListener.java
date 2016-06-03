package fr.emn.elastuff.perCEPtion;

import java.util.Observable;
import java.util.Observer;

import com.espertech.esper.client.EPRuntime;


public class CloudRessourceListener implements Observer {

	private EPRuntime eprt ;
	
	public CloudRessourceListener(EPRuntime eprt) {
		this.eprt = eprt ;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.eprt.sendEvent(o);
	}

}

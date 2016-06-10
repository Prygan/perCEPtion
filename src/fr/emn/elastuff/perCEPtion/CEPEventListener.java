package fr.emn.elastuff.perCEPtion;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class CEPEventListener implements UpdateListener {
	private static Logger logger = Logger.getLogger("mainLogger");

	private String name;

	public CEPEventListener(String n) {
		super();
		name = n;
	}

	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("ESPER CEP Event Listener : Event " + name + " received: " + newData[0].getUnderlying());
	}

}

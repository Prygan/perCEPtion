package fr.emn.elastuff.perCEPtion;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class CEPEventListener implements UpdateListener {
	private String name;

	public CEPEventListener(String n) {
		super();
		name = n;
	}

	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		// TODO make all the magic append
		System.out.println("ESPER CEP Event Listener : Event " + name + " received: " + newData[0].getUnderlying());
	}

}

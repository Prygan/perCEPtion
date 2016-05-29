package fr.emn.elastuff.perCEPtion;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class CEPListener implements UpdateListener {

	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		// TODO make all the magic append
		System.out.println("Event received: " + newData[0].getUnderlying());
	}

}

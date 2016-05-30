package fr.emn.elastuff.perCEPtion;

import java.util.ArrayList;
import java.util.List;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import fr.emn.elastuff.graph.CloudResource;

public class CEPListener implements UpdateListener {
	private String name;

	public CEPListener(String n) {
		super();
		name = n;
	}

	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		// TODO make all the magic append
		System.out.println("Event " + name + " received: " + newData[0].getUnderlying());
		List<CloudResource> ressources = new ArrayList<CloudResource>();
		Symptom s = new Symptom(name, ressources);
		QueueSymptom.getInstance().addSymptom(s);
	}

}

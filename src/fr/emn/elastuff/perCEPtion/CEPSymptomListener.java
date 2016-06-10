package fr.emn.elastuff.perCEPtion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.event.bean.BeanEventBean;

import fr.emn.elastuff.graph.CloudResource;

public class CEPSymptomListener implements UpdateListener {
	private static Logger logger = Logger.getLogger("mainLogger");
	private String name;

	public CEPSymptomListener(String n) {
		super();
		name = n;
	}

	@Override
	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("ESPER CEPListener : Event " + name + " received: " + newData[0].getUnderlying());

		List<CloudResource> ressources = new ArrayList<CloudResource>();

		if (!(newData[0].getUnderlying() instanceof CloudResource)) {
			@SuppressWarnings("unchecked")
			HashMap<String, BeanEventBean> map = (HashMap<String, BeanEventBean>) newData[0].getUnderlying();

			for (String key : map.keySet()) {
				ressources.add((CloudResource) map.get(key).getUnderlying());
			}
		} else {
			ressources.add((CloudResource) newData[0].getUnderlying());
		}

		Symptom s = new Symptom(name, ressources);
		QueueSymptom.getInstance().addSymptom(s);
	}

}

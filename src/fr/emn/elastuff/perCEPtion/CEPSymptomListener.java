package fr.emn.elastuff.perCEPtion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.event.bean.BeanEventBean;

import fr.emn.elastuff.graph.CloudResource;

public class CEPSymptomListener implements UpdateListener {
	private static Logger logger = Logger.getLogger("mainLogger");
	private String name;
	private int symptom_ttl ;

	public CEPSymptomListener(String n, int symptom_ttl){
		super();
		name = n;
		this.symptom_ttl = symptom_ttl ;
	}

	public void update(EventBean[] newData, EventBean[] oldData) {
		logger.info("ESPER CEPSymptomListener : Event " + name + " received: " + newData[0].getUnderlying());

		List<CloudResource> ressources = new ArrayList<CloudResource>();

		if (newData[0].getUnderlying() instanceof Map) {
			@SuppressWarnings("unchecked")
			HashMap<String, Object> map = (HashMap<String, Object>) newData[0].getUnderlying();

			for (String key : map.keySet()) {
				if (map.get(key) instanceof BeanEventBean)
					ressources.add((CloudResource) ((BeanEventBean) map.get(key)).getUnderlying());
				if (map.get(key) instanceof CloudResource)
					ressources.add((CloudResource) map.get(key));
			}
		} else if (newData[0].getUnderlying() instanceof CloudResource) {
			ressources.add((CloudResource) newData[0].getUnderlying());
		} else {
			logger.error("CEPSymptomListener " + newData[0].getUnderlying().getClass() + " unsupported");
		}

		Symptom s = new Symptom(this.name, ressources,this.symptom_ttl);
		QueueSymptom.getInstance().addSymptom(s);
	}

}

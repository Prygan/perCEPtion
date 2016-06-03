package fr.emn.elastuff.perCEPtion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.CloudResource;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;
import fr.emn.elastuff.offering.OffInfra;
import fr.emn.elastuff.offering.OffSoft;

public class CloudRessourceSimulator {
	
	private Map<String,CloudResource> cloudsRessources;
	
	public CloudRessourceSimulator(){
		this.cloudsRessources = new HashMap<>();
	}
	
	public void createCloudResources(){
	// offering infra
			OffInfra small = new OffInfra("small", 1, 1024, 10); // 1CPU , 512 MB ,
																	// 10 GB
			OffInfra medium = new OffInfra("medium", 2, 2048, 50); // 2 CPU , 2048
																	// MB , 50 GB
			OffInfra large = new OffInfra("large", 4, 4096, 500); // 4 CPU , 4096 MB
																	// , 500 GB

			/*
			 * //initialize OfferingsInfraDB OfferingsInfraDB offeringsInfra =
			 * OfferingsInfraDB.getInstance(); offeringsInfra.put("small", small);
			 * offeringsInfra.put("medium", medium); offeringsInfra.put("large",
			 * large);
			 */

			// offering soft
			OffSoft mode1 = new OffSoft("mode1");
			OffSoft mode2 = new OffSoft("mode2");
			OffSoft mode3 = new OffSoft("mode3");

			/*
			 * //initialize OfferingsSoftDB OfferingsSoftDB offeringsSoft =
			 * OfferingsSoftDB.getInstance(); offeringsSoft.put("mode1", mode1);
			 * offeringsSoft.put("mode2", mode2); offeringsSoft.put("mode3", mode3);
			 */

			// resources
			Appli appli = new Appli("app1");


			Tier tier1 = new Tier("T1");
			Tier tier2 = new Tier("T2");

			PM pm1 = new PM("PM1", appli);
			PM pm2 = new PM("PM2", appli);

			VM vm1 = new VM("VM1", small);
			VM vm2 = new VM("VM2", large);
			VM vm3 = new VM("VM3", medium);

			Co co1 = new Co("co1", "mySQL", mode1);
			Co co2 = new Co("co2", "mySQL", mode2);
			Co co3 = new Co("co3", "mySQL", mode2);
			Co co4 = new Co("co4", "mySQL", mode3);

			// graph
			appli.addElt(pm1);
			appli.addElt(pm2);
			appli.addElt(tier1);
			appli.addElt(tier2);

			tier1.addElt(vm1);
			tier2.addElt(vm2);
			tier2.addElt(vm3);

			pm1.addElt(vm1);
			pm2.addElt(vm2);
			pm2.addElt(vm3);

			vm1.addElt(co1);
			vm1.addElt(co2);
			vm2.addElt(co3);
			vm3.addElt(co4);

			//cloudRessources
			cloudsRessources.put(appli.getName(), appli);
			cloudsRessources.put(tier1.getName(), tier1);
			cloudsRessources.put(tier2.getName(), tier2);
			cloudsRessources.put(pm1.getName(), pm1);
			cloudsRessources.put(pm2.getName(), pm2);
			cloudsRessources.put(vm1.getName(), vm1);
			cloudsRessources.put(vm2.getName(), vm2);
			cloudsRessources.put(vm3.getName(), vm3);
			cloudsRessources.put(co1.getName(), co1);
			cloudsRessources.put(co2.getName(), co2);
			cloudsRessources.put(co3.getName(), co3);
			cloudsRessources.put(co4.getName(), co4);
			
			
			System.out.println("\n***************** Initial graph structure *******************\n");
			appli.display();
			
	}
	
	public void setValue(String name, String methodName, Object value) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		if(cloudsRessources.get(name) == null)
			throw new IllegalArgumentException("the cloudRessource "+name+" doesn't exist");

		Method method = cloudsRessources.get(name).getClass().getMethod(this.parse(cloudsRessources.get(name), methodName), int.class);
		method.invoke(cloudsRessources.get(name), value);
	}
	
	public String parse(CloudResource cr, String command){
		if(cr instanceof VM){
			if("Vcpu".equals(command))
				return "setVcpu_consumption" ;
			if("ram".equals(command))
				return "setRam_consumption" ;
			if("disk".equals(command))
				return "setDisk_consumption" ;
			throw new IllegalArgumentException("the parameter "+command+" doesn't exist for VM");
		}
		if(cr instanceof PM){
			if("cpu".equals(command))
				return "setCpu_consumption" ;
			if("ram".equals(command))
				return "setRam_consumption" ;
			if("disk".equals(command))
				return "setDisk_consumption" ;
			throw new IllegalArgumentException("the parameter "+command+" doesn't exist for PM");
		}
		throw new IllegalArgumentException("the parameter "+command+" doesn't exist for "+cr.getName());
	}
	
	public Map<String,CloudResource> getCloudRessources(){
		return this.cloudsRessources ;
	}
}

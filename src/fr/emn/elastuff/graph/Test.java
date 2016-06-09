package fr.emn.elastuff.graph;

import fr.emn.elastuff.offering.OffInfra;
import fr.emn.elastuff.offering.OffSoft;
import fr.emn.elastuff.perCEPtion.QueueSymptom;

public class Test {

	public static void main(String[] args) {

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

		System.out.println("\n***************** Initial graph structure *******************\n");
		appli.display();

		System.out.println("\n***************** Event creation *******************\n");
		// new version, use the launcher with Perception.java
		// EsperManager em = new EsperManager();

		for (int i = 0; i < 10; i++) {
			vm1.setRam_consumption(96);
			// em.sendEvent(vm1);
		}

		System.out.println(QueueSymptom.getInstance().toString());
	}

}

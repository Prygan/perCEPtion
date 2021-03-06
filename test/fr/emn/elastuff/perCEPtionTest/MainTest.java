package fr.emn.elastuff.perCEPtionTest;

import org.apache.log4j.PropertyConfigurator;

import fr.emn.elastuff.perCEPtion.Perception;

public class MainTest {
	public static void main(String[] args) {
		PropertyConfigurator.configure("logs/log4j/log4j.properties");

		Perception perCEPtion = new Perception();
		perCEPtion.initialize();
		perCEPtion.parse("/xml Input/request.xml");
		perCEPtion.parse("/run");

		long t = System.currentTimeMillis();
		int sec = 1000;
		int sec2 = 4000;
		long end = t + sec;
		long end2 = t + sec;
		int vm1 = 96;
		int RT = 96;
		int RT2 = 74;
		while (System.currentTimeMillis() > 0) {
			if (System.currentTimeMillis() > end) {
				if (vm1 < 100) {
					vm1 += 1;
					perCEPtion.parse("/simulate VM1 vcpu " + vm1);
					end += sec;
				}
			}
			if (System.currentTimeMillis() > end2) {
				if (RT < 100) {
					RT += 1;
					RT2 -= 1;
					perCEPtion.parse("/simulate co1 RT " + RT);
					perCEPtion.parse("/simulate co2 RT " + RT2);
					end2 += sec2;
				}
			}
		}
	}
}

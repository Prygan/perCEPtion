package fr.emn.elastuff.perCEPtion;

import java.util.List;

import fr.emn.elastuff.graph.Appli;
import fr.emn.elastuff.graph.CloudResource;
import fr.emn.elastuff.graph.Co;
import fr.emn.elastuff.graph.PM;
import fr.emn.elastuff.graph.Tier;
import fr.emn.elastuff.graph.VM;

/**
 * Symptom Class
 * Allows to identify the symptom by its name and all the Cloud Ressources which are concerned
 * 
 * @author Kévin
 *
 */
public class Symptom implements Comparable<Symptom> {
	//Time to leave, 60 sec
	private final long TTL = 60*10^3;
	private final String name;
	private final List<CloudResource> cloudRessources;
	private final long startTime;	 	
	private final int score;
	
	private static final int scoreAppli = 4;
	private static final int scorePm = 3;
	private static final int scoreTier = 3;
	private static final int scoreVm = 2;
	private static final int scoreCo = 1;
	
	public Symptom(String name, List<CloudResource> cloudRessources) {
		this.name = name;
		this.cloudRessources = cloudRessources;
		this.startTime = System.currentTimeMillis();
		this.score = this.calcul(cloudRessources);
	}
	
	/**
	 * Calcul the symptom's score to determine its priority in the queue
	 * @return
	 */
	private int calcul(List<CloudResource> cloudRessources){
		int score = 0;
		for (CloudResource cr : cloudRessources){
			if(cr instanceof Appli) score += scoreAppli;
			if(cr instanceof PM) score+= scorePm;
			if(cr instanceof Tier) score+= scoreTier;
			if(cr instanceof Co) score+= scoreCo;
			if(cr instanceof VM) score+= scoreVm;
		}
		return score;
	}
	
	public String getName() {
		return name;
	}
	public List<CloudResource> getCloudRessources() {
		return cloudRessources;
	}
	
	/**
	 * 
	 * @return true 
	 * 		if the elapsed time is stricly superior to the time to leave
	 * 	else return false
	 */
	public boolean isExpired(){
		return (System.currentTimeMillis()-startTime)>this.TTL;
	}
	
	public int getScore() {
		return score;
	}

	public long getTTL() {
		return TTL;
	}
	
	// Method to compare two symptom
	public int compareTo(Symptom s) {
		int resCompScore = Integer.compare(this.getScore(), s.getScore());
		//if the score of the two symptom are equals
		if (resCompScore == 0) {
		//Compare the starting time of the symptom
		return Long.compare(this.startTime, s.startTime);
		}
		return resCompScore;
	}
	
}

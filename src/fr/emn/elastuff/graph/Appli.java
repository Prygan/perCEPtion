package fr.emn.elastuff.graph;

import java.util.ArrayList;


import javax.naming.SizeLimitExceededException;

import fr.emn.elastuff.offering.OffInfra;



public class Appli extends CloudResource {

	private ArrayList<Tier> tiers;
	private ArrayList<PM> pms;
	private static int score = 4;
	
	public Appli(String nom) {
		super(nom);
		tiers = new ArrayList<Tier>();
		pms = new ArrayList<PM>();
		
	}

	public ArrayList<Tier> getTiers() {
		return (tiers);
	}

	public ArrayList<PM> getPms() {
		return (pms);
	}
	
		
	/************************ add child ************************/
	@Override
	public	void addElt(CloudResource o) {
		ResourcesDB db = ResourcesDB.getInstance();
		if (o instanceof PM) {
			if (!db.containsKey(o.hashCode())) {
				pms.add((PM) o);
				db.put(o.hashCode(), o);
				System.out.println(((PM) o).getName() + " was added to " + this.getName());
			} else
				 throw new UnsupportedOperationException("Error: A dupplicate name exists!");
				//System.out.println("Error: A dupplicate name exists!");
		} else if (!db.containsKey(o.hashCode())) {
			tiers.add((Tier) o);
			db.put(o.hashCode(), o);
			System.out.println(((Tier) o).getName() + " was added to " + this.getName());
		} else
			throw new UnsupportedOperationException("Error: A dupplicate name exists!");
			//System.out.println("Error: A dupplicate name exists!");
	}
	
	
	/************************ remove child ************************/
	@Override
	void removeElt(CloudResource o) {
		
		ResourcesDB db = ResourcesDB.getInstance();
		if (o instanceof PM) {

			if (db.containsKey(o.hashCode())) {
				pms.remove(o);
				db.remove(o.hashCode(), o);
				System.out.println(((PM) o).getName() + " has been deleted from " + this.getName());
			} else
				System.out.println("Error: Not found!");
		} else if (db.containsKey(o.hashCode())) {
			tiers.remove(o);
			db.remove(o.hashCode(), o);
			System.out.println(((Tier) o).getName() + " has been deleted from " + this.getName());
		} else
			System.out.println("Error: Not found!");

	}

	
	/************************ Scale Out Infra ************************
	 * add (nbVM) VM with offering (Offering) for each Tier in appli
	 *****************************************************************/
	
	@Override
	public void soi(int nbVM, OffInfra offering) {
		for (Tier tier : tiers) {
			tier.soi(nbVM, offering);
		}

	}
	
	/************************ Scale Out Infra ************************
	 *   add 1 VM with offering (Offering) for each Tier in appli
	 *****************************************************************/
	@Override
	public void soi( OffInfra offering) {
		for (Tier tier : tiers) {
			tier.soi(offering);
		}
	}
	
	/************************ Scale In Infra ************************
	 * 				remove (nbVM) VM from each Tier in appli
	 *****************************************************************/

	@Override
	public void sii(int nbResources) throws SizeLimitExceededException {
		for (Tier tier : tiers) {
			tier.sii(nbResources);
		}
	}

	
	/************************ Scale In Infra ************************
	 * 			           remove (vm) from appli
	 *****************************************************************/
	
	@Override
	public void sii(VM vm) throws UnsupportedOperationException {
		for (Tier tier : this.getTiers())
		{
			if (tier.getVms().contains(vm))
				tier.sii(vm);
		}
		for (PM pm:this.getPms())
			if (pm.getVms().contains(vm))
				pm.getVms().remove(vm);
	}
	
	

	/************************ Scale Up Infra ************************
	 * 				increase the offering of each VM in appli
	 * 						 Large  --> throw exception!
	 * 						 Medium --> Large
	 * 						 Small  --> Medium
	 *****************************************************************/

	@Override
	public void sui() throws UnsupportedOperationException {
		
		for (Tier tier : tiers) {
			tier.sui();
		}
	}
	
	/************************ Scale Down Infra ************************
	 * 				decrease the offering of each VM in appli
	 * 						 Large  --> Medium
	 * 						 Medium --> Small
	 * 						 Small  --> throw exception!
	 *****************************************************************/

	@Override
	public void sdi() throws UnsupportedOperationException {
		for (Tier tier : tiers) {
			tier.sdi();
		}
	}
	
	
	/************************ Scale Out Soft ************************
	 * 		add (nbCo) CO with type (typeCO) for each VM in appli
	 ****************************************************************/

	@Override
	public void sos( int nbCO , String typeCO) {
		
		for (Tier tier : tiers) {
			tier.sos(nbCO, typeCO);
			
		}

	}

	/************************ Scale In Soft ************************
	 * 	remove (nbCo) CO with type (typeCO) of each VM in appli
	 ***************************************************************/
	@Override
	public void sis(int nbCO, String typeCO) throws UnsupportedOperationException {
		for (Tier tier : tiers) {
			tier.sis(nbCO, typeCO);
		}
	}
	
	/************************ Scale In Soft ************************
	 * 					   remove CO from appli
	 ***************************************************************/
	@Override
	public void sis(Co co) throws UnsupportedOperationException {
		
		
		for (Tier tier : this.getTiers())
		{
	
				tier.sis(co);
		}
	}
	
	/************************ Scale Up Soft ************************
	 * 			increase the offering of each CO in appli
	 * 						 Mode1 --> Mode2
	 * 						 Mode2 --> Mode3
	 *						 Mode3 --> throw exception!
	 * **************************************************************/

	@Override
	public void sus() throws UnsupportedOperationException {
		
		for (Tier tier : tiers) {
			tier.sus();
		}

	}

	/************************ Scale Down Soft ************************
	 * 			increase the offering of each CO in appli
	 * 						 Mode1 --> throw exception!
	 * 						 Mode2 --> Mode1
	 *						 Mode3 --> Mode2
	 *****************************************************************/
	@Override
	public void sds() throws UnsupportedOperationException {
		for (Tier tier : tiers) {
			tier.sds();
		}

	}
	
	
	/************************display graph************************/
	@Override
	public void display()
	{
		/*System.out.println("	Application: "+this.getName());
		System.out.println("************************************");*/
		for (int i = 0; i < pms.size(); i++) {
			String name = pms.get(i).getName();
			System.out.println(" PM " + (i + 1) + ": " + name/*+
					" , "+pms.get(i).getOfferingPM()*/);
			
			pms.get(i).display();
			}

		System.out.println("________________________\n");
		for (int i = 0; i < tiers.size(); i++) {
			String name = tiers.get(i).getName();
			System.out.println(" tier " + (i + 1) + ": " + name);
			tiers.get(i).display();
		}
		
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 4;
	}

	

	
	
	
}

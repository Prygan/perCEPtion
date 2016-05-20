package fr.emn.elastuff.graph;

import java.util.ArrayList;
import java.util.List;

import fr.emn.elastuff.offering.OffInfra;
import fr.emn.elastuff.offering.OffSoft;
import fr.emn.elastuff.offering.OfferingsSoftDB;

public class Co extends CloudResource {

	private VM vm;
	private String typeCO;
	
	OffSoft offeringCO;
	
	

	public Co(String nom, String typeCO, OffSoft offeringCO) {
		super(nom);
		this.typeCO = typeCO;
		this.offeringCO = offeringCO;
	}

	public String getTypeCO() {
		return typeCO;
	}

	public void setTypeCO(String typeCO) {
		this.typeCO = typeCO;
	}

	public OffSoft getOfferingCO() {
		return offeringCO;
	}

	public void setOfferingCO(OffSoft offeringCO) {
		this.offeringCO = offeringCO;
	}

	public VM getVm() {
		return (vm);
	}

	public void setVm(VM v) {
		this.vm = v;
	}

	public void addVm(VM v) {
		if (v != null) {
			if (!v.getCos().contains(this)) {
				if (vm != null)
					try {
						vm.removeElt(this);
					} catch (UnsupportedOperationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				this.setVm(v);
				vm.getCos().add(this);
			}
		}
	}	
	
	/************************ Scale Up Soft ************************
	 * 			    increase the offering of this CO
	 * 						 Mode1 --> Mode2
	 * 						 Mode2 --> Mode3
	 *						 Mode3 --> throw exception!
	 * **************************************************************/

	@Override
	public void sus() throws UnsupportedOperationException {
		OfferingsSoftDB db = OfferingsSoftDB.getInstance();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(this.getName()+".sus()");
		OffSoft offeringCO = this.getOfferingCO();
	
		if (offeringCO!=db.values().toArray()[db.size()-1])
		{
			if(db.containsValue(offeringCO) )
			{
				List<OffSoft> valueList = new ArrayList<OffSoft>(db.values());
				int pos = valueList.indexOf(offeringCO);
				OffSoft nextOffering = (OffSoft) db.values().toArray()[pos+1];
				this.setOfferingCO(nextOffering);
			}
			
		}
		else throw new UnsupportedOperationException
		("Invalide operation : Offering of " + this.getName() + " = "+this.getOfferingCO().getName());
		
		
		/*OfferingCO offeringCO = this.getOfferingCO();
		switch (offeringCO) {

		case MODE1:
			this.setOfferingCO(OfferingCO.MODE2);
			break;
		case MODE2:
			this.setOfferingCO(OfferingCO.MODE3);
			break;
		case MODE3: throw new UnsupportedOperationException("Invalide operation : "
				+ "Offering of " + this.getName() + " = MODE3");
			
		default:
			break;
		}*/

	}

	/************************ Scale Down Soft ************************
	 * 			     increase the offering of this CO
	 * 						 Mode1 --> throw exception!
	 * 						 Mode2 --> Mode1
	 *						 Mode3 --> Mode2
	 *****************************************************************/
	@Override
	public void sds() throws UnsupportedOperationException{
		OfferingsSoftDB db = OfferingsSoftDB.getInstance();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		System.out.println(this.getName()+".sds()");
		OffSoft offeringCO = this.getOfferingCO();
		
		if (offeringCO!=db.values().toArray()[0])
		{
			if(db.containsValue(offeringCO) )
			{
				List<OffSoft> valueList = new ArrayList<OffSoft>(db.values());
				int pos = valueList.indexOf(offeringCO);
				OffSoft prevOffering = (OffSoft) db.values().toArray()[pos-1];
				this.setOfferingCO(prevOffering);
			}
			
		}
		else throw new UnsupportedOperationException
		("Invalide operation : Offering of " + this.getName() + " = "+this.getOfferingCO().getName());
		
		
		/*OfferingCO offeringCO = this.getOfferingCO();
		switch (offeringCO) {

		case MODE1:
			throw new UnsupportedOperationException(
					"Invalide operation : " + "Offering of " + this.getName() + " = MODE1");

		case MODE2:
			this.setOfferingCO(OfferingCO.MODE1);
			break;
		case MODE3:
			this.setOfferingCO(OfferingCO.MODE2);
			break;
		default:
			break;

		}*/
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addElt(CloudResource o) {
		// TODO Auto-generated method stub


	}

	@Override
	void removeElt(CloudResource o) {
		// TODO Auto-generated method stub


	}

	@Override
	public void soi(int nbResources, OffInfra offering) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sii(int nbResources) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sui() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sdi() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sos(int nbCO, String typeCO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sis(int nbCO, String typeCO) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void sii(VM vm) {
		// TODO Auto-generated method stub

	}

	@Override
	public void soi(OffInfra offering) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void sis(Co co) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

}

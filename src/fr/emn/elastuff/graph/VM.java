package fr.emn.elastuff.graph;

import java.util.ArrayList;
import java.util.List;

import fr.emn.elastuff.offering.OffInfra;
import fr.emn.elastuff.offering.OffSoft;
import fr.emn.elastuff.offering.OfferingsInfraDB;
import fr.emn.elastuff.offering.OfferingsSoftDB;

public class VM extends CloudResource {

	private ArrayList<Co> cos; // children's list
	private PM pm; // physical parent
	private Tier tier; // logical parent

	int vcpu_consumption;
	int ram_consumption;
	int disk_consumption;
	// offering
	OffInfra offVM;

	public VM(String name, OffInfra offVM) {
		super(name);
		this.offVM = offVM;
		cos = new ArrayList<Co>();
	}

	/**************************************
	 * Getters and Setters
	 **************************************/
	// new
	public OffInfra getOffVM() {
		return offVM;
	}

	public void setOffVM(OffInfra offVM) {
		this.offVM = offVM;
	}

	public PM getPm() {
		return (pm);
	}

	public void setPm(PM p) {
		this.pm = p;
	}

	public Tier getTier() {
		return (tier);
	}

	public void setTier(Tier t) {
		this.tier = t;
	}

	public int getVcpu_consumption() {
		return vcpu_consumption;
	}

	public void setVcpu_consumption(int vcpu_consumption) {
		this.vcpu_consumption = vcpu_consumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getRam_consumption() {
		return ram_consumption;
	}

	public void setRam_consumption(int ram_consumption) {
		this.ram_consumption = ram_consumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	public int getDisk_consuption() {
		return disk_consumption;
	}

	public void setDisk_consumption(int disk_consumption) {
		this.disk_consumption = disk_consumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	// get children
	public ArrayList<Co> getCos() {
		return (cos);
	}

	public void setCos(ArrayList<Co> cos) {
		this.cos = cos;
	}

	/*************************************************************************************************/

	// add physical parent
	public void addPm(PM p) {
		if (this.getPm() == null) {
			this.setPm(p);
			p.getVms().add(this);
		} else
			System.out.println("Error: A dupplicate name exists!");

	}

	// change physical parent
	public void changePm(PM pm) {
		try {
			this.getPm().removeElt(this);
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pm.addElt(this);
	}

	// add logical parent
	public void addTier(Tier t) {
		if (t != null) {
			if (!t.getVms().contains(this)) {
				if (tier != null)
					try {
						tier.removeElt(this);
					} catch (UnsupportedOperationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				this.setTier(t);
				tier.getVms().add(this);
			}
		}
	}

	// add child
	@Override
	public void addElt(CloudResource o) {
		ResourcesDB db = ResourcesDB.getInstance();
		if (!db.containsKey(o.hashCode())) {
			cos.add((Co) o);
			db.put(o.hashCode(), o);
			System.out.println(((Co) o).getName() + " was added to " + this.getName());
		} else
			throw new UnsupportedOperationException("Error: A dupplicate name exists!");
		// System.out.println("Error: A dupplicate name exists!");

	}

	// remove child
	@Override
	void removeElt(CloudResource o) throws UnsupportedOperationException {
		ResourcesDB db = ResourcesDB.getInstance();
		List<Co> cos = this.getCos();
		if (cos.contains(o)) {
			cos.remove(o);
			db.remove(o.hashCode(), o);
			System.out.println(((Co) o).getName() + " has been deleted from " + this.getName());
		} else
			throw new UnsupportedOperationException("Not found");
	}

	// display children
	@Override
	public void display() {

		for (int i = 0; i < cos.size(); i++) {
			String name = cos.get(i).getName();
			System.out.println(
					"		| " + name + " , " + cos.get(i).getTypeCO() + " , " + cos.get(i).getOfferingCO().getName());
		}

	}

	/******************************************** Actions ********************************************/

	/************************
	 * Scale Up Infra ************************ increase the offering of this VM
	 ****************************************************************/

	@Override
	public void sui() throws UnsupportedOperationException {
		OfferingsInfraDB db = OfferingsInfraDB.getInstance();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println(this.getName() + ".sui()");
		OffInfra offeringVM = this.getOffVM();

		if (offeringVM != db.values().toArray()[db.size() - 1]) {
			if (db.containsValue(offeringVM)) {
				List<OffInfra> valueList = new ArrayList<OffInfra>(db.values());
				int pos = valueList.indexOf(offeringVM);
				System.out.println(pos);
				OffInfra nextOffering = (OffInfra) db.values().toArray()[pos + 1];
				this.setOffVM(nextOffering);
			}

		} else
			throw new UnsupportedOperationException(
					"Invalide operation : Offering of " + this.getName() + " = " + this.getOffVM().getName());

		/*
		 * OfferingVM offeringVM = this.getOfferingVM(); switch (offeringVM) {
		 * 
		 * case LARGE: throw new UnsupportedOperationException(
		 * "Invalide operation : Offering of " + this.getName() + " = LARGE");
		 * 
		 * case MEDIUM: this.setOfferingVM(OfferingVM.LARGE); break; case SMALL:
		 * this.setOfferingVM(OfferingVM.MEDIUM); break; default: break;
		 */

	}

	/************************
	 * Scale Down Infra ************************ decrease the offering of this
	 * VM
	 ******************************************************************/

	@Override
	public void sdi() throws UnsupportedOperationException {
		OfferingsInfraDB db = OfferingsInfraDB.getInstance();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println(this.getName() + ".sdi()");
		OffInfra offeringVM = this.getOffVM();

		if (offeringVM != db.values().toArray()[0]) {
			if (db.containsValue(offeringVM)) {
				List<OffInfra> valueList = new ArrayList<OffInfra>(db.values());
				int pos = valueList.indexOf(offeringVM);
				OffInfra prevOffering = (OffInfra) db.values().toArray()[pos - 1];
				this.setOffVM(prevOffering);
			}

		} else
			throw new UnsupportedOperationException(
					"Invalide operation : Offering of " + this.getName() + " = " + this.getOffVM().getName());

		/*
		 * OfferingVM offeringVM = this.getOfferingVM(); switch (offeringVM) {
		 * case LARGE: this.setOfferingVM(OfferingVM.MEDIUM); break; case
		 * MEDIUM: this.setOfferingVM(OfferingVM.SMALL); break;
		 * 
		 * case SMALL: throw new UnsupportedOperationException(
		 * "Invalide operation : Offering of " + this.getName() + " = SMALL");
		 * 
		 * default: break; }
		 */
	}

	/************************
	 * Scale Out Soft ************************ add (nbCo) CO with type (typeCO)
	 * for this VM
	 ****************************************************************/

	@Override
	public void sos(int nbCO, String typeCO) {

		OfferingsSoftDB db = OfferingsSoftDB.getInstance();
		System.out.println(this.getName() + ".sos(" + nbCO + ',' + typeCO + ")");
		int j = 1;
		for (int i = 1; i <= nbCO; i++) {

			Co co = new Co("CO" + hashCode() + j, typeCO, (OffSoft) db.values().toArray()[0]);
			this.addElt(co);
			j++;
		}

	}

	/************************
	 * Scale In Soft ************************ remove (nbCo) CO with type
	 * (typeCO) from this VM
	 ***************************************************************/

	@Override
	public void sis(int nbCO, String typeCO) throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sis(" + nbCO + ',' + typeCO + ")");
		if (this.getCos().size() >= nbCO) {
			for (int i = 0; i < nbCO; i++) {
				int j = 0;
				if (this.getCos().get(j).getTypeCO() == typeCO)

					this.removeElt(this.getCos().get(j));
				else
					throw new UnsupportedOperationException("Invalid operation type");

				j++;
			}
		} else
			throw new UnsupportedOperationException("Invalid operation ");

	}

	/************************
	 * Scale In Soft ************************ remove (co) from this from this VM
	 ***************************************************************/

	@Override
	public void sis(Co co) throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sii(" + co.getName() + ")");
		try {
			this.removeElt(co);
		} catch (UnsupportedOperationException e) {

			e.printStackTrace();
		}

	}

	/************************
	 * Scale Up Soft ************************ increase the offering of each CO
	 * in this VM
	 ***************************************************************/

	@Override
	public void sus() throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sus()");
		if (this.getCos().isEmpty())
			throw new UnsupportedOperationException("Invalide operation : " + this.getName() + " has 0 component");
		else {
			for (Co co : this.getCos()) {
				co.sus();
			}
		}
	}

	/************************
	 * Scale Down Soft ********************** increase the offering of each CO
	 * in this VM
	 ***************************************************************/
	@Override
	public void sds() throws UnsupportedOperationException {
		try {

			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(this.getName() + ".sds()");
		if (this.getCos().isEmpty())
			throw new UnsupportedOperationException("Invalide operation : " + this.getName() + " has 0 component");
		else {
			for (Co co : this.getCos()) {
				co.sds();
			}
		}
	}

	/**************************************
	 * Impossible actions
	 ***************************************/
	@Override
	public void soi(int nbResources, OffInfra offering) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sii(int nbResources) {
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

	public String toString() {
		return "VM : " + this.name + " [co :" + cos.size() + "] [pm : " + pm + "] [tier : " + tier + "]";
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 2;
	}
}

package fr.emn.elastuff.graph;

import java.util.ArrayList;
import java.util.List;

import javax.naming.SizeLimitExceededException;

import fr.emn.elastuff.offering.OffInfra;

public class Tier extends CloudResource {

	private ArrayList<VM> vms;
	private Appli appli;

	public Tier(String nom) {
		super(nom);
		vms = new ArrayList<VM>();

	}

	public Appli getAppli() {
		return (appli);
	}

	public void setAppli(Appli a) {
		this.appli = a;
	}

	public void addAppli(Appli a) {
		/*
		 * if (a != null) { if (a.getTiers().contains(this)) {
		 * appli.removeElt(this); this.setAppli(a); appli.getTiers().add(this);
		 * } }
		 */
		/*
		 * ResourcesDB db = ResourcesDB.getInstance(); if
		 * (!db.containsKey(a.hashCode())) { a.getTiers().add(this);
		 * db.put(a.hashCode(), o); System.out.println(((VM) o).getName() +
		 * " was added to " + this.getName()); } else System.out.println(
		 * "Error: A dupplicate name exists!");
		 */
	}

	public ArrayList<VM> getVms() {
		return (vms);
	}

	public VM find(String name, List<VM> list) {
		List<VM> vms = list;
		VM result = null;
		for (VM vm : vms) {
			if (vm.getName().equals(name)) {
				result = vm;
				return result;
			}
		}

		return result;
	}

	@Override
	public void addElt(CloudResource o) {
		ResourcesDB db = ResourcesDB.getInstance();
		if (!db.containsKey(o.hashCode())) {
			vms.add((VM) o);
			db.put(o.hashCode(), o);
			System.out.println(((VM) o).getName() + " was added to " + this.getName());
		} else
			throw new UnsupportedOperationException("Error: A dupplicate name exists!");
		// System.out.println("Error: A dupplicate name exists!");
	}

	@Override
	void removeElt(CloudResource o) throws UnsupportedOperationException {
		ResourcesDB db = ResourcesDB.getInstance();
		List<VM> vms = this.getVms();
		if (vms.contains(o)) {
			vms.remove(o);
			db.remove(o.hashCode(), o);
			System.out.println(((VM) o).getName() + " has been deleted from " + this.getName());
		} else
			throw new UnsupportedOperationException("Not found");
	}

	@Override
	public void display() {
		for (int i = 0; i < vms.size(); i++) {
			String name = vms.get(i).getName();
			/*
			 * System.out.println("	| " + name + " , " +
			 * vms.get(i).getOfferingVM()); vms.get(i).display();
			 */
			// new
			System.out.println("	| " + name + " , " + vms.get(i).getOffVM().getName());
			vms.get(i).display();
		}

	}

	/************************
	 * Scale Out Infra ************************ add (nbVM) VM with offering
	 * (OfferingVM) for this tier
	 *****************************************************************/
	@Override
	public void soi(int nbResources, OffInfra offering) {
		System.out.println(this.getName() + ".soi(" + nbResources + ',' + offering.getName() + ")");
		int j = 1;

		for (int i = 1; i <= nbResources; i++) {

			VM vm = new VM("VM" + hashCode() + j, offering);
			this.addElt(vm);
			j++;
		}

	}

	/************************
	 * Scale Out Infra ************************ add 1 VM with offering
	 * (OfferingVM) for this tier
	 *****************************************************************/
	@Override
	public void soi(OffInfra offering) {
		System.out.println(this.getName() + ".soi(" + offering.getName() + ")");

		this.soi(1, offering);
		/*
		 * VM vm = new VM("VM" + hashCode(), offering); this.addElt(vm);
		 */

	}

	/************************
	 * Scale Out Infra ************************ add VMs with offering
	 * (OfferingVM) for this tier
	 *****************************************************************/
	/*
	 * @Override public void soi() { System.out.println(this.getName()+".soi");
	 * int nb=this.getVms().size();
	 * 
	 * int j = 1;
	 * 
	 * for (int i = 1; i <= nb; i++) {
	 * 
	 * VM vm = new VM("VM" + hashCode() + j,OfferingVM.SMALL); this.addElt(vm);
	 * j++; } }
	 */

	/************************
	 * Scale In Infra ************************ remove (nbVM) VM from this tier
	 *****************************************************************/
	@Override
	public void sii(int nbResources) throws SizeLimitExceededException {
		System.out.println(this.getName() + ".sii(" + nbResources + ")");
		if (this.getVms().size() >= nbResources) {
			for (int i = 0; i < nbResources; i++) {
				int j = 0;
				try {
					this.removeElt(this.getVms().get(j));
				} catch (UnsupportedOperationException e) {

					e.printStackTrace();
				}
				j++;
			}
		} else
			throw new SizeLimitExceededException(
					"exceeded the limit : " + this.getName() + " has only " + this.getVms().size() + " VM(s)");

	}

	/************************
	 * Scale In Infra ************************ remove (vm) from this Tier
	 ***************************************************************/

	@Override
	public void sii(VM vm) {
		System.out.println(this.getName() + ".sii(" + vm.getName() + ")");
		try {
			this.removeElt(vm);
		} catch (UnsupportedOperationException e) {

			e.printStackTrace();
		}
	}

	/************************
	 * Scale In Infra ************************ remove VMs from this tier
	 *****************************************************************/

	public void sii() {
		System.out.println(this.getName() + ".sii");
		int nb = this.getVms().size();
		try {
			this.sii(nb);
		} catch (SizeLimitExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/************************
	 * Scale Up Infra ************************ increase the offering of each VM
	 * of this tier Large --> throw exception! Medium --> Large Small --> Medium
	 *****************************************************************/

	@Override
	public void sui() throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sui()");
		for (VM vm : this.getVms()) {
			vm.sui();
		}

	}

	/************************
	 * Scale Down Infra ************************ decrease the offering of each
	 * VM of this tier Large --> Medium Medium --> Small Small --> throw
	 * exception!
	 *****************************************************************/

	@Override
	public void sdi() throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sdi()");
		for (VM vm : this.getVms()) {
			vm.sdi();

		}
	}

	/************************
	 * Scale Out Soft ************************ add (nbCo) CO with type (typeCO)
	 * for each VM of this tier
	 ****************************************************************/
	@Override
	public void sos(int nbCO, String typeCO) {
		System.out.println(this.getName() + ".sos(" + nbCO + ',' + typeCO + ")");
		for (VM vm : this.getVms()) {
			vm.sos(nbCO, typeCO);
		}

	}

	/************************
	 * Scale In Soft ************************ remove (nbCo) CO with type
	 * (typeCO) of each VM of this tier
	 ***************************************************************/
	@Override
	public void sis(int nbCO, String typeCO) throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sis(" + nbCO + ',' + typeCO + ")");
		for (VM vm : this.getVms())
			vm.sis(nbCO, typeCO);

	}

	/************************
	 * Scale Up Soft ************************ increase the offering of each CO
	 * of all vms of this tier Mode1 --> Mode2 Mode2 --> Mode3 Mode3 --> throw
	 * exception!
	 **************************************************************/
	@Override
	public void sus() throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sus()");
		if (this.getVms().isEmpty())
			throw new UnsupportedOperationException("Invalide operation : " + this.getName() + " has 0 VM");
		else {
			for (VM vm : this.getVms()) {
				vm.sus();
			}
		}

	}

	/************************
	 * Scale Down Soft ************************ increase the offering of each CO
	 * of all vms of this tier Mode1 --> throw exception! Mode2 --> Mode1 Mode3
	 * --> Mode2
	 *****************************************************************/

	@Override
	public void sds() throws UnsupportedOperationException {
		System.out.println(this.getName() + ".sds()");
		if (this.getVms().isEmpty())
			throw new UnsupportedOperationException("Invalide operation : " + this.getName() + " has 0 VM");
		else {
			for (VM vm : this.getVms()) {
				vm.sds();
			}

		}
	}

	@Override
	public void sis(Co co) throws UnsupportedOperationException {

		for (VM vm : this.getVms()) {

			vm.sis(co);
		}

	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 3;
	}

	public String toString() {
		return "Tier " + name;
	}

}

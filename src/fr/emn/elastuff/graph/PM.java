package fr.emn.elastuff.graph;

import java.util.ArrayList;
import java.util.List;

import fr.emn.elastuff.offering.OffInfra;

public class PM extends CloudResource{

	private ArrayList<VM> vms;
	private Appli appli;

	int cpu_consumption;
	int ram_consumption;
	int disk_consumption;

	public PM(String nom /* , OfferingPM offeringPM */) {
		super(nom);
		// this.offeringPM = offeringPM;
		vms = new ArrayList<VM>();
		setAppli(appli);
	}

	public PM(String nom, Appli appli) {
		super(nom);

		vms = new ArrayList<VM>();
		this.appli = appli;
	}

	public int getCpu_consumption() {
		return cpu_consumption;
	}

	public void setCpu_consumption(int cpu_consumption) {
		this.cpu_consumption = cpu_consumption;
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

	public int getDisk_consumption() {
		return disk_consumption;
	}

	public void setDisk_consumption(int disk_consumption) {
		this.disk_consumption = disk_consumption;
		this.setChanged();
		this.notifyObservers(this);
	}

	public Appli getAppli() {
		return (appli);
	}

	public void setAppli(Appli a) {
		this.appli = a;
	}

	public void addAppli(Appli a) {
		if (a != null) {
			if (a.getPms().contains(this)) {
				if (appli != null)
					appli.removeElt(this);
				this.setAppli(a);
				appli.getPms().add(this);
			}
		}
	}

	public ArrayList<VM> getVms() {
		return (vms);
	}

	public void addElt(CloudResource c) {

		boolean Notfound = true;
		for (PM pm : this.getAppli().getPms()) {
			if (pm.getVms().contains(c))

				Notfound = false;
		}

		if (Notfound) {
			vms.add((VM) c);
			System.out.println(((VM) c).getName() + " was added to " + this.getName());
		} else
			throw new UnsupportedOperationException("Error: A dupplicate name exists!");
		// System.out.println("Error: A dupplicate name exists!");
	}

	void removeElt(CloudResource o) throws UnsupportedOperationException {
		ResourcesDB db = ResourcesDB.getInstance();
		List<VM> vms = this.getVms();
		for (VM vm : vms) {
			if (vm.getName().equals(o.getName())) {
				vms.remove(o);
				db.remove(o.hashCode(), o);
				System.out.println(((VM) o).getName() + " has been deleted from " + this.getName());
			} else
				throw new UnsupportedOperationException("Not found");
		}

	}

	@Override
	public void display() {
		for (int i = 0; i < vms.size(); i++) {
			String name = vms.get(i).getName();
			System.out.println("	| " + name + " , " + vms.get(i).getOffVM().getName());
			vms.get(i).display();
		}
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
	public void sus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sds() {
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

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 3;
	}

}

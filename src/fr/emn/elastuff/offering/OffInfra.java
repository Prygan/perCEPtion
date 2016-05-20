package fr.emn.elastuff.offering;






public class OffInfra {
	private String name;
	private final int vcpu;
	private final int ram;
	private final int disk;

	public OffInfra(String name,int vcpu, int ram, int disk) {
		this.name=name;
		this.vcpu = vcpu;
		this.ram = ram;
		this.disk = disk;
	}

	public String getName(){
		return name;
	}
	public int getVcpu() {
		return vcpu;
	}

	public int getRam() {
		return ram;
	}

	public int getDisk() {
		return disk;
	}
	
}

	


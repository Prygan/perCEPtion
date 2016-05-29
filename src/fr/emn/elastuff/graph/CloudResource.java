package fr.emn.elastuff.graph;

import javax.naming.SizeLimitExceededException;

import fr.emn.elastuff.offering.OffInfra;


public abstract class CloudResource {

	protected String name;
	
	public CloudResource(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void addElt(CloudResource cloudResource);
	abstract void removeElt(CloudResource cloudResource) throws UnsupportedOperationException;
	public abstract void display();

	//

	//public abstract void soi(int nbVM, OfferingVM offering);
	public abstract void soi(int nbVM, OffInfra offering);
	//public abstract void soi(OfferingVM offering);
	public abstract void soi(OffInfra offering);
	
		
	public abstract void sii(int nbResources) throws SizeLimitExceededException;
	public abstract void sii(VM vm) throws UnsupportedOperationException;

	//

	public abstract void sui() throws UnsupportedOperationException;
	public abstract void sdi() throws UnsupportedOperationException;

	//

	public abstract void sos(int nbCO, String typeCO);

	public abstract void sis(int nbCO, String typeCO) throws UnsupportedOperationException;
	public abstract void sis(Co co) throws UnsupportedOperationException;

	//

	public abstract void sus() throws UnsupportedOperationException;

	public abstract void sds() throws UnsupportedOperationException;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloudResource other = (CloudResource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	

}

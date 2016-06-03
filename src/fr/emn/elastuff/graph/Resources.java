package fr.emn.elastuff.graph;

import java.util.List;

import javax.naming.SizeLimitExceededException;


import fr.emn.elastuff.offering.OffInfra;


public class Resources extends CloudResource{
	
	List<CloudResource> list;
	
	public Resources(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	
	public List<CloudResource> getList() {
		return list;
	}




	public void setList(List<CloudResource> list) {
		this.list = list;
	}




	@Override
	public void addElt(CloudResource cloudResource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void removeElt(CloudResource cloudResource) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soi(int nbVM, OffInfra offering) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sii(int nbResources) throws SizeLimitExceededException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sii(VM vm) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sui() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sdi() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sos(int nbCO, String typeCO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sis(int nbCO, String typeCO) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sus() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sds() throws UnsupportedOperationException {
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
		int score = 0;
		for (CloudResource r : this.list)
			score += r.getScore();
		return score;
	}
	
	
	

}

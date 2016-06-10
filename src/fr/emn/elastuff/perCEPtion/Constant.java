package fr.emn.elastuff.perCEPtion;

public class Constant {
	
	private int defaultTTL = 6000 ;
	
	private static Constant constant ;
	
	private Constant(){}
	
	public static Constant getInstance(){
		if(constant==null){
			constant = new Constant();
		}
		return constant ;
	}
	
	public int getDefaultTTL(){
		return this.defaultTTL ;
	}

}

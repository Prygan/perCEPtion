package fr.emn.elastuff.perCEPtion;

public class SymptomRequest extends Request{
	
	private int ttl ;
	
	public SymptomRequest(String name, String command, Event event, int ttl) {
		super(name, command, event);
		this.ttl = ttl ;
	}

	public int getTTL(){
		return this.ttl ;
	}

}

package fr.emn.elastuff.perCEPtion;

public class Request {
	protected String command ;
	protected String name ;
	protected Event event ;
	
	public Request(String name, String command, Event event){
		this.command = command ;
		this.name = name ;
		this.event = event ;
	}
	
	public String getCommand() {
		return command;
	}

	public String getName() {
		return name;
	}

	public Event getEvent() {
		return event;
	}
	
	public String toString(){
		return "Request[" + this.name +", " + this.event +", " + this.command +"]";
	}

}

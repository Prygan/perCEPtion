package fr.emn.elastuff.perCEPtionTest;

import org.jdom.Attribute;
import org.jdom.Element;
import org.junit.Test;

import fr.emn.elastuff.perCEPtion.ParserXML;
import fr.emn.elastuff.perCEPtion.ParserXMLException;
import junit.framework.TestCase;

public class ParserXMLTest extends TestCase {
	
	@Test
	public void testCheckEmptyValue(){
		ParserXML parserXML = new ParserXML();
		Element e = new Element("request");
		e.setAttribute(new Attribute("name", "")) ;
		boolean thrown = false ;
		try {
			parserXML.checkRequest(e);
		} catch (ParserXMLException e1) {
			thrown = true ;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testCheckUncorrectValue(){
		ParserXML parserXML = new ParserXML();
		Element e = new Element("request");
		e.setAttribute(new Attribute("event", "Toto")) ;
		boolean thrown = false ;
		try {
			parserXML.checkRequest(e);
		} catch (ParserXMLException e1) {
			thrown = true ;
		}
		
		assertTrue(thrown);
	}

}

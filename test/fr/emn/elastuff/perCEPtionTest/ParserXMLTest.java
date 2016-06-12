package fr.emn.elastuff.perCEPtionTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.jdom.Attribute;
import org.jdom.Element;
import org.junit.Test;

import fr.emn.elastuff.perCEPtion.ParserXML;
import fr.emn.elastuff.perCEPtion.ParserXMLException;
import junit.framework.TestCase;

public class ParserXMLTest extends TestCase {
	
	@Test
	public void testCheckEmptyValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException{
		ParserXML parserXML = new ParserXML();
		Element e = new Element("request");
		e.setAttribute(new Attribute("name", "")) ;
		boolean thrown = false ;
		try {
			Method method = parserXML.getClass().getDeclaredMethod("checkRequest", Element.class);
			method.setAccessible(true);
			method.invoke(parserXML, e);
		} catch (InvocationTargetException e1) {
			if(e1.getCause() instanceof ParserXMLException)
				thrown = true ;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	public void testCheckUncorrectValue() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException{
		ParserXML parserXML = new ParserXML();
		Element e = new Element("request");
		e.setAttribute(new Attribute("event", "Toto")) ;
		boolean thrown = false ;
		try {
			Method method = parserXML.getClass().getDeclaredMethod("checkRequest", Element.class);
			method.setAccessible(true);
			method.invoke(parserXML, e);
		} catch (InvocationTargetException e1) {
			if(e1.getCause() instanceof ParserXMLException)
				thrown = true ;
		}
		
		assertTrue(thrown);
	}

}

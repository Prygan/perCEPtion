package fr.emn.elastuff.perCEPtion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ParserXML {

	public Collection<Request> read(String filepath) throws JDOMException, IOException, ParserXMLException {
		Collection<Request> requests = new ArrayList<Request>();

		SAXBuilder sxb = new SAXBuilder();
		// parsing
		Document document = sxb.build(new File(filepath));

		Element racine = document.getRootElement();
		List<?> listRequest = racine.getChildren();
		Iterator<?> i = listRequest.iterator();

		// loop on requests
		while (i.hasNext()) {
			Element element_request = (Element) i.next();

			// throw new ParserException if it is uncorrect
			checkRequest(element_request);

			Request request = new Request(element_request.getAttribute("name").getValue(),
					element_request.getAttribute("command").getValue(),
					Event.valueOf(element_request.getAttribute("event").getValue().toUpperCase()));
			requests.add(request);
		}

		return requests;
	}

	private void checkRequest(Element request) throws ParserXMLException {
		// Checks if attribute exists
		if (request.getAttribute("name") == null)
			throw new ParserXMLException("the attribute name in the xml file doesn't exist");

		if (request.getAttribute("command") == null)
			throw new ParserXMLException("the attribute command in the xml file doesn't exist");

		if (request.getAttribute("event") == null)
			throw new ParserXMLException("the attribute event in the xml file doesn't exist");

		// check if attribute value is correct
		if (request.getAttribute("name").getValue() == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");

		if (request.getAttribute("command").getValue() == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");

		String eventvalue = request.getAttribute("event").getValue();
		if (eventvalue == null)
			throw new ParserXMLException("the value event in the xml file is uncorrect");

		eventvalue = eventvalue.toUpperCase();

		try {
			Event.valueOf(eventvalue);
		} catch (IllegalArgumentException e) {
			throw new ParserXMLException("the event \"" + eventvalue + "\" doesn't exist");
		}
	}

	public static void main(String args[]) throws JDOMException, IOException, ParserXMLException {
		ParserXML pxml = new ParserXML();
		Collection<Request> l = pxml.read("Input/request.xml");
		for (Request r : l) {
			System.out.println(r);
		}
	}

}

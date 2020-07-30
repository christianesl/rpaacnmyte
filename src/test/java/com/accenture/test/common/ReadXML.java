package com.accenture.test.common;

import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadXML {

	private static final String DEFAULT_RESOURCE_PATHS = "src/test/java/com/telus/data/";

	public static String getValueXML(String xmlFileName, String tagName) {

		String xmlValue = null;
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(DEFAULT_RESOURCE_PATHS + xmlFileName));

			doc.getDocumentElement().normalize();
			String rootNodeName = doc.getDocumentElement().getNodeName();
			//Get the root node
			System.out.println("Root element of the doc is " + rootNodeName);
			
			//Get Tot of nodes with same tag (in case we will have iterations)
			NodeList mainNode = doc.getElementsByTagName(rootNodeName);
			int totNodes = mainNode.getLength();

			//Go through the nodes and see the element
			for (int i = 0; i < totNodes; i++) {

				Node firstValueNode = mainNode.item(i);

				if (firstValueNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstNodeElement = (Element) firstValueNode;

					NodeList firstNodeList = firstNodeElement.getElementsByTagName(tagName);

					Element firstNodeElements = (Element) firstNodeList.item(0);

					NodeList textFNList = firstNodeElements.getChildNodes();

					xmlValue = ((Node) textFNList.item(0)).getNodeValue().trim();
				}

			} // end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

			
		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

			
		} catch (Throwable t) {
			t.printStackTrace();

		}
		
		return xmlValue;

	}
}

package com.qa.util.XMLUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

public class XmlParser {
	
	public static String xmlString;

	public XmlParser(String xmlString) {
		this.xmlString = xmlString;
	}

	public static List<Element> getElements(String xpathString) {
		return getElements(xmlString, xpathString);
	}

	public static List<Element> getElements(String xmlString, String xpathString) {

		List<Element> al = new ArrayList<Element>();
		NodeList nodeList;
		try {
			nodeList = (NodeList) getXPathExpression(xpathString).evaluate(getInputSource(xmlString),
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			throw new RuntimeException("The xmlString is invalid::\n" + xmlString);
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			al.add((Element) nodeList.item(i));
		}

		return al;
	}

	public List<String> getAllTextContent(String xpathString) {
		List<String> l = new ArrayList<String>();
		for (Element e : getElements(xpathString)) {
			l.add(e.getTextContent());
		}
		return l;
	}

	public Element getElement(String xpathString) {
		return getElements(xpathString).get(0);
	}

	public static Element getElement(String xmlString, String xpathString) {
		return getElements(xmlString, xpathString).get(0);
	}

	public String getTextContent(String xpathString) {
		return getElement(xpathString).getTextContent();
	}

	public static HashMap<String, String> getAttributes(Element element) {
		HashMap<String, String> attributes = new HashMap<String, String>();
		NamedNodeMap attributeNodeList = element.getAttributes();
		for (int i = 0; i < attributeNodeList.getLength(); i++) {
			Node attr = attributeNodeList.item(i);
			attributes.put(attr.getNodeName(), attr.getNodeValue());
		}
		return attributes;
	}

	public HashMap<String, String> getAllAttributeValues(String xpathString) {
		Element e = getElement(xpathString);
		return getAttributes(e);
	}

	public static HashMap<String, String> getAllAttributeValues(String xmlString, String xpathString) {
		Element e = getElement(xmlString, xpathString);
		return getAttributes(e);
	}

	public String getAttributeValue(String xpathString, String attributeName) {
		return getAllAttributeValues(xpathString).get(attributeName);
	}

	public static XPathExpression getXPathExpression(String xpathString) {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		try {
			XPathExpression xPathExpression = xpath.compile(xpathString);
			return xPathExpression;
		} catch (XPathExpressionException e) {
			throw new RuntimeException("The xpath " + xpathString + " is invalid");
		}
	}

	public static InputSource getInputSource(String xmlString) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xmlString.getBytes());
		InputStreamReader reader;

		try {
			reader = new InputStreamReader(byteArrayInputStream, "UTF-8");
		} catch (UnsupportedEncodingException useEx) {
			throw new RuntimeException("the string " + xmlString + " could not be encoded to UTF-8");
		}

		InputSource inputSource = new InputSource(reader);
		inputSource.setEncoding("UTF-8");
		return inputSource;
	}

	public static ArrayList<String> getAllParentNode(Element element) {
		Node node = element;
		ArrayList<String> al = new ArrayList<String>();

		while (node != null) {
			al.add(node.getNodeName());
			node = node.getParentNode();
		}

		Collections.reverse(al);

		return al;
	}

	

}

package com;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

import com.BoardLocation.BoardLocationBuilder;

import java.io.*;
import java.util.ArrayList;

/**
 * XMLParser
 */
public class XMLParser {

    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document document;

    public XMLParser() {
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public BoardLocation[] buildBoardLocations(File XMLFile) {
        
        try {
            document = builder.parse(XMLFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList nList = document.getElementsByTagName("set");
        ArrayList<BoardLocation> locaitonList = new ArrayList<BoardLocation>();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                BoardLocationBuilder builder = new BoardLocationBuilder();
                builder.neighbors(getNeighbors(
                                  eElement.getElementsByTagName("neighbors")))
                       .area(getArea(eElement.getElementsByTagName("area")))
        }
    }

    private String[] getNeighbors(NodeList nList) {
        String[] neighbors = new String[nList.getLength()];
        Element temp = (Element) nList.item(0);
        nList = temp.getElementsByTagName("neighbor");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                neighbors[i] = eElement.getAttribute("name");

            }
        }

        return neighbors;
    }

    private Area getArea(NodeList nList) {
        Element eElement = (Element) nList.item(0);
        return new Area(Integer.parseInt(eElement.getAttribute("x")), 
                        Integer.parseInt(eElement.getAttribute("y")),
                        Integer.parseInt(eElement.getAttribute("w")), 
                        Integer.parseInt(eElement.getAttribute("h")));
    }

    /*public Card[] buildCards(File XMLFile) {

    }*/

    private Part[] buildParts() {
        return new Part[1];
    }
}
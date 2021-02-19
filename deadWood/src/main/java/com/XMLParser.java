package com;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

import com.BoardLocation.BoardLocationBuilder;
import com.Card.CardBuilder;
import com.Part.PartBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * XMLParser
 */
public class XMLParser {

    private DocumentBuilderFactory factory;
    private DocumentBuilder        builder;
    private Document               document;

    //TODO:could this be a singleton/static class? No real need for
    //     multiple parsers/doesn't utilize most functions of an 
    //     object, is more just a collection of methods
    public XMLParser() {
        factory = DocumentBuilderFactory.newInstance();

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /*
     * Builds an array of BoardLocations from a correctly formatted XML file
     */
    public BoardLocation[] buildBoardLocations(File XMLFile) {
        
        try {
            document = builder.parse(XMLFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element              root         = document
                                                .getDocumentElement();
        NodeList             nList        = root
                                                .getElementsByTagName("set");
        List<BoardLocation>  locationList = new ArrayList<BoardLocation>();
        BoardLocationBuilder builder      = new BoardLocationBuilder();

        

        // iterates through all Set elements in XML
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                Element temp     = (Element) eElement
                                             .getElementsByTagName("parts")
                                             .item(0);               

                // build current BoardLocation and add to arraylist
                builder.name(
                                eElement.getAttribute("name"))
                       .neighbors(getNeighbors(
                                eElement.getElementsByTagName("neighbors")))
                       .area(buildArea(
                                eElement.getElementsByTagName("area")))
                       .takeAreas(getTakeAreas(
                                eElement.getElementsByTagName("takes")))
                       .parts(buildParts(
                                temp.getElementsByTagName("part")));

                locationList.add(builder.build());
            }
        }
        return (BoardLocation[]) 
            locationList.toArray(new BoardLocation[locationList.size()]);
    }

    /*
     * Retrieves neighbor values from XML file
     */ 
    private String[] getNeighbors(NodeList nList) {
        Element temp       = (Element) nList.item(0);
        nList              = temp.getElementsByTagName("neighbor");
        String[] neighbors = new String[nList.getLength()];

        // iterates through all neighbor nodes
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                neighbors[i]     = eElement.getAttribute("name");
            }
        }
        return neighbors;
    }

    /*
     * Retrieves area values from XML file to create an area object
     */
    private Area buildArea(NodeList nList) {
        Element eElement = (Element) nList.item(0);
        return new Area(Integer.parseInt(eElement.getAttribute("x")), 
                        Integer.parseInt(eElement.getAttribute("y")),
                        Integer.parseInt(eElement.getAttribute("w")), 
                        Integer.parseInt(eElement.getAttribute("h")));
    }

    /*
     * Retrieves takeArea values from XML file 
     */
    private Area[] getTakeAreas(NodeList nList) {
        Element temp     = (Element) nList.item(0);
        nList            = temp.getElementsByTagName("take");
        Area[] takeAreas = new Area[nList.getLength()];

        for (int i = nList.getLength()-1; i >= 0; i--) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                takeAreas[i]     = buildArea(eElement
                                           .getElementsByTagName("area"));
            }
        }
        return takeAreas;
    }

    public Card[] buildCards(File XMLFile) {
        
        try {
            document = builder.parse(XMLFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NodeList        nList    = document.getElementsByTagName("cards");
        Element         decLevel = (Element) nList.item(0);
        List<Card> CardList = new ArrayList<Card>();
        CardBuilder     builder  = new CardBuilder();

        nList = decLevel.getElementsByTagName("card");

        // iterates through all card elements in XML
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;          

                // build current Card and add to arraylist
                builder.name(
                                eElement.getAttribute("name"))
                       .parts(buildParts(
                                eElement.getElementsByTagName("part")))
                       .budget(Integer.parseInt(
                                eElement.getAttribute("budget")))
                       .image(new File("../../../../res/images/" +
                                eElement.getAttribute("img")))
                       .sceneNum(getSceneNum(
                                eElement.getElementsByTagName("scene")))
                       .description(getDescription(
                                eElement.getElementsByTagName("scene")))
                       .parts(buildParts(
                                eElement.getElementsByTagName("part")));

                CardList.add(builder.build());
            }
        }
        return (Card[]) CardList.toArray(new Card[CardList.size()]);
    }

    private int getSceneNum(NodeList nList) {
        return Integer.parseInt(((Element) nList.item(0))
                      .getAttribute("number"));
    }

    private String getDescription(NodeList nList) {
        return nList.item(0).getTextContent();
    }

    /*
     * Builds Part objects as a part of a Card or BoardLocation object
     */
    private Part[] buildParts(NodeList nList) {
        List<Part> parts   = new ArrayList<Part>();
        PartBuilder     builder = new PartBuilder();

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                builder.name(eElement.getAttribute("name"))
                       .level(Integer.parseInt(eElement.getAttribute("level")))
                       .area(buildArea(eElement.getElementsByTagName("area")))
                       .line(eElement.getElementsByTagName("line")
                                     .item(0)
                                     .getTextContent());

                       parts.add(builder.build());
            }
        }
        return parts.toArray(new Part[parts.size()]);
    }
}
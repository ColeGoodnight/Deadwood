package com;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

import com.BoardLocation.BoardLocationBuilder;
import com.Card.CardBuilder;
import com.Part.PartBuilder;
import com.Upgrade.UpgradeBuilder;

import java.awt.*;
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
        Node                 node;

        // iterates through all Set elements in XML
        for (int i = 0; i < nList.getLength(); i++) {
            node = nList.item(i);

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
                       .rectangle(buildRectangle(
                                eElement.getElementsByTagName("area")))
                       .takes(getTakes(
                                eElement.getElementsByTagName("takes")))
                       .parts(buildParts(
                                temp.getElementsByTagName("part"), false));

                locationList.add(builder.build());
            }
        }

        nList = root.getElementsByTagName("trailer");
        node  = nList.item(0);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;           

            // build unique location Trailers and add to arraylist
            builder.name("Trailers")
                   .neighbors(getNeighbors(
                            eElement.getElementsByTagName("neighbors")))
                   .rectangle(buildRectangle(
                            eElement.getElementsByTagName("area")));

            locationList.add(builder.build());
        }

        nList = root.getElementsByTagName("office");
        node  = nList.item(0);

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;           

            // build unique location office and add to arraylist
            builder.name("Casting Office")
                   .neighbors(getNeighbors(
                            eElement.getElementsByTagName("neighbors")))
                   .rectangle(buildRectangle(
                            eElement.getElementsByTagName("area")));
                            
            locationList.add(builder.build());
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
     * Retrieves rectangle values from XML file to create an rectangle object
     */
    private Rectangle buildRectangle(NodeList nList) {
        Element eElement = (Element) nList.item(0);
        return new Rectangle(Integer.parseInt(eElement.getAttribute("x")),
                        Integer.parseInt(eElement.getAttribute("y")),
                        Integer.parseInt(eElement.getAttribute("w")), 
                        Integer.parseInt(eElement.getAttribute("h")));
    }

    /*
     * Retrieves takeRectangle values from XML file
     */
    private Take[] getTakes(NodeList nList) {
        Element temp  = (Element) nList.item(0);
        nList         = temp.getElementsByTagName("take");
        Take[]  takes = new Take[nList.getLength()];

        for (int i = nList.getLength()-1; i >= 0; i--) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                takes[i]     = new Take(buildRectangle(eElement
                                           .getElementsByTagName("area")), false);
            }
        }
        return takes;
    }

    /*
     * Builds an array of Cards from a correctly formatted XML file
     */
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
        List<Card>      CardList = new ArrayList<Card>();
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
                       .budget(Integer.parseInt(
                                eElement.getAttribute("budget")))
                       .image(new File("res/images/" +
                                eElement.getAttribute("img")))
                       .sceneNum(getSceneNum(
                                eElement.getElementsByTagName("scene")))
                       .description(getDescription(
                                eElement.getElementsByTagName("scene")))
                       .parts(buildParts(
                                eElement.getElementsByTagName("part"), true));

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
    private Part[] buildParts(NodeList nList, boolean onCard) {
        List<Part>  parts   = new ArrayList<Part>();
        PartBuilder builder = new PartBuilder();

        // iterates through all Part elements in nList
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                // build current Part and adds to arraylist
                builder.name(eElement.getAttribute("name"))
                       .level(Integer.parseInt(eElement.getAttribute("level")))
                       .rectangle(buildRectangle(eElement.getElementsByTagName("area")))
                       .line(eElement.getElementsByTagName("line")
                                     .item(0)
                                     .getTextContent())
                       .onCard(onCard);                        

                       parts.add(builder.build());
            }
        }
        return parts.toArray(new Part[parts.size()]);
    }

    public Upgrade[] buildUpgrades(File XMLFile) {
        
        try {
            document = builder.parse(XMLFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element        root     = document.getDocumentElement();
        NodeList       nList    = root.getElementsByTagName("office"); 
        Element        node     = (Element) nList.item(0);
        nList                   = node.getElementsByTagName("upgrades");
        node                    = (Element) nList.item(0);
        nList                   = node.getElementsByTagName("upgrade");                            
        List<Upgrade>  upgrades = new ArrayList<Upgrade>();
        UpgradeBuilder builder  = new UpgradeBuilder();

        // iterates through all Upgrade elements in nList
        for (int i = 0; i < nList.getLength(); i++) {
            node = (Element) nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;

                // build current Upgrade and adds to arraylist
                builder.level(Integer.parseInt(eElement.getAttribute("level")))
                       .amt(Integer.parseInt(eElement.getAttribute("amt")))
                       .rectangle(buildRectangle(eElement.getElementsByTagName("area")))
                       .currency(eElement.getAttribute("currency"));

                upgrades.add(builder.build());
            }
        }
        return upgrades.toArray(new Upgrade[upgrades.size()]);
    }
}
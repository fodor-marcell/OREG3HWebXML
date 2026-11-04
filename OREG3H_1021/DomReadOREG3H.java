package OREG3H;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DomReadOREG3H {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File("B:\\workspace\\DomParserOREG3H\\src\\main\\java\\OREG3H\\OREG3H_xml.xml");

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        Document document = docBuilder.parse(xmlFile);

        document.getDocumentElement().normalize();

        System.out.println("A gyökér elem: " + document.getDocumentElement().getNodeName());

        NodeList nList = document.getElementsByTagName("etterem");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String id = elem.getAttribute("ekod");
                String foreignId = elem.getAttribute("e_r");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("varos").item(0);
                String city = node2.getTextContent();

                Node node3 = elem.getElementsByTagName("utca").item(0);
                String street = node3.getTextContent();

                Node node4 = elem.getElementsByTagName("hazszam").item(0);
                String houseNumber = node4.getTextContent();

                Node node5 = elem.getElementsByTagName("csillag").item(0);
                String star = node5.getTextContent();

                String address = city + ", " + street + ", " + houseNumber;

                System.out.println("Étterem ID: " + id);
                System.out.println("Étterem idegen kulcs: " + foreignId);
                System.out.println("Étterem neve: " + name);
                System.out.println("Étterem címe: " + address);
                System.out.println("Étterem csillaga: " + star);
            }
        }

        NodeList nList2 = document.getElementsByTagName("foszakacs");

        for (int i = 0; i < nList2.getLength(); i++) {
            Node node = nList2.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;

                String id = elem.getAttribute("fkod");
                String foreignId = elem.getAttribute("e_f");

                Node node1 = elem.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                NodeList nodeList = elem.getElementsByTagName("vegzettseg");
                List<String> professions = new ArrayList<>();

                for (int a = 0; a < nodeList.getLength(); a++) {
                    Node professionNode = nodeList.item(a);
                        Element elem2 = (Element) professionNode;
                        String degree = elem2.getTextContent();
                        professions.add(degree);
                }

                System.out.println("Főszakács ID: " + id);
                System.out.println("Főszakács foreign ID: " + foreignId);
                System.out.println("Főszakács neve: " + name);
                System.out.println("Főszakács kora: " + age);
                System.out.print("Főszakács végzettsége(i): " + professions.stream().collect(Collectors.joining(", ")));
                System.out.println();
            }
        }

        NodeList nList3 = document.getElementsByTagName("szakacs");

        for (int i = 0; i < nList3.getLength(); i++) {
            Node node = nList3.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getAttribute("szkod");
                String foreignId = element.getAttribute("e_sz");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = element.getElementsByTagName("reszleg").item(0);
                String section = node2.getTextContent();

                Node node3 = element.getElementsByTagName("vegzettseg").item(0);
                String profession = node3.getTextContent();

                System.out.println("Szakács ID: " + id);
                System.out.println("Szakács foreign ID: " + foreignId);
                System.out.println("Szakács neve: " + name);
                System.out.println("Szakács részlege: " + section);
                System.out.println("Szakács végzettsége: " + profession);
            }
        }

        NodeList nList4 = document.getElementsByTagName("gyakornok");

        for (int i = 0; i < nList4.getLength(); i++) {
            Node node = nList4.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getAttribute("gykod");
                String foreignId = element.getAttribute("e_gy");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = element.getElementsByTagName("kezdete").item(0);
                String startDate = node2.getTextContent();

                Node node3 = element.getElementsByTagName("idotartama").item(0);
                String duration = node3.getTextContent();

                NodeList shiftNodes = element.getElementsByTagName("muszak");
                List<String> shifts = new ArrayList<>();

                for (int a = 0; a < shiftNodes.getLength(); a++) {
                    shifts.add(shiftNodes.item(a).getTextContent());
                }

                System.out.println("Gyakornok ID: " + id);
                System.out.println("Gyakornok foreign ID: " + foreignId);
                System.out.println("Gyakornok neve: " + name);
                System.out.println("Gyakorlat kezdete: " + startDate);
                System.out.println("Gyakorlat időtartama: " + duration);
                System.out.println("Gyakornok műszakja(i): " + shifts.stream().collect(Collectors.joining(", ")));
            }
        }

        NodeList nList5 = document.getElementsByTagName("vendeg");

        for (int i = 0; i < nList5.getLength(); i++) {
            Node node = nList5.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String id = element.getAttribute("vkod");

                Node node1 = element.getElementsByTagName("nev").item(0);
                String name = node1.getTextContent();

                Node node2 = element.getElementsByTagName("eletkor").item(0);
                String age = node2.getTextContent();

                Node node3 = element.getElementsByTagName("varos").item(0);
                String city = node3.getTextContent();

                Node node4 = element.getElementsByTagName("utca").item(0);
                String street = node4.getTextContent();

                Node node5 = element.getElementsByTagName("hazszam").item(0);
                String houseNumber = node5.getTextContent();

                String address = city + ", " + street + ", " + houseNumber;

                System.out.println("Vendég ID: " + id);
                System.out.println("Vendég neve: " + name);
                System.out.println("Vendég kora: " + age);
                System.out.println("Vendég címe: " + address);
            }
        }

        NodeList nList6 = document.getElementsByTagName("rendeles");

        for (int i = 0; i < nList6.getLength(); i++) {
            Node node = nList6.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                String restaurantId = element.getAttribute("e_v_e");
                String guestId = element.getAttribute("e_v_v");

                Node node1 = element.getElementsByTagName("osszeg").item(0);
                String amount = node1.getTextContent();

                Node node2 = element.getElementsByTagName("etel").item(0);
                String food = node2.getTextContent();

                System.out.println("Rendelés étterem ID: " + restaurantId);
                System.out.println("Rendelés vendég ID: " + guestId);
                System.out.println("Rendelés összege: " + amount);
                System.out.println("Rendelt étel: " + food);
            }
        }
    }

}

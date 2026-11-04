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

public class DomQueryOREG3H {

    public static void main(String[] args) {
        File xmlFile = new File(args.length > 0 ? args[0] : "C:\\Users\\marcell.fodor\\Downloads\\webxml\\OREG3HWebXML\\OREG3H_1104\\VendeglatasOREG3H.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            System.out.println();
            System.out.println("a.) Szakácsok, akiknek a végzettség között van a szakiskola:");
            NodeList cookList = document.getElementsByTagName("szakacs");
            for (int i = 0; i < cookList.getLength(); i++) {
                Element cook = (Element) cookList.item(i);
                NodeList qualifications = cook.getElementsByTagName("vegzettseg");
                for (int j = 0; j < qualifications.getLength(); j++) {
                    if ("Szakiskola".equals(qualifications.item(j).getTextContent())) {
                        String id = cook.getAttribute("szkod");
                        String name = getSingleChildText(cook, "nev");
                        String section = getSingleChildText(cook, "reszleg");
                        String degree = getSingleChildText(cook, "vegzettseg");
                        String restaurantId = cook.getAttribute("e_sz");
                        String restaurantName = getRestaurantNameById(document, restaurantId);
                        String restaurantCity = getRestaurantDetailById(document, restaurantId, "varos");
                        String restaurantStreet = getRestaurantDetailById(document, restaurantId, "utca");
                        String restaurantHouseNumber = getRestaurantDetailById(document, restaurantId, "hazszam");
                        System.out.println("- ID: " + id);
                        System.out.println("  Név: " + name);
                        System.out.println("  Részleg: " + section);
                        System.out.println("  Végzettség: " + degree);
                        System.out.println("  Étterem: " + restaurantName + " (" + restaurantId + ")");
                        System.out.println("  Étterem ekod: " + restaurantId);
                        System.out.println("  Város: " + restaurantCity);
                        System.out.println("  Utca: " + restaurantStreet);
                        System.out.println("  Házszám: " + restaurantHouseNumber);
                        break;
                    }
                }
            }

            System.out.println("---------------------------------------------");
            System.out.println("b.) Éttermek, amelyek öt csillagosak:");
            NodeList restaurantList = document.getElementsByTagName("etterem");
            boolean foundFiveStarRestaurant = false;
            for (int i = 0; i < restaurantList.getLength(); i++) {
                Element restaurant = (Element) restaurantList.item(i);
                String starCount = getSingleChildText(restaurant, "csillag");
                if ("5".equals(starCount)) {
                    foundFiveStarRestaurant = true;
                    String restaurantId = restaurant.getAttribute("ekod");
                    String restaurantName = getSingleChildText(restaurant, "nev");
                    String city = getNestedText(restaurant, "cim", "varos");
                    String street = getNestedText(restaurant, "cim", "utca");
                    String houseNumber = getNestedText(restaurant, "cim", "hazszam");
                    System.out.println("- ID: " + restaurantId);
                    System.out.println("  Név: " + restaurantName);
                    System.out.println("  Város: " + city);
                    System.out.println("  Utca: " + street);
                    System.out.println("  Házszám: " + houseNumber);
                }
            }
            if (!foundFiveStarRestaurant) {
                System.out.println("- Nincs 5 csillagos étterem.");
            }

            System.out.println("---------------------------------------------");
            System.out.println("c.) Gyakornokok, akik délutánra vannak beosztva:");
            NodeList traineeList = document.getElementsByTagName("gyakornok");
            for (int i = 0; i < traineeList.getLength(); i++) {
                Element trainee = (Element) traineeList.item(i);
                NodeList shifts = trainee.getElementsByTagName("muszak");
                String id = trainee.getAttribute("gykod");
                String name = getSingleChildText(trainee, "nev");
                String startDate = getSingleChildText(trainee, "kezdete");
                String duration = getSingleChildText(trainee, "idotartama");
                String restaurantId = trainee.getAttribute("e_gy");
                String restaurantName = getRestaurantNameById(document, restaurantId);
                String restaurantCity = getRestaurantDetailById(document, restaurantId, "varos");
                String restaurantStreet = getRestaurantDetailById(document, restaurantId, "utca");
                String restaurantHouseNumber = getRestaurantDetailById(document, restaurantId, "hazszam");

                for (int j = 0; j < shifts.getLength(); j++) {
                    if ("Délután".equals(shifts.item(j).getTextContent())) {
                        System.out.println("- ID: " + id);
                        System.out.println("  Név: " + name);
                        System.out.println("  Kezdete: " + startDate);
                        System.out.println("  Időtartam: " + duration);
                        System.out.println("  Étterem: " + restaurantName + " (" + restaurantId + ")");
                        System.out.println("  Étterem ekod: " + restaurantId);
                        System.out.println("  Város: " + restaurantCity);
                        System.out.println("  Utca: " + restaurantStreet);
                        System.out.println("  Házszám: " + restaurantHouseNumber);
                        System.out.println();
                        break;
                    }
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {}
    }

    private static String getSingleChildText(Element parent, String tagName) {
        NodeList children = parent.getElementsByTagName(tagName);
        if (children.getLength() > 0) {
            return children.item(0).getTextContent();
        }
        return "";
    }

    private static String getNestedText(Element parent, String parentTag, String childTag) {
        NodeList parents = parent.getElementsByTagName(parentTag);
        if (parents.getLength() > 0) {
            Element container = (Element) parents.item(0);
            NodeList children = container.getElementsByTagName(childTag);
            if (children.getLength() > 0) {
                return children.item(0).getTextContent();
            }
        }
        return "";
    }

    private static String getRestaurantNameById(Document document, String restaurantId) {
        NodeList restaurants = document.getElementsByTagName("etterem");
        for (int i = 0; i < restaurants.getLength(); i++) {
            Element restaurant = (Element) restaurants.item(i);
            if (restaurantId.equals(restaurant.getAttribute("ekod"))) {
                return getSingleChildText(restaurant, "nev");
            }
        }
        return "Ismeretlen étterem";
    }

    private static String getRestaurantDetailById(Document document, String restaurantId, String detailTag) {
        NodeList restaurants = document.getElementsByTagName("etterem");
        for (int i = 0; i < restaurants.getLength(); i++) {
            Element restaurant = (Element) restaurants.item(i);
            if (restaurantId.equals(restaurant.getAttribute("ekod"))) {
                Element detail = (Element) restaurant.getElementsByTagName(detailTag).item(0);
                if (detail != null) {
                    return detail.getTextContent();
                }
                return "";
            }
        }
        return "";
    }
}

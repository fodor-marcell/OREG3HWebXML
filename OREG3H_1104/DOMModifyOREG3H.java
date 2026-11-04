package OREG3H;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMModifyOREG3H {

    public static void main(String[] args) {
        File xmlFile = new File(args.length > 0 ? args[0] : "C:\\Users\\marcell.fodor\\Downloads\\webxml\\OREG3HWebXML\\OREG3H_1104\\VendeglatasOREG3H.xml");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("vendeg");
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node vendeg = nList.item(i);

                NodeList list = vendeg.getChildNodes();

                for (int j = 0; j < list.getLength(); j++) {

                    Node node = list.item(j);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        if ("eletkor".equals(element.getNodeName())) {
                            if ("26".equals(element.getTextContent())) {
                                element.setTextContent("22");
                            }
                        }
                        if ("nev".equals(element.getNodeName())) {
                            if ("Kiss Hajnalka".equals(element.getTextContent())) {
                                element.setTextContent("Kiss Piroska");
                            }
                        }
                    }
                }
            }

            NodeList nList2 = document.getElementsByTagName("gyakornok");

            for (int i = 0; i < nList2.getLength(); i++) {
                Node intern = nList2.item(i);
                if (intern.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) intern;

                    element.setAttribute("e_gy", "e3");
                }
            }

            NodeList orders = document.getElementsByTagName("rendeles");
            for (int i = orders.getLength() - 1; i >= 0; i--) {
                Node order = orders.item(i);
                order.getParentNode().removeChild(order);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.transform(new DOMSource(document), new StreamResult(System.out));
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }

}


package org.xpathoreg3h;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class xPathOREG3H {

    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse("B:\\workspace\\xPathOREG3H\\src\\main\\java\\studentOREG3H.xml");

            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();

            String kifejezes = "/class/student";

            NodeList hallgatok = (NodeList) xPath
                    .compile(kifejezes)
                    .evaluate(document, XPathConstants.NODESET);

            System.out.println("Találatok száma: "
                    + hallgatok.getLength());

            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("studentOREG3H_output.xml"))) {

                writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
                writer.write("<hallgatok>\n");

                for (int i = 0; i < hallgatok.getLength(); i++) {

                    Node node = hallgatok.item(i);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) node;

                        String id = element.getAttribute("id");

                        String keresztnev = element
                                .getElementsByTagName("keresztnev")
                                .item(0).getTextContent();

                        String vezeteknev = element
                                .getElementsByTagName("vezeteknev")
                                .item(0).getTextContent();

                        String becenev = element
                                .getElementsByTagName("becenev")
                                .item(0).getTextContent();

                        String kor = element
                                .getElementsByTagName("kor")
                                .item(0).getTextContent();

                        System.out.println("Hallgató ID: " + id);
                        System.out.println("Keresztnév: " + keresztnev);
                        System.out.println("Vezetéknév: " + vezeteknev);
                        System.out.println("Becenév: " + becenev);
                        System.out.println("Kor: " + kor);
                        System.out.println("-------------------");

                        writer.write("  <hallgato id=\"" + id + "\">\n");
                        writer.write("    <keresztnev>" + keresztnev + "</keresztnev>\n");
                        writer.write("    <vezeteknev>" + vezeteknev + "</vezeteknev>\n");
                        writer.write("    <becenev>" + becenev + "</becenev>\n");
                        writer.write("    <kor>" + kor + "</kor>\n");
                        writer.write("  </hallgato>\n");
                    }
                }

                writer.write("</hallgatok>\n");
            }

            System.out.println("XML fájl elkészült!");

        } catch (ParserConfigurationException |
                 SAXException |
                 IOException |
                 XPathExpressionException e) {

            e.printStackTrace();
        }
    }
}

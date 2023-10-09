import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PuntoA {
    public static void main(String[] args) {
        try {
            // Crear el analizador de XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el archivo XML
            Document document = builder.parse(new File("C:/Users/marco/IdeaProjects/XML_SAX/src/cd_catalog.xml"));

            // Obtener la lista de nodos CD
            NodeList cdList = document.getElementsByTagName("CD");

            // Crear una lista para almacenar los precios
            ArrayList<Double> prices = new ArrayList<>();

            // Recorrer los nodos CD y extraer los precios
            for (int i = 0; i < cdList.getLength(); i++) {
                Element cd = (Element) cdList.item(i);
                Element priceElement = (Element) cd.getElementsByTagName("PRICE").item(0);
                String priceStr = priceElement.getTextContent();
                double price = Double.parseDouble(priceStr);
                prices.add(price);
            }

            // Calcular la media
            double sum = 0;
            for (double price : prices) {
                sum += price;
            }
            double mean = sum / prices.size();

            // Calcular la desviación estándar
            double sumSquaredDifferences = 0;
            for (double price : prices) {
                double difference = price - mean;
                sumSquaredDifferences += difference * difference;
            }
            double variance = sumSquaredDifferences / prices.size();
            double standardDeviation = Math.sqrt(variance);

            System.out.println("Media de precios: " + mean);
            System.out.println("Desviación estándar de precios: " + standardDeviation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



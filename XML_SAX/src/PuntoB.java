import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class PuntoB extends JFrame {
    private JTable table;

    public PuntoB() {
        setTitle("Catálogo de CDs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("TITLE");
        model.addColumn("ARTIST");
        model.addColumn("COUNTRY");
        model.addColumn("COMPANY");
        model.addColumn("PRICE");
        model.addColumn("YEAR");

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Cargar y mostrar los datos del archivo XML
        loadXMLData();

        pack();
        setLocationRelativeTo(null);
    }

    private void loadXMLData() {
        try {
            // Crear el analizador de XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el archivo XML
            Document document = builder.parse(new File("C:/Users/marco/IdeaProjects/XML_SAX/src/cd_catalog.xml"));

            // Obtener la lista de nodos CD
            NodeList cdList = document.getElementsByTagName("CD");

            // Recorrer los nodos CD y agregar los datos a la tabla
            for (int i = 0; i < cdList.getLength(); i++) {
                Element cd = (Element) cdList.item(i);
                String title = cd.getElementsByTagName("TITLE").item(0).getTextContent();
                String artist = cd.getElementsByTagName("ARTIST").item(0).getTextContent();
                String country = cd.getElementsByTagName("COUNTRY").item(0).getTextContent();
                String company = cd.getElementsByTagName("COMPANY").item(0).getTextContent();
                String price = cd.getElementsByTagName("PRICE").item(0).getTextContent();
                String year = cd.getElementsByTagName("YEAR").item(0).getTextContent();

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new String[]{title, artist, country, company, price, year});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PuntoB viewer = new PuntoB();
            viewer.setVisible(true);
        });
    }
}

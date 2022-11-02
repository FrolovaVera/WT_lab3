import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;



public class Database {
    String name,secname,age,cource;
    Document document;
    public Database(String name, String secname, String age,String cource){
        this.name=name;
        this.secname=secname;
        this.age=age;
        this.cource=cource;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse("database.xml");
        }
        catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);}
        catch (SAXException ex) {
            ex.printStackTrace(System.out);}
        catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void showDocument(BufferedWriter writer){
        Node root = document.getDocumentElement();
        NodeList books = root.getChildNodes();
        for (int i = 0; i < books.getLength(); i++) {
            Node book = books.item(i);
            // Если нода не текст, то это книга - заходим внутрь
            if (book.getNodeType() != Node.TEXT_NODE) {
                NodeList bookProps = book.getChildNodes();
                for(int j = 0; j < bookProps.getLength(); j++) {
                    Node bookProp = bookProps.item(j);
                        try {
                            if (bookProp.getNodeType() != Node.TEXT_NODE) {
                                writer.write(bookProp.getNodeName() + ":" + bookProp.getChildNodes().item(0).getTextContent());
                                writer.newLine();
                                writer.flush();
                            }
                        }
                        catch (IOException e){throw new RuntimeException(e);}
                }
                try {
                writer.write("===========>>>>");
                    writer.newLine();
                    writer.flush();
                }
                catch (IOException e){throw new RuntimeException(e);}
            }
        }
        try {
            writer.write("break");
            writer.newLine();
            writer.flush();

        }
        catch (IOException e){throw new RuntimeException(e);}

    }
    public void addNewDocument(String name,String secondName,String age,String cource) {
        Node root = document.getDocumentElement();

        Element student = document.createElement("Student");
        Element nameStudent = document.createElement("Name");
        //nameStudent.setTextContent("Vname");
        Text textname = document.createTextNode(name);
        nameStudent.appendChild(textname);

        Element secondNameStudent = document.createElement("SecondName");
        secondNameStudent.setTextContent(secondName);

        Element ageStudent = document.createElement("Age");
        ageStudent.setTextContent(age);

        Element courceStudent = document.createElement("Cource");
        courceStudent.setTextContent(cource);

        Element cost = document.createElement("Cost");
        cost.setTextContent("499");
        cost.setAttribute("currency", "RUB");

        student.appendChild(nameStudent);
        student.appendChild(secondNameStudent);
        student.appendChild(ageStudent);
        student.appendChild(courceStudent);
        student.appendChild(cost);
        root.appendChild(student);
        writeDocument(document);

    }
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("database.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}

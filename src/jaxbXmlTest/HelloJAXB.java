package jaxbXmlTest;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class HelloJAXB {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		JAXBContext jaxb = JAXBContext.newInstance(Boy.class);
		Marshaller marshaller = jaxb.createMarshaller();
		Unmarshaller unmarshaller = jaxb.createUnmarshaller();

		// Java object to XML
		marshaller.marshal(new Boy(), System.out);
		System.out.println();

		// XML to Java object
		String xml = "<boy><name>David</name></boy>";
		Boy boy2 = (Boy) unmarshaller.unmarshal(new StringReader(xml));
		System.out.println(boy2.name);
	}

}

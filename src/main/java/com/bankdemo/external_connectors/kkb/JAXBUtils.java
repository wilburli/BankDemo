package com.bankdemo.external_connectors.kkb;

/**
 * Created by Ilyas.Kuanyshbekov on 27.09.2016.
 */
public class JAXBUtils {

    public String marshal(@NotNull Object marshallerObject, boolean addXmlHeader) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(marshallerObject.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, !addXmlHeader);

        try (StringWriter writer = new StringWriter()) {

            marshaller.marshal(marshallerObject, writer);

            return writer.toString();
        }

    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshal(@NotNull String xml, Class<T> className) throws JAXBException {

        if (Objects.isNull(xml)) {
            throw new JAXBException("PARSE RESPONSE ERROR");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(className);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            return (T) unmarshaller.unmarshal(reader);
        }
    }

}
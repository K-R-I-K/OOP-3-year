package com.my.builders;

import com.my.models.Characteristics;
import com.my.models.Paper;
import com.my.models.PeriodicalType;
import com.my.utils.PaperEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import static com.my.utils.Constants.PATH_TO_SCHEMA;

public class PaperStAXBuilder extends XmlBuilder<Paper> {
    private XMLInputFactory inputFactory;

    @Override
    public Set<Paper> buildObjectsFromXml(String path) {

        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputFactory = XMLInputFactory.newInstance();
            inputStream = new FileInputStream(new File(path));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER) {
                        Paper paper = buildPaper(reader);
                        papers.add(paper);
                    }
                }
            }
                return papers;
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Paper buildPaper(XMLStreamReader reader) throws XMLStreamException {
        Paper paper = new Paper();
        paper.setId(Long.parseLong(reader.getAttributeValue(null, PaperEnum.ID.getValue())));
        paper.setTitle(reader.getAttributeValue(null, PaperEnum.TITLE.getValue()));
        paper.setMonthly(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_MONTHLY.getValue())));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PaperEnum.valueOf(name.toUpperCase())) {
                        case TYPE:
                            paper.setType(PeriodicalType.valueOf(getXMLText(reader)));
                            break;
                        case CHARACTERISTICS:
                            paper.setCharacteristics(getXMLCharacteristics(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PaperEnum.valueOf(name.toUpperCase()) == PaperEnum.PAPER) {
                        return paper;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag <paper>");
    }

    private Characteristics getXMLCharacteristics(XMLStreamReader reader) throws XMLStreamException {
        Characteristics characteristics = new Characteristics();
        characteristics.setColored(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_COLORED.getValue())));
        characteristics.setPageAmount(Integer.parseInt(reader.getAttributeValue(null, PaperEnum.PAGE_AMOUNT.getValue())));
        characteristics.setGlossy(Boolean.parseBoolean(reader.getAttributeValue(null, PaperEnum.IS_GLOSSY.getValue())));
        String attr = reader.getAttributeValue(null, PaperEnum.SUBSCRIPTION_INDEX.getValue());
        if (attr != null) {
            characteristics.setSubscriptionIndex(Integer.parseInt(attr));
        }
        return characteristics;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

package com.my.builders;

import com.my.models.Characteristics;
import com.my.models.Paper;
import com.my.models.PeriodicalType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

import static com.my.utils.Constants.*;
public class PaperDOMBuilder extends XmlBuilder<Paper> {

    private DocumentBuilder documentBuilder;

    @Override
    public Set<Paper> buildObjectsFromXml(String path) {

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            buildSetPapers(path);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return papers;
    }

    private void buildSetPapers(String path) {
        Document doc = null;

        try {
            doc = documentBuilder.parse(path);
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        Element root = doc.getDocumentElement();
        NodeList papersList = root.getElementsByTagName("paper");
        for (int i = 0; i < papersList.getLength(); i++) {
            Element paperElement = (Element) papersList.item(i);
            Paper paper = buildPaper(paperElement);
            papers.add(paper);
        }
    }

    private Paper buildPaper(Element paperElement) {
        Paper paper = new Paper();
        paper.setId(Long.parseLong(paperElement.getAttribute("id")));
        paper.setTitle(paperElement.getAttribute("title"));
        paper.setMonthly(Boolean.parseBoolean(paperElement.getAttribute("isMonthly")));
        paper.setType(PeriodicalType.valueOf(getElementTextContent(paperElement, "type")));
        Characteristics characteristics = getCharacteristics(paperElement, "characteristics");
        paper.setCharacteristics(characteristics);

        return paper;

    }

    private Characteristics getCharacteristics(Element paperElement, String elementName) {
        Characteristics characteristics = new Characteristics();
        Element charElement = (Element) paperElement.getElementsByTagName(elementName).item(0);
        characteristics.setColored(Boolean.parseBoolean(charElement.getAttribute("isColored")));
        characteristics.setPageAmount(Integer.parseInt(charElement.getAttribute("pageAmount")));
        characteristics.setGlossy(Boolean.parseBoolean(charElement.getAttribute("isGlossy")));
        if (!charElement.getAttribute("subscriptionIndex").isEmpty()) {
            characteristics.setSubscriptionIndex(Integer.parseInt(charElement.getAttribute("subscriptionIndex")));
        }
        return characteristics;
    }

    private String getElementTextContent(Element paperElement, String elementName) {
        NodeList nList = paperElement.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;

    }
}

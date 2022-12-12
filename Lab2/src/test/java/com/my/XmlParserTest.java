package com.my;

import com.my.builders.PaperDOMBuilder;
import com.my.builders.PaperSAXBuilder;
import com.my.builders.PaperStAXBuilder;
import com.my.models.Characteristics;
import com.my.models.Paper;
import com.my.models.PaperStore;
import com.my.models.PeriodicalType;
import com.my.utils.Constants;
import com.my.utils.ValidatorTest;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class XmlParserTest {
    private static PaperStore store;
    @BeforeClass
    public static void init() {
        Characteristics characteristics = new Characteristics(true, 500, true);
        characteristics.setSubscriptionIndex(456328);
        Paper paper = new Paper(1345454L, "New Magazine", PeriodicalType.MAGAZINE, true, characteristics);
        Characteristics characteristics2 = new Characteristics(true, 40, false);
        characteristics2.setSubscriptionIndex(324328);
        Paper paper2 = new Paper(2344543L, "New Paper", PeriodicalType.NEWSPAPER, true, characteristics2);
        Characteristics characteristics3 = new Characteristics(true, 500, true);
        Paper paper3 = new Paper(3345456L, "New Booklet", PeriodicalType.BOOKLET, true, characteristics3);
        store = new PaperStore();
        store.getPapers().add(paper);
        store.getPapers().add(paper2);
        store.getPapers().add(paper3);

    }

    @Test
    public void parseXml_paperDomBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperDOMBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test
    public void parseXml_paperDomBuilderInvalidXml_setIsNull() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperDOMBuilder());
        assertNull(resultSet);
    }
    @Test
    public void parseXml_paperSAXBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperSAXBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test
    public void parseXml_paperSAXBuilderInvalidXmlTag_setIsNull() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        Set<Paper> result = parser.parseXml(new PaperSAXBuilder());
        assertNull(result);
    }
    @Test
    public void parseXml_paperStAXBuilderValidXml_setsEqual() {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet = parser.parseXml(new PaperStAXBuilder());
        assertEquals(store.getPapers(), resultSet);
    }
    @Test
    public void parseXml_paperStAXBuilderInvalidXmlTag_setIsNull() {
        XmlParser<Paper> parser = new XmlParser<>(ValidatorTest.INVALID_XML);
        Set<Paper> result = parser.parseXml(new PaperStAXBuilder());
        assertNull(result);
    }
}
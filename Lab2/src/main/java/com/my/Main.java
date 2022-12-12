package com.my;

import com.my.builders.PaperDOMBuilder;
import com.my.builders.PaperSAXBuilder;
import com.my.builders.PaperStAXBuilder;
import com.my.models.Paper;
import com.my.utils.Constants;

import java.util.Set;


public class Main {

    public static void main(String[] args) {
        XmlParser<Paper> parser = new XmlParser<>(Constants.PATH_TO_XML);
        Set<Paper> resultSet1 = parser.parseXml(new PaperDOMBuilder());
        System.out.println(resultSet1);
        Set<Paper> resultSet2 = parser.parseXml(new PaperSAXBuilder());
        System.out.println(resultSet2);
        Set<Paper> resultSet3 = parser.parseXml(new PaperStAXBuilder());
        System.out.println(resultSet3);
    }

}

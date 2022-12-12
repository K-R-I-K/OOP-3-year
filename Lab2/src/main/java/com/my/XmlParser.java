package com.my;

import com.my.builders.XmlBuilder;

import java.util.Set;

public class XmlParser<T> {
    private String path;

    public XmlParser(String pathToXml) {
        this.path = pathToXml;
    }

    public Set<T> parseXml(XmlBuilder<T> builder) {
        return builder.buildObjectsFromXml(path);
    }
}

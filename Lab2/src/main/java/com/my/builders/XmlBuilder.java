package com.my.builders;

import java.util.HashSet;
import java.util.Set;

public abstract class XmlBuilder<T> {
    Set<T> papers;

    public XmlBuilder() {
        this.papers = new HashSet<>();
    }

    public abstract Set<T> buildObjectsFromXml(String path);
}

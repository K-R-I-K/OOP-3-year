package com.my.models;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement(name = "paper_store")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaperStore {
    @XmlElement(name = "paper", required = true)
    private Set<Paper> papers = new HashSet<>();

    public Set<Paper> getPapers() {
        return papers;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }
}

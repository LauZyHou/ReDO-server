package com.ecnu.redo.input.ea.xmi1;


import org.xml.sax.Attributes;

public class UMLGeneralization extends UMLConnector {
    private UMLVisibility visibility;
    private String ordered;
    public UMLGeneralization(String fromId, String toId) {
        super();
        this.fromId=fromId;
        this.toId=toId;
    }
    public UMLGeneralization(Attributes attributes) {
        super();
        this.fromId=attributes.getValue("supertype");
        this.toId=attributes.getValue("subtype");
        try {
            visibility=UMLVisibility.parse(attributes.getValue("visibility"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

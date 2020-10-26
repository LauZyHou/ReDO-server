package com.ecnu.redo.input.ea.xmi1;

import org.xml.sax.Attributes;

import java.util.HashMap;
import java.util.Map;


public class UMLAttribute {
private String name;
private String changeable;
private UMLVisibility visibility;
private String ownerScope;
private String targetScope;
private String initialValue;
private String type;
private Map<String,String> taggedValue;


    public UMLAttribute(Attributes attributes) {
        name=attributes.getValue("name");
        changeable=attributes.getValue("changeable");
        try {
            visibility=UMLVisibility.parse(attributes.getValue("visibility"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ownerScope=attributes.getValue("ownerScope");
        targetScope=attributes.getValue("targetScope");
        taggedValue=new HashMap<>();
    }
    public void parseXMI(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("UML:Classifier"))
            type=attributes.getValue("xmi.idref");
        else if (qName.equals("UML:TaggedValue"))
            parseParamProperties(attributes.getValue("tag"), attributes.getValue("value"));
        else if (qName.equals("UML:Expression"))
            initialValue=attributes.getValue("value");
    }
    public void parseParamProperties(String tag, String value) {

        taggedValue.put(tag,value);
    }

    @Override
    public String toString() {
        return "UMLAttribute{" +
                "name='" + name + '\'' +
                ", changeable='" + changeable + '\'' +
                ", visibility=" + visibility +
                ", ownerScope='" + ownerScope + '\'' +
                ", targetScope='" + targetScope + '\'' +
                ", initialValue='" + initialValue + '\'' +
                ", type='" + type + '\'' +
                ", taggedValue=" + taggedValue +
                '}';
    }
}

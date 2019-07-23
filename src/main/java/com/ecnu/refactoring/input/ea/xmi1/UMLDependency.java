package com.ecnu.refactoring.input.ea.xmi1;

import org.xml.sax.Attributes;
// Implementation relation
public class UMLDependency extends UMLConnector {
    private UMLVisibility visibility;
    public UMLDependency(Attributes attributes) {
        toId=attributes.getValue("client");
        fromId=attributes.getValue("supplier");
        try{
            visibility=UMLVisibility.parse(attributes.getValue("visibility"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

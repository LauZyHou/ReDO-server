package com.ecnu.refactoring.input.ea.xmi1;

import org.xml.sax.Attributes;

public class UMLPackage {
    private String id;
    private String name;
    private boolean root;
    private boolean leaf;
    private boolean abstractt;
    private UMLVisibility visibility;

    public UMLPackage(Attributes attributes) {
        id=attributes.getValue("xmi.id");
        name=attributes.getValue("name");
        root=Boolean.parseBoolean( attributes.getValue("isRoot"));
        leaf=Boolean.parseBoolean( attributes.getValue("isLeaf"));
        abstractt=Boolean.parseBoolean( attributes.getValue("isAbstract"));
        try{
            visibility=UMLVisibility.parse(attributes.getValue("visibility"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

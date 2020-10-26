package com.ecnu.redo.input.ea.xmi1;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;


public class UMLClass {
    private String id;
    private UMLPackage packageIn;
    private String name;
    private UMLVisibility visibility;
    private List<UMLOperation> operations;
    private ArrayList<UMLAttribute> attributes;



    public UMLClass(Attributes attributes, UMLPackage packagee){
        id=attributes.getValue("xmi.id");
        packageIn=packagee;
        name=attributes.getValue("name");
        try {
            visibility = UMLVisibility.parse(attributes.getValue("visibility"));
        }catch(Exception e){
            e.printStackTrace();
        }
        this.operations = new ArrayList<>();
        this.attributes=new ArrayList<>();
    }
    public UMLClass(Attributes attributes){
        this(attributes,null);
    }
    public String getId() {
        return id;
    }

    public UMLPackage getPackageIn() {
        return packageIn;
    }

    public String getName() {
        return name;
    }

    public UMLVisibility getVisibility() {
        return visibility;
    }

    public List<UMLOperation> getOperations() {
        return operations;
    }


    public void addOperation(UMLOperation parsingOperation) {
        operations.add(parsingOperation);
    }

    public void addAttribute(UMLAttribute parsingAttribute) {
        attributes.add(parsingAttribute);
    }

    public ArrayList<UMLAttribute> getAttributes() {
        return attributes;
    }
}

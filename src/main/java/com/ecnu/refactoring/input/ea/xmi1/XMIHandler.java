package com.ecnu.refactoring.input.ea.xmi1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMIHandler extends DefaultHandler {

    // eg xmlns:a=www.x.com <a:b c:d="e"> uri=www.x.com localname=b qName=a:b attributes.getValue("c:d")="e" uri=
    private boolean parse; // start parse or not.
    private boolean parseClass;
    private boolean parseOperation;
    private boolean parseAttribute;
    private boolean parseConnector=false;

    private UMLClass parsingClass;
    private UMLOperation parsingOperation;
    private UMLAttribute parsingAttribute;
    private UMLConnector parsingConnector;

    private UMLPackage packagee;
    private List<UMLClass> classes;
    private List<UMLConnector> connectors;
    private Map<String,String> eaStubs;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        classes=new ArrayList<>();
        connectors=new ArrayList<>();
        eaStubs=new HashMap<>();
        // all boolean set false here.
    }

    /**
     * <UML:Class name="CircleGizmo" xmi.id="EAID_01FC39CB_2106_46e7_8F43_1A32D44DAEC3" visibility="public" namespace="EAPK_60F020E8_75B7_4b5d_B378_7F34EFADCD89" isRoot="false" isLeaf="false" isAbstract="false" isActive="false">
     * 	  <UML:ModelElement.taggedValue>...</UML:ModelElement.taggedValue>
     * 	  <UML:Classifier.feature>
     * 	  	<UML:Attribute name="Attribute 1" changeable="none" visibility="private" ownerScope="instance" targetScope="instance">
     *        </UML:Attribute>
     *        <UML:Operation name="CircleGizmo" visibility="public" ownerScope="instance" isQuery="false" concurrency="sequential">
     *        </UML:Operation>
     * 	  </UML:Classifier.feature>
     * </UML:Class>
     * interface here deal as class
     * @param uri
     * @param localName
     * @param qName
     * @param attributes
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("UML:Package")){
            packagee=new UMLPackage(attributes);
            parse=true;
            return;
        }
        if(parse&&(qName.equals("UML:Class")||qName.equals("UML:Interface"))){
            parseClass=true;
            parsingClass=new UMLClass(attributes);
            return;
        }

        //Parsing Class Operation
        if(parse&&parseClass&&qName.equals("UML:Operation")){
            parsingOperation=new UMLOperation(attributes);
            parseOperation=true;
            return;
        }
        if(parse&&parseClass&&parseOperation){
            parsingOperation.parseXMI(uri,localName,qName,attributes);
            return;
        }

        //Parsing Class Attributes
        if(parse&&parseClass&&qName.equals("UML:Attribute")){
            parsingAttribute=new UMLAttribute(attributes);
            parseAttribute=true;
            return;
        }
        if(parse&&parseClass&&parseAttribute){
            parsingAttribute.parseXMI(uri,localName,qName,attributes);
            return;
        }

        // generalization
        if(parse&&qName.equals("UML:Generalization")){
            parsingConnector=new UMLGeneralization(attributes);
            parseConnector=true;
            return;
        }
        if(parse&&parseConnector&&qName.equals("UML:TaggedValue")) {
            parsingConnector.parseTag(attributes.getValue("tag"), attributes.getValue("value"));
            return;
        }

        // association (dependency)
        if(parse&&qName.equals("UML:Association")){
            parsingConnector=new UMLAssociation(attributes);
            parseConnector=true;
            return;
        }
        if(parse&&parseConnector&&qName.equals("UML:AssociationEnd")){
            if(parsingConnector instanceof UMLAssociation)
            ((UMLAssociation) parsingConnector).parseConnectionEnd(attributes);
            return;
        }

        // implementation
        if(parse&&qName.equals("UML:Dependency")){
            parsingConnector=new UMLDependency(attributes);
            connectors.add(parsingConnector);
            return;
        }

        if(qName.equals("EAStub")){
            eaStubs.put(attributes.getValue("xmi.id"),attributes.getValue("name"));
return;
        }
        System.out.println("start"+uri+" "+localName+" q"+qName+" "+attributes.getValue("xmi:id"));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(parse&&parseClass&&parseOperation){
            if(qName.equals("UML:Operation")) {
                parseOperation = false;
                parsingClass.addOperation(parsingOperation);
            }
            else{
                parsingOperation.parseXMIEnd(uri,localName,qName);
            }
            return;
        }
        if(parse&&parseClass&&parseAttribute&&qName.equals("UML:Attribute")){
            parsingClass.addAttribute(parsingAttribute);
            parseAttribute=false;
            return;
        }
        if(parse&&parseClass&&(qName.equals("UML:Class")||qName.equals("UML:Interface"))){
            parseClass=false;
            classes.add(parsingClass);
            return;
        }
        if(parse&&qName.equals("UML:Package")){
            parse=false;
            return;
        }
        if(parse&&parseConnector&&qName.equals("UML:Generalization")){
           connectors.add( parsingConnector);
            parseConnector=false;
            return;
        }
        if(parse&&parseConnector&&qName.equals("UML:Association")) {
            if (((UMLAssociation) parsingConnector).getFrom() != null
                    && ((UMLAssociation) parsingConnector).getTo() != null)
                connectors.add(parsingConnector);
            else{
                System.out.println("Error in association, this connector will be skipped.");
            }
            parseConnector = false;
            return;
        }

//        System.out.println("end:"+uri+" "+localName+" "+qName+" ");
    }

    public List<UMLClass> getClasses() {
        return classes;
    }

    public List<UMLConnector> getConnectors() {
        return connectors;
    }

    public Map<String, String> getEaStubs() {
        return eaStubs;
    }
}

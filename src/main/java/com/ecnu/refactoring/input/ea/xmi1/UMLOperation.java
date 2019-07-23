package com.ecnu.refactoring.input.ea.xmi1;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;

public class UMLOperation {
    private String id;
    private String name;
    private UMLVisibility visibility;
    private String concurrency;
    private List<UMLParameter> parameters;
private UMLParameter returnValue;
    public UMLOperation(Attributes attributes){
        id=attributes.getValue("xmi:id");
        name=attributes.getValue("name");
        try {
            visibility = UMLVisibility.parse(attributes.getValue("visibility"));
        }catch(Exception e){
            e.printStackTrace();
        }
        concurrency=attributes.getValue("concurrency");
        parameters=new ArrayList<>();

    }

    private boolean parseParam=false; //initial when new should be false
    private boolean parseReturnParam=false;
    private boolean parseNormalParam=false;
    private UMLParameter paramParsing;
    public void parseXMI(String uri, String localName, String qName, Attributes attributes) {
        if(qName.equals("UML:BehavioralFeature.parameter")){
            parseParam=true;
        }
        else if(parseParam&&qName.equals("UML:Parameter")){
            if(attributes.getValue("kind").equals("return")) {
                returnValue = new UMLParameter(attributes);
                parseReturnParam = true;
            }
            else{
                paramParsing=new UMLParameter(attributes);
                parseNormalParam=true;
            }
        }

        /*
        <UML:Parameter name="g" kind="in" visibility="public">
            <UML:Parameter.type>
                <UML:Classifier xmi.idref="eaxmiid4"/>
            </UML:Parameter.type>
            <UML:ModelElement.taggedValue>
                <UML:TaggedValue tag="pos" value="0"/>
                <UML:TaggedValue tag="type" value="Graphics"/>
                <UML:TaggedValue tag="const" value="0"/>
                <UML:TaggedValue tag="ea_guid" value="{0D0090CA-C22F-4393-8CDC-F4C457205E6D}"/>
            </UML:ModelElement.taggedValue>
            <UML:Parameter.defaultValue>
                <UML:Expression/>
            </UML:Parameter.defaultValue>
         </UML:Parameter>
         */
        if(parseParam&&parseReturnParam&&qName.equals("UML:Classifier")){
            extractParamInfo(qName, attributes, returnValue);
        }
        if(parseParam&&parseNormalParam) {
            extractParamInfo(qName, attributes, paramParsing);
        }
    }

    private void extractParamInfo(String qName, Attributes attributes, UMLParameter returnValue) {
        if (qName.equals("UML:Classifier"))
            returnValue.parseParamType(attributes.getValue("xmi.idref"));
        else if (qName.equals("UML:TaggedValue"))
            returnValue.parseParamProperties(attributes.getValue("tag"), attributes.getValue("value"));
        else if (qName.equals("UML:Expression"))
            returnValue.parseParamDefaultValue(attributes.getValue("value"));
    }

    public void parseXMIEnd(String uri, String localName, String qName) {
        if(parseParam&&parseReturnParam&&qName.equals("UML:Parameter")){
            parseReturnParam=false;
        }
        else if(parseParam&&parseNormalParam&&qName.equals("UML:Parameter")){
           parameters.add( paramParsing);
           parseNormalParam=false;
        }
        if(parseParam&&qName.equals("UML:BehavioralFeature.parameter")){
            parseParam=false;
        }
    }
}

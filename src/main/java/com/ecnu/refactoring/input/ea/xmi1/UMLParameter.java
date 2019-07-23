package com.ecnu.refactoring.input.ea.xmi1;

import org.xml.sax.Attributes;

import java.util.HashMap;
import java.util.Map;

public class UMLParameter {
//    private String id;
    private String name;
    private String kind;
    private UMLVisibility visibility;
    private String type;
//    private String direction;
//    private boolean stream;
//    private boolean  exception;
//    private boolean  ordered;
//    private boolean  unique;
    private String defaultValue;
    private Map<String,String> taggedValue;
    public UMLParameter(Attributes attributes){
//        id=attributes.getValue("xmi:id");
        name=attributes.getValue("name");
        kind=attributes.getValue("kind");
        try{
            visibility=UMLVisibility.parse(attributes.getValue("visibility"));
        }catch (Exception e){
            e.printStackTrace();
        }
//        type=attributes.getValue("type");
//        direction=attributes.getValue("direction");
//        stream=Boolean.getBoolean( attributes.getValue("stream"));
//        exception=Boolean.getBoolean( attributes.getValue("exception"));
//        ordered=Boolean.getBoolean( attributes.getValue("ordered"));
//        unique=Boolean.getBoolean( attributes.getValue("unique"));
        taggedValue=new HashMap<>();
    }

    public void parseParamType(String value) {
        type=value;
    }

    public void parseParamDefaultValue(String defaultValue) {
        this.defaultValue=defaultValue;
    }

    /**
     * <UML:ModelElement.taggedValue>
     *    <UML:TaggedValue tag="pos" value="0"/>
     *    <UML:TaggedValue tag="type" value="Graphics"/>
     *    <UML:TaggedValue tag="const" value="0"/>
     *    <UML:TaggedValue tag="ea_guid" value="{0D0090CA-C22F-4393-8CDC-F4C457205E6D}"/>
     * </UML:ModelElement.taggedValue>
     * @param tag
     * @param value
     */
    public void parseParamProperties(String tag, String value) {
        taggedValue.put(tag,value);
    }
}

package com.ecnu.refactoring.input.ea.xmi1;

import java.util.HashMap;
import java.util.Map;

public  class UMLConnector {
    String fromId; // side which has tag, such as triangle in generalization.
    String toId;
    Map<String,String> taggedValue;

    public UMLConnector() {
        taggedValue=new HashMap<>();
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

public  void parseTag(String tag,String value){
        taggedValue.put(tag,value);
};


}

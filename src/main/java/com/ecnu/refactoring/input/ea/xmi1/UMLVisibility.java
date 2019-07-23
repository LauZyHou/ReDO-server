package com.ecnu.refactoring.input.ea.xmi1;

public enum UMLVisibility {
    PUBLICC,PRIVATE,PROTECTED,DEFAULT;

    public static UMLVisibility parse(String visibility) throws Exception{
        if(visibility.equals("public")) return PUBLICC;
        else if (visibility.equals("private")) return PRIVATE;
        else if(visibility.equals("protected")) return PROTECTED;
        else if (visibility.equals(null)) return DEFAULT;
        else throw new Exception("Can not parse visibility with "+visibility);
    }
}

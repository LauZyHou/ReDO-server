package com.ecnu.redo.input.ea.xmi1;

import lombok.Getter;
import org.xml.sax.Attributes;

// 组合Composition、聚合Aggregation、关联Dependency
public class UMLAssociation extends UMLConnector {
    private UMLVisibility visibility;
    private boolean root;
    private boolean leaf;
    private boolean abstractt;
    private String direction;
    private AssociationEnd from;
    private AssociationEnd to;

    public UMLAssociation(Attributes attributes) {
        super();
        try {
            visibility = UMLVisibility.parse(attributes.getValue("visibility"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.root = Boolean.parseBoolean(attributes.getValue("isRoot"));
        this.leaf = Boolean.parseBoolean(attributes.getValue("isLeaf"));
        this.abstractt = Boolean.parseBoolean(attributes.getValue("isAbstract"));
    }


    public void parseConnectionEnd(Attributes attributes) {
        if (attributes.getValue("aggregation").equals("none") && to == null) {
            toId = attributes.getValue("type");
            to = new AssociationEnd(attributes);
        } else {
            fromId = attributes.getValue("type");
            from = new AssociationEnd(attributes);
        }
    }

    public boolean isDependencyRelation() {
        return to.aggregation == UMLAggregation.NONE && from.aggregation == UMLAggregation.NONE;
    }

    public boolean isAggregationRelation() {
        return to.aggregation == UMLAggregation.AGGREGATION || from.aggregation == UMLAggregation.AGGREGATION;
    }

    public boolean isCompositionRelation() {
        return to.aggregation == UMLAggregation.SHARED || from.aggregation == UMLAggregation.SHARED;
    }


    public AssociationEnd getFrom() {
        return from;
    }

    public AssociationEnd getTo() {
        return to;
    }

    public int getBehalfMultiplicity() {
        String s = from.getMultiplicity();
        int fromMult = resolveMultiplicityString(s);
        String s2 = to.getMultiplicity();
        int toMult = resolveMultiplicityString(s2);
        int fromMaxValue = Math.max(fromMult >> 16, (short) fromMult);
        int toMaxValue = Math.max(toMult >> 16, (short) toMult);
        return Math.max(fromMaxValue, toMaxValue);
    }


    private int resolveMultiplicityString(String s) {
        if (s.contains("..")) {
            String firstStr = s.substring(0, s.indexOf(".."));
            int minMul;
            if (firstStr.equals("*")) {
                minMul = 0xffff;
            } else if (firstStr.equals("")) {
                minMul = 1;
            } else {
                minMul = Integer.parseInt(firstStr);
            }
            String maxStr = s.substring(s.indexOf("..") + 2);
            int maxMul;
            if (firstStr.equals("*")) {
                maxMul = 0xffff;
            } else if (firstStr.equals("")) {
                maxMul = 1;
            } else {
                maxMul = Integer.parseInt(firstStr);
            }
            return minMul << 16 + maxMul;
        } else {
            if (s.equals("*")) return 0xffff;
            return Integer.parseInt(s);
        }
    }

    @Getter
    class AssociationEnd {
        private UMLVisibility visibility;
        private String multiplicity; //optimize

        public String getMultiplicity() {
            return multiplicity;
        }

        private String name; //job
        private UMLAggregation aggregation;
        private boolean ordered;
        private String targetScope;
        private String changeable;
        private boolean navigable;
        private String type;

        public AssociationEnd(Attributes attributes) {
            try {
                visibility = UMLVisibility.parse(attributes.getValue("visibility"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            multiplicity = attributes.getValue("multiplicity");
            name = attributes.getValue("name");
            aggregation = UMLAggregation.parseString(attributes.getValue("aggregation"));
            ordered = Boolean.parseBoolean(attributes.getValue("isOrdered"));
            targetScope = attributes.getValue("targetScope");
            changeable = attributes.getValue("changeable");
            navigable = Boolean.parseBoolean(attributes.getValue("isNavigable"));

        }
    }

    /**
     * If both ends is {@code NONE}, it's a dependency relation.
     * If one in two ends is {@code SHARED}, it's a aggregation relation.
     * If one in two ends is {@code AGGREGATION}, it's a composition relation.
     * Seems like a trick here since the name of the class seems what related to aggregation, however here we treat
     * dependency relation as a special situation of composition/aggregation relation.
     */
    enum UMLAggregation {
        NONE, SHARED, AGGREGATION;

        public static UMLAggregation parseString(String s) {
            if (s.equals("aggregation")) {
                return AGGREGATION;
            } else if (s.equals("shared")) {
                return SHARED;
            } else {
                return NONE;
            }
        }
    }
}

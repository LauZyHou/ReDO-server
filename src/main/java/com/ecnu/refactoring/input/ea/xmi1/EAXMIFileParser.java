package com.ecnu.refactoring.input.ea.xmi1;

import com.ecnu.refactoring.input.FileParser;
import com.ecnu.refactoring.input.RefactorMatrix;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EAXMIFileParser implements FileParser {

    List<UMLConnector> connectors;
    List<UMLClass> classes;

    // special for ea, try to find id here if class is not found.
    Map<String,String> eaStubs;


    @Override
    public RefactorMatrix parseFile(File file) throws Exception {
        parseXMLFile(file);
        double[][] matrix=generateMatrix();
        List<String> tag=generateTag();
        return new RefactorMatrix(matrix,tag.toArray(new String[tag.size()]));
    }

    private List<String> generateTag() {
        List<String > ret=new ArrayList<>();
        for(int i=0;i<classes.size();i++){
            ret.add( classes.get(i).getName());
        }
        return ret;
    }


    public void parseXMLFile(File file) throws Exception {
        SAXParserFactory factory= SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        SAXParser saxParser= factory.newSAXParser();
        XMIHandler xmiHandler=new XMIHandler();
        saxParser.parse(file,xmiHandler);
        classes= xmiHandler.getClasses();
        connectors=xmiHandler.getConnectors();
        eaStubs=xmiHandler.getEaStubs();
    }

    public double[][] generateMatrix(){
//        System.out.println(classes.size());
        double[][] res=new double[classes.size()][classes.size()];
        // Class complexity
        for(int i=0;i<classes.size();i++){
           UMLClass umlClass= classes.get(i);
            System.out.println(umlClass.getName()+umlClass.getOperations().toString());

            res[i][i]+=umlClass.getOperations().size()*1.0d;
            res[i][i]+=umlClass.getAttributes().size()*0.5d;
        }
        // Connector complexity
        for(UMLConnector c:connectors){
            System.out.println("toIndex = " +c.getToId()+"fromId="+ c.getFromId());
           int fromIndex= findClassIndex(c.getFromId());
           int toIndex=findClassIndex(c.getToId());

           if(c instanceof UMLGeneralization || c instanceof UMLDependency){ // Generalization or Implementation
               res[fromIndex][toIndex]+=4.0d;
               res[toIndex][fromIndex]+=4.0d;
           }
           else if(c instanceof UMLAssociation){  // Composition, Dependency or Aggregation
               UMLAssociation association=(UMLAssociation)c;
               if(association.isAggregationRelation()||association.isCompositionRelation()){ // Composition or Aggregation
                   if(association.getBehalfMultiplicity()==0xffff){
                       res[fromIndex][toIndex]+=3.0d;
                       res[toIndex][fromIndex]+=3.0d;
                   }
                   else{
                       res[fromIndex][toIndex]+=2.0d;
                       res[toIndex][fromIndex]+=2.0d;
                   }
               }
               else{ //Dependency
                   res[fromIndex][toIndex]+=1.0d;
                   res[toIndex][fromIndex]+=1.0d;
               }
           }
        }
        return res;
    }

    private int findClassIndex(String classId) {
        for(int i=0;i<classes.size();i++){
            System.out.println( classes.get(i).getId());
            if(classId.equals( classes.get(i).getId())){
                return i;
            };
        }
        if(eaStubs.get(classId)!=null){
            String className=eaStubs.get(classId);
            System.out.println("me"+className);
            for(int i=0;i<classes.size();i++){
                System.out.println( classes.get(i).getName());
                if(className.equals( classes.get(i).getName())){
                    return i;
                };
            }
        }
        return -1;
    }

    public List<UMLConnector> getConnectors() {
        return connectors;
    }

    public List<UMLClass> getClasses() {
        return classes;
    }
}

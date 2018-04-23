package com.nbh.patterns.Visitor;

import java.util.*;

public class PrintVisitor implements Visitor{
    
    public void visitCollection(Collection collection) {
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            if (o instanceof Visitable)
                ((Visitable)o).accept(this);
        }
    }
    
    public void visitVisitor(Object o){
     
        if (o instanceof Visitable){
            ((Visitable)o).accept(this);            
        }
    }
    
    public void visitString(String string) { 
        
        System.out.println("'"+string+"'");
    }
    
    public void visitFloat(Float floate) {
        System.out.println(floate.toString()+"f");
    }
    
    public static void main(String[] args){

        Visitable v = new VisitableString("Hello world");
        
        Visitor myVisitor = new PrintVisitor();
        //myVisitor.visitVisitor(v);
        
        
    }
}




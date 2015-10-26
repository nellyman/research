package com.nbh.tutorials.patterns.Visitor;


public class VisitableString implements Visitable
{
   private String value;
   public VisitableString(String string) {
      value = string;
   }
   public void accept(Visitor visitor) {
      visitor.visitString(this);
   }
}
 
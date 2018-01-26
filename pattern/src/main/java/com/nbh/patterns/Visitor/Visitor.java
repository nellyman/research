package com.nbh.patterns.Visitor;

import java.util.*;

public interface Visitor
{
   public void visitCollection(Collection collection);
   public void visitString(String string);
   public void visitFloat(Float floate);
}
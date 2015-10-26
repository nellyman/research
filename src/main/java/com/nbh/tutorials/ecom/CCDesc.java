package com.nbh.tutorials.ecom;

//
// A description of a single credit card
//

public class CCDesc implements java.io.Serializable { 
   String _name;
   String _desc;
   int[] _lengths;
   String[] _prefixes;

   public CCDesc(String name, String desc, int[] lengths, String[] prefixes) {
      _name = name;
      _desc = desc;
      _lengths = lengths;
      _prefixes = prefixes;
   }

   public String getName() {
      return _name;
   }

   public String getDescription() {
      return _desc;
   }

   public int[] getLengths() {
      return _lengths;
   }

   public String[] getPrefixes() {
      return _prefixes;
   }
}

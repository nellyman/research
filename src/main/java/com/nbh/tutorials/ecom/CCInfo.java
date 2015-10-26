package com.nbh.tutorials.ecom;
//
// A collection of CCDesc indexed by credit card name
//

import java.util.HashMap;

public class CCInfo extends HashMap implements java.io.Serializable {
   public CCInfo() {
      super();
   }

   public void put(CCDesc carddesc) {
      super.put(carddesc.getName(), carddesc);
   }

   protected CCDesc getDesc(String cardtype) {
      CCDesc carddesc = (CCDesc)get(cardtype);
      if (carddesc == null) {
         throw new IllegalArgumentException("No such card type: " +
                                            cardtype);
      }
      return carddesc;
   }

   public String getDescription(String cardtype) {
      return getDesc(cardtype).getDescription();
   }

   public int[] getLengths(String cardtype) {
      return getDesc(cardtype).getLengths();
   }

   public String[] getPrefixes(String cardtype) {
      return getDesc(cardtype).getPrefixes();
   }

}

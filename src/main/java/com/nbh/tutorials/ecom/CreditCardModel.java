package com.nbh.tutorials.ecom;

import java.util.Date;
//import java.util.regex.Pattern;
//import java.util.regex.PatternSyntaxException;

    public class CreditCardModel{
   // Credit card information definition and
   // static initialization
/*   private static  CCInfo _ccinfo = null;
   static Pattern _mmyy;
   static {
      _ccinfo = new CCInfo();

      _ccinfo.put(new CCDesc("amex",
                             "American Express",
                             new int[] { 15 },
                             new String[] {
                                "34", "37"
                             }));

      _ccinfo.put(new CCDesc("dccb",
                             "Diner's Club/Carte Blanche",
                             new int[] { 14 },
                             new String[] {
                                "300", "301", "302",
                                "303", "304", "305",
                                "36", "38"
                             }));

      _ccinfo.put(new CCDesc("disc",
                             "Discover",
                             new int[] { 16 },
                             new String[] {
                                "6011"
                             }));

      _ccinfo.put(new CCDesc("mc",
                             "MasterCard",
                             new int[] { 16 },
                             new String[] {
                                "51", "52", "53",
                                "54", "55"
                             }));

      _ccinfo.put(new CCDesc("visa",
                             "Visa",
                             new int[] { 13, 16 },
                             new String[] {
                                "4"
                             }));
      try {
//         _mmyy = Pattern.compile("\\s*(\\d+)\\s*/ // \\s*(\\d+)");
      /*} catch (PatternSyntaxException pse) {
         System.err.println("Exception: " + pse);
      }
   }

   public CreditCardModel(String name,
                           String type,
                           String number,
                           String expdate) {
      _name = name;
      _type = type;
      _number = number;
      _expirationDate = null;

      setExpirationDateStr(expdate);

      // Default value
      if (type == null || type.equals("")) {
         type = "visa";
      }
   }

   // Return all credit card types
   public static CCInfo getInfo() {
      return _ccinfo;
   }

   // Return credit card type information
   public CCDesc getDesc() {
      return (CCDesc)_ccinfo.get(_type);
   }

   // Returns whether or not the card number is valid
   public boolean isValid() {
      return
         isNameValid() &&
         isTypeValid() &&
         isLengthValid() &&
         isPrefixValid() &&
         isDateValid() &&
         !isExpired() &&
         isChecksumValid();
   }

   // Type is valid only if there's a CCDesc for it in CCInfo.
   protected boolean isTypeValid() {
      return _ccinfo.containsKey(_type);
   }

   // Calculate validity of "luhn checksum"
   protected boolean isChecksumValid() {
      int checksum = 0;
      byte[] bytes = _number.getBytes();
      for (int i = 0; i < bytes.length; i++) {

         // Get digit. If it's not a digit, fail
         int p = (int)(bytes[i]) - '0';
         if (p < 0 || p > 9) {
            return false;
         }

         // luhn algorithm.
         if ((i & 1) == 0) {
            p *= 2;
            p = (p % 10) + (p / 10);
         }
         checksum += p;
      }
      return (checksum % 10) == 0;
   }

   // Returns why card number is not valid
   public String getValidityMessage() {
      if (!isTypeValid()) {
         return "No such credit card type: '" + _type + "'";
      }
      else if (!isLengthValid()) {
         return "Length of number wrong for this type of card.";
      }
      else if (!isPrefixValid()) {
         return "Card prefix is wrong. " +
            "Are you sure you chose the correct type of card?";
      }
      else if (!isDateValid()) {
         return "Date entered incorrectly.";
      } else if (isExpired()) {
         return "Card is expired.";
      }
      else if (!isChecksumValid()) {
         CCDesc ccdesc = getDesc();
         String desc = (ccdesc != null) ? ccdesc.getDescription() : null;
         String result = "This is not a valid number";

         if (desc != null) { 
            result = result + " for a " + desc;
         }
         return result;
      }
      return "Credit card number is valid.";
   }

   // Is card currently expired?
   public boolean isExpired() {
      if (!isDateValid())
         return true;
      Date now = new Date();
      if (_expirationDate.before(now))
         return true;
      return false;
   }

   // Look for length of this item in list of valid lengths
   private boolean isLengthValid() {
      int[] lengths = getDesc().getLengths();
      int length = _number.length();
      for (int i = 0; i < lengths.length; i++) {
         if (length == lengths[i]) {
            return true;
         }
      }
      return false;
   }

   // Check list of valid prefixes for this card type
   private boolean isPrefixValid() {
      String[] prefixes = getDesc().getPrefixes();
      for (int i = 0; i < prefixes.length; i++) {
         if (_number.startsWith(prefixes[i])) {
            return true;
         }
      }
      return false;
   }

   // Check list of valid prefixes for this card type
   private boolean isDateValid() {
      return _expirationDate != null;
   }

   private boolean isNameValid() {
      return (_name != null) && !_name.equals("");
   }

   public String getName() {
      return _name;
   }

   public void setName(String name) {
      if (name != null) {
         _name = name.trim();
      }
   }

   public String getType() {
      return _type;
   }

   public void setType(String type) {
      _type = type;
   }

   public String getNumber() {
      return _number;
   }

   public void setNumber(String number) {
      _number = number;
   }

   // Get actual date
   public Date getExpirationDate() {
      return _expirationDate;
   }

   public String getExpirationDateStr() {
      return _expdate;
   }

   // Given mm/yy, figure out the expiration date and
   // set both it and the date string properties
   public void setExpirationDateStr(String expdate) {

      // Disallow empty or null
      if (expdate == null)
         return;

      // Match date pattern
      Matcher match = _mmyy.matcher(expdate);
      if (!match.matches())
         return;

      int month = Integer.parseInt(match.group(1));
      int year = Integer.parseInt(match.group(2));

      // Ill-formed
      if (month < 1 || month > 12) {
         return;
      }

      // Fix 2-digit year
      if (year < 49) {
         year += 2000;
      } else if (year <= 99) {
         year += 1900;
      }

      // Find the last day of this month
      GregorianCalendar gc = new GregorianCalendar(year, month-1, 1);
      gc.roll(GregorianCalendar.MONTH, true);   // First of next month
      gc.roll(GregorianCalendar.DAY_OF_YEAR, false);    // Last of prev month

      // Get expiration date into date object and into string.
      _expirationDate = gc.getTime();
      DateFormat fmt = new SimpleDateFormat("MM/yyyy");
      _expdate = fmt.format(_expirationDate);
   }
   */
}

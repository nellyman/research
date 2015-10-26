package com.nbh.test;

    import com.nbh.other.DTO;

import java.util.Iterator;
/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Apr 13, 2004
 * Time: 2:10:22 PM
 * To change this template use Options | File Templates.
 */
public class InnerClassTest {

    public static void main(String[] args){
        DTO dto = new DTO();
        populateDTO(dto);
        printOptions(dto);
        printAccounts(dto);

    }


    public static void printOptions(DTO dto){

        Iterator it = dto.getOptionInformation();
        System.out.println("options");
        while (it.hasNext()){
            Object o = it.next();
            DTO.OptionInformation opInfo = (DTO.OptionInformation)o;
            System.out.println(opInfo.getOptionCode()+" "+opInfo.getPrice());
        }
    }

    public static void printAccounts(DTO dto){

        Iterator it = dto.getAccountInformation();
        System.out.println("accounts");
        while (it.hasNext()){
            DTO.AccountInformation acctInfo = (DTO.AccountInformation)it.next();
            System.out.println(acctInfo.getQualifiedAcctId()+" "+acctInfo.getAccBal()+" "+acctInfo.getBalType());
        }
    }


    public static void populateDTO(DTO dto){
        dto.addAccountInformation("123","00.00","cur");
        dto.addAccountInformation("123","00.00","cur");

        dto.addAccountInformation("124","100.00","sav");
        dto.addAccountInformation("124","100.00","cur");

        dto.addAccountInformation("125","1000.00","cur");

        dto.addOptionInformation("909","f","p","FFR","100");
        dto.addOptionInformation("909","f","p","FFR","100");
        dto.addOptionInformation("1909","f","p","FFR","100");
        dto.addOptionInformation("2909","f","p","FFR","100");
        dto.addOptionInformation("3909","f","p","FFR","100");

    }

}

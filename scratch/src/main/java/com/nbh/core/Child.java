/*
 * Child.java
 *
 * Created on 22 April 2003, 17:10
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class Child extends Parent {
    
    public String name2="cHello";
    public String name3="child hello";
    public String name4="child hiya";
    
    /** Creates a new instance of Child */
    public Child() {
        System.out.println("Child no-args constructor ");        
        // implied super call        
      //  super();
        
    }
    
    public Child(String name){    
        System.out.println("Child constructor with "+name);
    }
    
    public Child(String name, String x){
     
        super(name);
        System.out.println("Child constructor with "+name);
    }
    
    public String getName1(){
        
        return (name);
    }
    
    public String getName3(){
        return (name3);
    }
    
   
    public String notInParent(){
        
        return ("Method only in child");
    }
    
    public static void main(String[] args){
        
        // Parent pc is the key class to understanding java objects.
        // Parent pc is derived from a child method.
        // Parent pc can only use the methods of the Parent, even though child has more available methods.
        // Parent pc will use any overridden methods of the child class, (eg name3 - rem it out in the child).
        // proven by pc.getName3() - returning the child hello.
        
        Parent p = new Parent();
        Child c = new Child();
        Parent pc = c;
        System.out.println(" parent method (member + local variable) parent call : "+p.getName1());
        System.out.println(" parent method parent call : "+p.getName3());
        System.out.println(" over-ridden method, variable only in parent child call : "+c.getName1());
        System.out.println(" method in parent class only, child call : "+c.getName2());
        System.out.println(" parent from child calling over-ridden method (data only in parent) : "+pc.getName1());
        System.out.println(" parent from child calling method in parent (data in child +parent) : "+pc.getName2());
        System.out.println(" parent from child calling over-ridden method (data in child +parent): "+pc.getName3());    
        System.out.println(" child method notInParent : "+c.notInParent());
        System.out.println(" parent from child calling method notInParent results in compile error");
        // compile error ....  methods not part of parent class.
        //        System.out.println(" parent from child method in child only : "+pc.notInParent());
     
        
        // testing for super() calls....
        System.out.println("\n");
        Child d = new Child("henry");
        System.out.println("Incorrect constructor operation, Child extends Parent, call a Parent method...");
        System.out.println(d.parentMethodOnly());
        System.out.println("\n");
        Child e = new Child("Peter","x");
        System.out.println("Correct constructor operation, (super called), call a parent method");
        System.out.println(e.parentMethodOnly());
        System.out.println("Therefore if there is not a super(args) call made in a child constructor then the parent default constructor is used !");
        System.out.println("If theres no default parent constructor then inheritence requires each child constructor to make a call to super(args)");
    }
    
}

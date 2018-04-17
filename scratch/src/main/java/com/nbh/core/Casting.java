package com.nbh.core;

public class Casting {

    public static void main(String[] args) {
        Mammal tiger = new Tiger();
        Mammal bear = new Bear();
        System.out.println(tiger.getName());
        System.out.println(bear.getName());

        Insect fly = new Fly();
        System.out.println(fly.getName());
        System.out.println(fly.age);
    }

}


interface Mammal{
    String getName();
}

class Tiger implements Mammal{
    @Override
    public String getName() {
        return "Tiger";
    }
}

class Bear implements Mammal{
    @Override
    public String getName() {
        return "Bear";
    }
}


abstract class Insect{
    int age=10;
    abstract String getName();
}

class Fly extends Insect{
    int age=100;
    @Override
    String getName() {
        return "Fly";
    }
}



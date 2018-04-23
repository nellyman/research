package com.nbh.core;

public class ClassCast {

    public static void main(String[] args) {
        String str1 = new String("String1");
        String str2 = new String("String1");
        System.out.print(str1 == str2);
        System.out.print(str1.equals(str2));
        str1 = str2;
        System.out.println(str1 == str2);


        str1 = "String1";
        str2 = "String2";
        str1.concat("String3");
        System.out.println(str1);
        System.out.println(str2);

        Vehicle car = new Car();
        System.out.println(car.getNumberOfWheels()); // it's 10, even though a car was created. The assigned type is the actual type

        Car car2 = (Car) car;
        System.out.println("car2.size = " + car2.getNumberOfWheels()); // but casting to a car gives the car value.

        //Bike bike1 = (Bike)car; // casting to a bike fails at runtime..
        //System.out.println("bike1.size = " + bike1.size);

        //Car car3 = (Car) new Vehicle(); // this will pass compile time, but throw a class cast at runtime.
        //System.out.println("car3.size = " + car3.size);

        boolean matches = car instanceof Car;
        System.out.println("matches = " + matches);

        matches=car instanceof Bike;
        System.out.println("matches = " + matches);

        //X  x = new Y(); // interface can't make new
        //X x = new Z(); // interface can't make new
        //Z z = new Y(); // interface can't make new
        Z z = new X();
        z.doSomething();
    }
}

    class Bike extends Vehicle {
        @Override
        public int getNumberOfWheels() {
            return 2;
        }

        int size=1;
    }


    interface Z{

        void doSomething();
    }

    interface Y extends Z{};

    class X implements Y{
        @Override
        public void doSomething() {
            System.out.println("something");
        }
    }
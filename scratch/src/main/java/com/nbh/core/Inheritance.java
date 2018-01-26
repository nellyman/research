package com.nbh.core;

public class Inheritance {

    public static void main(String[] args) {
        Vehicle2 vehicle = new Car2();
        //vehicle.move(); // private access, will not work!!!
    }
}

    class Vehicle2 {
        private void move(){
            System.out.println("Vehicle is moving");
        }
    }

    class Car2 extends Vehicle2{
        public void move(){
            System.out.println("Car is moving");
        }
    }




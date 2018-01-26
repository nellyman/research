package com.nbh.core;

public class ParentMethodCast {

    public static void main(String[] args){
        Vehicle3 vehicle = new Vehicle3();
        //((Car3)vehicle).move(); // runtime exception

        Vehicle3 vehicle2 = new Car3();
        vehicle2.move(); // method called from the instaniated class
        System.out.println(vehicle2.speed); // although values are from the reference
    }
}

    class Vehicle3 {
        int speed=100;
        public void move(){
            System.out.println("Vehicle is moving at "+speed);
        }
    }

    class Car3 extends Vehicle3 {
    int speed=20;
        public void move() {
            System.out.println("Car is moving at"+speed);
        }
    }



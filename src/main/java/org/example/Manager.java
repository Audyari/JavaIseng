package org.example;

public class Manager {

    String name;

    Manager(String name){
        this.name = name;
    }

    void sayHello(String name){
        System.out.println("Hello " + name + ", My Name is " + this.name + "From Manager");
    }

}



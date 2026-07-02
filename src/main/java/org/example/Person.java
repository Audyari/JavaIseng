package org.example;

public class Person {

    String name;
    String address;
    final String country = "Indonesia";

    Person(String name, String paramAddress){
        this.name = name;
        this.address = paramAddress;
    }

    Person(){
        this("Unknown", "Unknown");
    }

    void sayHello(String paramName) {
        System.out.println(
                "Nama saya " + name +
                " dari " + country +
                ", nama kamu " + paramName
        );
    }

    void setName(String name) {
        this.name = name;
    }
}

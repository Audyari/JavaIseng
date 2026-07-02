
package org.example;

public class Main {

    public static void main(String[] args) {

        var data = new Person("Audyari","Pondok Gede");

        System.out.println(data.name);
        data.sayHello("Budi");

        var data2 = new Person();

        System.out.println(data2.name);

        Person p = new Person();
        p.setName("Audyari");
        System.out.println(p.name);

        VicePresident data3 = new VicePresident("Asep1");
        data3.name = "Audyari W";
        data3.sayHello("Asep");

        Manager data4 = new Manager("Asep2");
        data4.sayHello("Audt manager");
    }
}

package com.danielme.demo.jaxws.cxf.model;

/**
 * 
 * @author danielme.com
 *
 */
public class Player {

    private String name;

    private int age;

    private int number;

    public Player() {
        super();
    }

    public Player(int number, String name, int age) {
        super();
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number + " - " + name + " (" + age + ")";
    }

}

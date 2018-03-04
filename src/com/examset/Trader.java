package com.examset;

/**
 * This class creates contains the information regarding the Trader object.
 * When initialized; only name and age is required. The trading history can be added later on.
 */
public class Trader {
    private String name;
    private int age;
    private int[] tradeHistory;

    public Trader(String name, int age) {
        this.name = name;
        this.age = age;
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

    public int[] getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(int[] tradeHistory) {
        this.tradeHistory = tradeHistory;
    }
}

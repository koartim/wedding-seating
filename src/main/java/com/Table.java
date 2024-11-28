package com;

public class Table {
    public String name;
    public int capacity;

    public Table(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

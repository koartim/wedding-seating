package com;

import java.util.ArrayList;
import java.util.Arrays;

public class Party {
    public String name;
    public int size;
    public String[] dislikes;

    public Party(String name, int size, String[] dislikes) {
        this.name = name;
        this.size = size;
        this.dislikes = dislikes;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String[] getDislikes() {
        return dislikes;
    }

    public void setDislikes(String[] dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "Party{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", dislikes=" + Arrays.toString(dislikes) +
                '}';
    }
}

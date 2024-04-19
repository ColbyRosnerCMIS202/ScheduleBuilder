package com.example.asiancafe;
import java.io.*;
import java.util.*;

class Cashier extends Worker {
    public Cashier(String name, List<Integer> availability) {
        super(name, "Cashier", availability);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Occupation: " + occupation + ", Availability: " + availability.toString();
    }
}
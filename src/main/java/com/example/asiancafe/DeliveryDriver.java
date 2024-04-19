package com.example.asiancafe;
import java.io.*;
import java.util.*;

class DeliveryDriver extends Worker {
    public DeliveryDriver(String name, List<Integer> availability) {
        super(name, "Delivery Driver", availability);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Occupation: " + occupation + ", Availability: " + availability.toString();
    }
}

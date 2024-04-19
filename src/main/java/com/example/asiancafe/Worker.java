package com.example.asiancafe;

import java.io.*;
import java.util.*;

abstract class Worker {
    protected String name;
    protected String occupation;
    protected List<Integer> availability;

    public Worker(String name, String occupation, List<Integer> availability) {
        this.name = name;
        this.occupation = occupation;
        this.availability = availability;
    }

    public abstract String toString();
}


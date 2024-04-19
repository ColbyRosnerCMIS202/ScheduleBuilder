package com.example.asiancafe;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ScheduleBuilder {
    // List to store workers
    private final List<Worker> workers;
    //list to store Last In First Out manner
    private final Deque<Worker> workerStack;
    //Map using their names as key to search up easily
    private final Map<String, Worker> workerMap;

    //constructor for list, LIFO, map
    public ScheduleBuilder() {
        this.workers = new ArrayList<>();
        this.workerStack = new ArrayDeque<>();
        this.workerMap = new HashMap<>();
    }

    // Method to add a worker to the list, stack, and map
    public void addWorker(Worker worker) {
        workers.add(worker);
        workerStack.push(worker);
        workerMap.put(worker.name, worker);
    }

    // Method to write worker information to a file
    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Worker worker : workers) {
                writer.println(worker.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to read worker information from a file
    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            // Parse each line into worker information
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(", ");
                String name = parts[0].split(": ")[1];
                String occupation = parts[1].split(": ")[1];
                List<Integer> availability = Arrays.stream(parts[2].split(": ")[1].split("\\[|,\\s*|]"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                // Create new worker based on occupation and add it to the list, stack, and map
                if (occupation.equals("Delivery Driver")) {
                    workers.add(new DeliveryDriver(name, availability));
                } else if (occupation.equals("Cashier")) {
                    workers.add(new Cashier(name, availability));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
    //method to sort workers based on merge sort
    public void mergeSortWorkers() {
        List<Worker> sortedWorkers = mergeSort(workers);
        workers.clear();
        workers.addAll(sortedWorkers);
    }

    // Merge sort implementation for sorting workers by name
    private List<Worker> mergeSort(List<Worker> list) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Worker> left = mergeSort(list.subList(0, mid));
        List<Worker> right = mergeSort(list.subList(mid, list.size()));

        return merge(left, right);
    }

    // Merge two sorted lists into a single sorted list
    private List<Worker> merge(List<Worker> left, List<Worker> right) {
        List<Worker> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).name.compareTo(right.get(j).name) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }
}

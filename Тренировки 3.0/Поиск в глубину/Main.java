package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static Map<Integer, Boolean> mapOfAttendance;

    static List<Integer> result;

    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String[] array = reader.readLine().split(" ");
            int countVertex = Integer.parseInt(array[0]);
            int countEdge = Integer.parseInt(array[1]);
            edges = new List[countVertex + 1];
            int firstValue, secondValue;
            mapOfAttendance = new HashMap<>();

            edges[1] = new ArrayList<>();
            mapOfAttendance.put(1, false);

            for (int i = 0; i < countEdge; i++) {
                array = reader.readLine().split(" ");
                firstValue = Integer.parseInt(array[0]);
                secondValue = Integer.parseInt(array[1]);
                if(edges[firstValue] == null){
                    edges[firstValue] = new ArrayList<>();
                    mapOfAttendance.put(firstValue, false);
                }
                if(edges[secondValue] == null){
                    edges[secondValue] = new ArrayList<>();
                    mapOfAttendance.put(secondValue, false);
                }
                edges[firstValue].add(secondValue);
                edges[secondValue].add(firstValue);
            }
            result = new ArrayList<>();
            dfs(1);
            System.out.println(result.size());
            result.sort(Integer::compareTo);
            for (int vertex: result) {
                System.out.print(vertex + " ");
            }
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static void dfs(int curVertex){
        if(mapOfAttendance.get(curVertex)){
            return;
        }

        mapOfAttendance.put(curVertex, true);
        result.add(curVertex);

        for (int vertex: edges[curVertex]) {
            dfs(vertex);
        }
    }
}
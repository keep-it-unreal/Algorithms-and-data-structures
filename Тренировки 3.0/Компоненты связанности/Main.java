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

            List<List<Integer>> listOfResults = new ArrayList<>();
            for (int i = 1; i <= countVertex; i++) {
                if(!mapOfAttendance.containsKey(i)){
                    mapOfAttendance.put(i, false);
                    edges[i] = new ArrayList<>();
                }
                if(!mapOfAttendance.get(i)) {
                    result = new ArrayList<>();
                    dfs(i);
                    result.sort(Integer::compareTo);
                    listOfResults.add(result);
                }
            }

            System.out.println(listOfResults.size());

            for (List<Integer> curResult: listOfResults) {
                System.out.println(curResult.size());
                for (int vertex:curResult) {
                    System.out.print(vertex + " ");
                }
                System.out.println();
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
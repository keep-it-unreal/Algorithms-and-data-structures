package org.example;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static List<Integer>[] edges;
    static Map<Integer, Integer> mapOfAttendance;

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
                    mapOfAttendance.put(firstValue, null);
                }
                if(edges[secondValue] == null){
                    edges[secondValue] = new ArrayList<>();
                    mapOfAttendance.put(secondValue, null);
                }
                edges[firstValue].add(secondValue);
                edges[secondValue].add(firstValue);
            }

            boolean isBilobed = true;

            for (int i = 1; i <= countVertex; i++) {
                if(!mapOfAttendance.containsKey(i)){
                    mapOfAttendance.put(i, null);
                    edges[i] = new ArrayList<>();
                }
                if(mapOfAttendance.get(i) == null) {
                    if(!dfs(i, 1)){
                        isBilobed = false;
                        break;
                    }
                }
            }
            if(isBilobed){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    public static boolean dfs(int curVertex, int curValue){
        if(mapOfAttendance.get(curVertex) != null){
            return true;
        }
        boolean isBilobed = true;
        mapOfAttendance.put(curVertex, curValue);
        for (int vertex: edges[curVertex]) {
            if(mapOfAttendance.get(vertex) == null){
                isBilobed = dfs(vertex, 3 - curValue);
            } else if(mapOfAttendance.get(vertex).equals(curValue)){
                return false;
            }
        }
        return isBilobed;
    }
}
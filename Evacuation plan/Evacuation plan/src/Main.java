import java.beans.PropertyEditorSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Boolean> vertexMap = new HashMap<>();
        String[][] resultMatrix;
        String s = "";
        String currentVertex, direction;
        char element;
        Path path = Paths.get("src/input.txt");
        try(BufferedReader reader = Files.newBufferedReader(path)){
            String[] matrixSize = reader.readLine().split(" ");
            resultMatrix = new String[Integer.parseInt(matrixSize[0])][Integer.parseInt(matrixSize[1])];
            for (int i = 0; i < Integer.parseInt(matrixSize[0]); ++i) {
                for (int j = 0; j < Integer.parseInt(matrixSize[1]); ++j) {
                    element = (char)reader.read();
                    if(element == '.'){
                        vertexMap.put(i + "," + j, false);
                    } else if (element == 'S') {
                        s = i + "," + j;
                    }
                }
                reader.readLine();
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }
        System.out.println(vertexMap);
        Iterator<String> iteratorInMap = vertexMap.keySet().iterator();
        currentVertex = iteratorInMap.next();
        int vertexRow = Integer.parseInt(currentVertex.split(",")[0]);
        int vertexColumn = Integer.parseInt(currentVertex.split(",")[1]);

        depthFirstSearch(currentVertex, "", s, vertexMap, resultMatrix);
        while (iteratorInMap.hasNext()){
            currentVertex = iteratorInMap.next();
            if(!vertexMap.get(currentVertex)){
                depthFirstSearch(currentVertex, "", s, vertexMap, resultMatrix);
            }
        }

        for(int i = 0; i < resultMatrix.length; ++i){
            for(int j = 0; j < resultMatrix[i].length; ++ j){
                if (resultMatrix[i][j] == null) {
                    System.out.print("#");
                } else System.out.print(resultMatrix[i][j]);
            }
            System.out.println();
        }



    }

    public static boolean depthFirstSearch(String currentVertex, String direction, String s,
                             HashMap<String,Boolean> vertexMap, String[][] resultMatrix){
        boolean result1, result2, result3, result4;

        vertexMap.put(currentVertex, true);

        int vertexRow = Integer.parseInt(currentVertex.split(",")[0]);
        int vertexColumn = Integer.parseInt(currentVertex.split(",")[1]);

        result1 = processNeigh((vertexRow + 1) + "," + vertexColumn, s, vertexRow, vertexColumn,
                "D", "U", vertexMap, resultMatrix);
        result2 = processNeigh((vertexRow - 1) + "," + vertexColumn, s, vertexRow, vertexColumn,
                "U", "D", vertexMap, resultMatrix);
        result3 = processNeigh(vertexRow + "," + (vertexColumn + 1), s, vertexRow, vertexColumn,
                "R", "L", vertexMap, resultMatrix);
        result4 = processNeigh(vertexRow + "," + (vertexColumn - 1), s, vertexRow, vertexColumn,
                "L", "R", vertexMap, resultMatrix);

        if(result1 || result2 || result3 || result4){
            return true;
        } else {
            resultMatrix[vertexRow][vertexColumn] = direction;
            return false;
        }
    }

    public static boolean processNeigh(String curNeigh,  String s, Integer vertexRow, Integer vertexColumn,
                         String dir, String reverse_dir, HashMap<String,Boolean> vertexMap,
                         String[][] resultMatrix) {
        boolean result = false;
        if(curNeigh.equals(s)) {
            resultMatrix[vertexRow][vertexColumn] = dir;
            result = true;
        } else if (!vertexMap.getOrDefault(curNeigh, true)) {
            result = depthFirstSearch(curNeigh, reverse_dir, s, vertexMap, resultMatrix);
            if (result) {
                resultMatrix[vertexRow][vertexColumn] = dir;
            }
        }
        return result;
    }

}
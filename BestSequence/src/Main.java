import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("input.txt");
        int[] firstArray;
        int[] secondArray;
        int[] thirdArray;
        try(BufferedReader reader = Files.newBufferedReader(path)){
            firstArray = getInts(reader);
            secondArray = getInts(reader);
            thirdArray = getInts(reader);
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        seqOfTwo(firstArray, secondArray);

    }

    private static int[] getInts(BufferedReader reader) throws IOException {
        List<Integer> list = new ArrayList<>();
        char ch;
        int[] array;
        while((ch = (char)reader.read()) != '\n'){
            if(Character.isDigit(ch)){
                list.add((int)ch);
            }
        }
        array = new int[list.size()];
        for(int i = 0; i < list.size(); ++i){
            array[i] = list.get(i);
        }
        reader.readLine();
        return array;
    }


    public static int[] seqOfTwo(int[] firstArray, int[] secondArray){
        int firstPlusSecond = 0;
        int secondPlusFirst = 0;
        int[][] table = new int[firstArray.length + 1][secondArray.length + 1];

        for(int i = 0; i < firstArray.length; ++i){
            for(int j = 0; j < secondArray.length; ++j){
                if(firstArray[i] == secondArray[j]){
                    table[i + 1][j + 1] = 1 + table[i][j];
                }
            }
        }

        for(int i = 1; i < table[table.length - 1].length; ++i){
            if(table[table.length - 1][i] == i){
                firstPlusSecond = table[table.length - 1][i];
            }
        }

        for(int i = 1; i < table.length; ++i){
            if(table[i][table[i].length - 1] == i){
                secondPlusFirst = table[i][table[i].length - 1];
            }
        }

        int sequence;
        int entry = 0;
        for(int i = 1; i < table.length; ++i){
            for(int j = 1; j < table[i].length; ++j){
                sequence = table[i][j];
                if(sequence == firstArray.length){
                    entry = 1;
                } else if (sequence == secondArray.length) {
                    entry = 2;
                }
            }
        }

        return new int[]{firstPlusSecond, secondPlusFirst, entry};
    }
}
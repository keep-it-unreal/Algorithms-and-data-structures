import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Академия Яндекса, https://contest.yandex.ru/contest/39665/problems/E/
public class Main {
    public static void main(String[] args) {
        System.out.println(func("src/input.txt"));
    }

    public static int func(String pathStr){
        int balance = 0;
        int indexOpen = -1;
        int indexClose = -1;
        int currentIndex = 1;
        Path path = Paths.get(pathStr);
        try(BufferedReader reader = Files.newBufferedReader(path)){
            char ch;
            while((ch = (char)reader.read()) != '\n'){
                if(ch == '{'){
                    if(balance == 0) {
                        indexOpen = currentIndex;
                    }
                    ++balance;
                } else if (ch == '}') {
                    if(balance == 0 && indexClose != -1){
                        return -1;
                    } else if (balance == 0) {
                        indexClose = currentIndex;
                    } else {
                        --balance;
                    }
                }
                ++currentIndex;
            }
            if(balance == 0 && indexClose != -1){
                return indexClose;
            } else if(balance == 1 && indexClose == -1){
                return indexOpen;
            } else {
                return -1;
            }

        } catch (IOException ex){
            System.out.println(ex.toString());
            return -1;
        }
    }
}







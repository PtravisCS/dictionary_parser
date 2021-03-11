/*
 * 
 */
package convert_word_to_phone_number;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Paul Travis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in);
        ArrayList<String> words = new ArrayList();
        
        try {
            
            File inFile = new File("D:\\Media\\Documents\\words.txt");
            FileWriter outFile = new FileWriter("D:\\Media\\Documents\\words_shorter_than_11_sorted.json");
            Scanner f = new Scanner(inFile);
            
            while (f.hasNextLine()) {
                
                String line = f.nextLine();
                
                if (line.length() > 1 && line.length() < 11) {
                
                    if (!line.matches(".*[ÈÉÊËÛÙÏÎÀÂÔèéêëûùïîàâôÇçÃãÕõçÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ.'].*")) {
                        
                        if (!words.contains(line)) {
                        
                            words.add(line);
                        
                        }
                        
                    } else if (line.matches(".*['].*") && line.length() > 2) {
                        
                        line = line.replaceAll("[\']", "");
                        
                        if (!words.contains(line)) {
                            
                            words.add(line);
                            
                        }
                        
                    }
                
                }
                
            }
            
            f.close();
            
            words.sort(Comparator.comparing(String::length).reversed());
            
            words = addNumbersToWords(words);
            
            outFile.write("[");
            
            for (String word : words) {
                
                outFile.write(word + "\n");
                
            }
            
            outFile.write("]");
            
            outFile.close();
            
            System.out.println("Fin");
            
        } catch (FileNotFoundException ex) {
            
            System.out.println("An Error Occurred \n" + ex.getMessage());
            
        } catch (IOException ex) {
            
            System.out.println("An Error Occurred \n" + ex.getMessage());
            
        } catch (Exception ex) {
            
            System.out.println("An Error Occurred \n" + ex.getMessage());
        }
        
    }
    
    public static ArrayList<String> addNumbersToWords(ArrayList<String> words) {
        
        for (int i = 0; i < words.size(); i++) {

            String word = words.get(i).toLowerCase();
            char[] word_array = word.toCharArray();
            
            String number = "";
            String json = "";

            for (char character: word_array) {

                number += convertToNumber(character);

            }
            
            json = "{\n\"word\": \"" + word + "\",\n\"number\": \"" + number + "\"\n},";
            
            words.set(i, json);
            
        }
        
        return words;
    }
    
    public static String convertToNumber(char c) {
        
        String number = "";
        
        switch (c) {

        case 'a':
        case 'b':
        case 'c':
            number = "2";
            break;
        case 'd':
        case 'e':
        case 'f':
            number = "3";
            break;
        case 'g':
        case 'h':
        case 'i':
            number = "4";
            break;
        case 'j':
        case 'k':
        case 'l':
            number = "5";
            break;
        case 'm':
        case 'n':
        case 'o':
            number = "6";
            break;
        case 'p':
        case 'q':
        case 'r':
        case 's':
            number = "7";
            break;
        case 't':
        case 'u':
        case 'v':
            number = "8";
            break;
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            number = "9";
            break;
        default :
            number = " ";
            break; 
        }
        
        return number;
        
    }
    
}

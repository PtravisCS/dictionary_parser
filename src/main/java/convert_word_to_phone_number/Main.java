/*
 * 
 */
package convert_word_to_phone_number;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Dictionary Parser
 * This program takes a newline separated list of words and processes them into a JSON array.
 * The array contains each word and it's keypad numerical representation.
 * Further all words less than 2 characters, or greater than 10 are removed from the wordlist.
 * Finally, all words containing accented characters, commas, apostrophes, and other non-alpha characters are removed.
 * 
 * The primary use for the output of this dictionary is to generate or test vanity phone numbers.
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
            
            String inFileLocation = getPathFromUser("Wordlist/Dictionary File Location> ", s);
            String outFileLocation = getPathFromUser("Location to Save JSON File> ", s);
                    
            File inFile = new File(inFileLocation);
            FileWriter outFile = new FileWriter(outFileLocation);
            Scanner f = new Scanner(inFile);
            
            processWordsFromFile(words, f);
            
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
    
    public static String getPathFromUser(String prompt, Scanner s) {
        
        String path = "";
        
        while (!validatePath(path)) {
            System.out.print(prompt);
            path = s.nextLine();
        }
        
        return path;
    }
    
    public static boolean validatePath(String path) {
        
        try {
            
            Paths.get(path);
            
        } catch (InvalidPathException | NullPointerException ex) {        
            return false;      
        }
        
        return true;
    }
    
    public static ArrayList<String> processWordsFromFile(ArrayList<String> words, Scanner f) {
        
        final int MAX_WORD_LENGTH = 10; //The longest length words to keep
        final int MIN_WORD_LENGTH = 2; //The shortest length words to keep
        
        while (f.hasNextLine()) {

            String line = f.nextLine();

            if (line.length() >= MIN_WORD_LENGTH && line.length() <= MAX_WORD_LENGTH) {

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
        
        return words;
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

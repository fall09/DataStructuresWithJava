package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<String> dictionary = new HashTable<>();
        Scanner scan = new Scanner(System.in);
        String user= System.getProperty("user.dir");
        String dictFile = "C:\\Users\\90531\\DictionaryProject\\src\\main\\resources\\dict.txt";
        String path=dictFile;
        String othFile="C:\\Users\\90531\\DictionaryProject\\src\\main\\resources\\dictOther.txt";
        String othPath=othFile;

        // loadDictionary(dictionary,path); // load dictionary before starting
        boolean cont = true;

        while (cont) {
            System.out.println("1. Load a dictionary from a given text file.");
            System.out.println("2. Search for an entry in this dictionary ");
            System.out.println("3. Insert a word to the dictionary ");
            System.out.println("4. Delete a word from the dictionary ");
            System.out.println("5. Given a random text file, do a spell check");
            System.out.println("6. Print all the elements.");
            System.out.println("7. Exit program");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1:
                    loadDictionary(dictionary, path);
                    // System.out.println(dictionary);
                    break;
                case 2:
                    System.out.println("Enter the word:");
                    String word = scan.nextLine();
                    System.out.println("Contains " + word + ": " + dictionary.search(word.toLowerCase()));
                    break;
                case 3:
                    System.out.println("Enter the word:");
                    word = scan.nextLine();
                    dictionary.insert(word.toLowerCase());
                    break;
                case 4:
                    System.out.println("Enter the word to be removed:");
                    word = scan.nextLine();
                    String selection=word.toLowerCase();
                    if (dictionary.search(selection)==true){
                        dictionary.delete(selection);
                        System.out.println(word + " successfully deleted");
                    }
                    else {
                        System.out.println("Dictionary doesn't contain : "+word);
                    }

                    break;
                case 5:
                    SpellChecker spellChecker=new SpellChecker(dictionary,othPath);
                    break;
                case 6:
                    System.out.println(dictionary);
                    break;
                case 7:
                    cont = false;
                    System.exit(0);

                default:
                    System.out.println("Wrong input , please enter again");
                    break;
            }
        }
    }

    public static void loadDictionary(HashTable<String> dictionary, String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.insert(line.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
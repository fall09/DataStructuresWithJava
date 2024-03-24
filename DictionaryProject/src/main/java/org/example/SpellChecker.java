package org.example;

import java.io.*;
import java.util.ArrayList;

public class SpellChecker {
    HashTable<String> dictionary;
    String path;

    SpellChecker(HashTable<String>dictionary , String path){
        this.dictionary=dictionary;
        this.path=path;
        spellCheck();
        System.out.println(" ");
        compareFiles();
    }



    private void spellCheck(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words

                // check every word
                for (String word : words) {
                    //convert to lowercase for comparison
                    String lowerCase = word.toLowerCase();

                    // check if it is in dictionary
                    if (!dictionary.search(lowerCase)) {
                        // correction
                        suggestion(lowerCase);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    private void suggestion(String word){
        System.out.println("Spelling mistake: " + word); //get mistakes from spellCheck

        for (Object words : dictionary.getWords()) { //compare every string with mistake
            String corrects = words.toString();
            int distance = distance(word, corrects); //search for similar words

            if (distance <= 2) {  //correct it with similar words
                System.out.println("Did you mean: " + corrects);
            }
        }

    }


    private boolean charEqual(char a, char b){
        return a == b;  //Equals method didn't work properly, so we did this instead
    }


    private int distance(String str1, String str2) {
        // don't use 0 index
        int m = str1.length();
        int n = str2.length();

        int[][] matrix = new int[m + 1][n + 1];//matrix for calculations

        // values for the first row and column
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
             //if the first string is empty, the distance is length of the second string
                    matrix[i][j] = j;
                } else if (j == 0) {
             //if the second string is empty, the distance if length of the first string
                    matrix[i][j] = i;
                }
            }
        }

        //fill the remaining cells
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int subs;
                if (charEqual(str1.charAt(i - 1), str2.charAt(j - 1))) {
                    subs = matrix[i - 1][j - 1]; // equal , no substitution needed
                } else {
                    subs = matrix[i - 1][j - 1] + 1; //need substitution
                }

                int deletion = matrix[i - 1][j] + 1; //remove a character from the first string
                int insertion = matrix[i][j - 1] + 1; // add a character to the second string

                matrix[i][j] = Math.min(subs, Math.min(deletion, insertion)); //find min , update
            }
        }
        // return minimum distance
        return matrix[m][n];
    }



    private void compareFiles() {
        try (BufferedReader reader2 = new BufferedReader(new FileReader(path))) {
            String line;
            int lineNumber = 1;

            while ((line = reader2.readLine()) != null) {
                // split the line into words
                String[] words = line.split("\\s+");

                // compare each word
                for (int i = 0; i < words.length; i++) {

                    String lowerCase = words[i].toLowerCase();

                    if (!dictionary.search(lowerCase)) {
                        System.out.println("Line " + lineNumber + ", Word " + ": " + lowerCase); //print lines
                    }
                }

                lineNumber++;
            }

            // find extra word in the 2nd file
            while ((line = reader2.readLine()) != null) {
                String[] words= line.split("\\s+");
                for (String word : words) {
                    String lowerCase = word.toLowerCase();
                    if (!dictionary.search(lowerCase)) {
                        System.out.println("Extra Word: " + lowerCase); // print extra words
                    }
                }
                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

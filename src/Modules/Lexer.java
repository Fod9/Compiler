package Modules;

import Lexique.Tokens;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {


    public static void main(String fileName) {

        // Recupère le fichier
        File file = new File("src/" + fileName);

        ArrayList<String> tokenized_file = new ArrayList<>();

        try {
            // Lis le fichier
            Scanner scanner = new Scanner(file);
            // Set semi-colon as delimiter
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                // Si la ligne est vide ou commencer par //, passer à la lsigne suivante
                if (line.equals("") || line.trim().startsWith("//")) {
                    continue;
                }
                // Transforme la ligne en tableau de String
                ArrayList<String> tokenized_line = Parser(line);
                // Transforme le tableau de String en tableau de tokens [(TOKEN, "content"), ...]
                ArrayList<ArrayList<String>> parsed_line = Tokenizer(tokenized_line);
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }

    }

    public static ArrayList<String> Parser(String line) {

        // Fonction qui transforme une ligne en tableau de String (token)
        // Exemple: "print("Hello World");" -> ["print", "(", "Hello World", ")", ";"]s

        // Crée un pattern pour matcher les tokens
        Pattern pattern = Pattern.compile("\\w+|\\(|\\)|\"[^\"]*\"|,|;| |\\{|\\}|\\+|-|\\*|/|~|&|\\||<|<=|>|>=|==|=|<>");
        Matcher matcher = pattern.matcher(line);


        // Crée un nouveau ArrayList
        ArrayList<String> tokens = new ArrayList<>();
        // Ajoute les tokens au ArrayList
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;

    }

    public static ArrayList<ArrayList<String>> Tokenizer(ArrayList<String> tokenized_line) {

        ArrayList<ArrayList<String>> parsed_line = new ArrayList<ArrayList<String>>();

        tokenized_line.forEach((token) -> {
            boolean isMatch = false;
            for (Tokens t : Tokens.values()) {
                Matcher matcher = t.pattern.matcher(token);
                if (matcher.find()) {
                    ArrayList<String> newList = new ArrayList<String>();
                    newList.add(t.toString());
                    newList.add(token);
                    parsed_line.add(newList);
                    isMatch = true;
                }
            }
            if (!isMatch) {
                System.out.println("Error: " + token + " is not a valid token");
            }
        });


        System.out.println(parsed_line);

        return parsed_line;
    }




}

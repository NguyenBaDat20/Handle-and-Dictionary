package controller;

import common.Validation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class Dictionary {

    Validation val = new Validation();

    public static String getValue(String mes) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mes);
        return sc.nextLine();
    }
    HashMap<String, String> translate = new HashMap<>();

    public void addWord() {
        readFile();
        String key = getValue("Enter English: ");
        String value = getValue("Enter VietNamese: ");
        if (translate.containsKey(key)) {
            System.out.println("Word Already Exists");
            if (!val.checkInputYN()) {
                return;
            } else {
                translate.put(key, value);
                System.out.println("Word Has Been Updated");
            }
        } else {
            translate.put(key, value);
            System.out.println("Add Word Successful");
        }
    }

    public void deleteWord() {
        String key = getValue("Enter Word You Want To Delete: ");
        if (translate.containsKey(key)) {
            translate.remove(key);
            System.out.println("Delete Successful");
        } else {
            System.out.println("Not Found");
        }
    }

    public void translateWord() {
        readFile();
        String key = getValue("Enter English: ");
        if (translate.containsKey(key)) {
            System.out.println("VietNamese: " + translate.get(key));
        } else {
            System.out.println("Not Found");
        }
    }
    public String checkKey (String input){
        if (translate.containsKey(input)) {
            return null;
        } else {
            return input;
        }
    }
    public void readFile() {
        Validation v = new Validation();
        File file = new File("src\\Word.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                String arr[] = line.split(",");
                if (arr.length == 2) {
                    String key = checkKey(arr[0]);
                    String value = arr[1];
                    if(key != null && value != null){
                        translate.put(key, value);
                    }
                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

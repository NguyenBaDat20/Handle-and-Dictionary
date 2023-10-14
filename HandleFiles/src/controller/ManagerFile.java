/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import model.Person;
import view.Menu;

/**
 *
 * @author admin
 */
public class ManagerFile extends Menu {

    public ManagerFile(String title, String[] options) {
        super(title, options);
    }

    Scanner sc = new Scanner(System.in);

    public String getValue(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public String checkInputPathFile() {
        System.out.print("Enter path file: ");
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public void personInfo() {
        ArrayList<Person> ps = new ArrayList<>();
        if (ps == null) {
            return;
        }
        String pathFile = getValue("Enter path file:");
        ps = getListPerson(pathFile);
        double money = Double.parseDouble(getValue("Enter Money:"));
        displayListPerson(ps, money);
    }

    public static double checkSalary(String salary) {
        double salaryResult = 0;
        try {
            salaryResult = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salaryResult = 0;
        } finally {
            return salaryResult;
        }
    }

    public ArrayList<Person> getListPerson(String pathFile) {
        ArrayList<Person> ps = new ArrayList<>();
        File file = new File(pathFile);
        //check file exist or not and path must be file
        if (!file.exists() || !file.isFile()) {
            System.err.println("Path doesn't exist");
            return null;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferReader.readLine()) != null) {
                String[] infoPerson = line.split(";");
                ps.add(new Person(infoPerson[0], infoPerson[1],
                        checkSalary(infoPerson[2])));

            }
        } catch (Exception e) {
            System.err.println("Can't read file.");
        }
        return ps;
    }

    public void displayListPerson(ArrayList<Person> ps, double money) {
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Address", "Money");
        for (Person person : ps) {
            if (person.getMoney() >= money) {
                System.out.printf("%-20s%-20s%-20.1f\n", person.getName(),
                        person.getAddress(), person.getMoney());
            }
        }
        Collections.sort(ps);
        System.out.println("Max: " + ps.get(0).getName());
        System.out.println("Min: " + ps.get(ps.size() - 1).getName());
    }

    public String getNewContent(String pathFile) {
        StringBuilder newContent = new StringBuilder();
        File file = new File(pathFile);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String word = input.next();
                newContent.append(word).append(" ");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Can't read file");
        }
        return newContent.toString();
    }

    public void writeNewContent(String pathFileOutput, String content) {
        FileWriter fileWriter = null;
        File file = new File(pathFileOutput);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {

            }
        }
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
            System.err.println("Coppy done...");
        } catch (IOException ex) {
            System.err.println("Can’t write file");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void coppyNewFile() {
        String pathFileInput = getValue("Enter Source:");
        String pathFileOutput = getValue("Enter new File Name:");
        String content = getNewContent(pathFileInput);
        // System.out.println(content);
        writeNewContent(pathFileOutput, content);
    }

    @Override
    public void excuted(int n) {
        if (n == options.length) {
            loop = false;
            System.out.println("Exit Program !");
            System.exit(0);
        } else {
            switch (n) {
                case 1:
                    personInfo();
                    break;
                case 2:
                    coppyNewFile();
                    break;
            }
        }
    }
}

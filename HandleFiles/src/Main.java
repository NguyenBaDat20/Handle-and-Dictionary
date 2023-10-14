
import controller.ManagerFile;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class Main {
       public static void main(String[] args) {
        String[] options = {"Find person info", "Copy Text to new file", "Exit"};
        ManagerFile m = new ManagerFile("File Processing", options);
        m.run();
    }
}

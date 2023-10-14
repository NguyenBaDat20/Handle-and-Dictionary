package Test;

import View.Menu;
import controller.Dictionary;
import static controller.Dictionary.getValue;

public class Main extends Menu<String> {
    Dictionary dic = new Dictionary();
    public Main(String td, String[] mc) {
        super(td, mc);
    }

    @Override
    public void excute(int n) {
        if (n == this.mChon.size()) {
            System.out.println("Exit Successfully");
            running = false;
        } else {
            switch (n) {
                case 1:
                    dic.addWord();
                    break;
                case 2:
                    dic.deleteWord();
                    break;
                case 3:
                    dic.translateWord();
                    break;
                default:
                    excute(Integer.parseInt(getValue("Please choose again: ")));
                    break;
            }
        }
    }
    public static void main(String[] args) {
        String[] choice = {"Add Word", "Delete Word", "Translate", "Exit"};
        Main a = new Main("Dictionary program", choice);
        a.runSwitchCase();
    }
}

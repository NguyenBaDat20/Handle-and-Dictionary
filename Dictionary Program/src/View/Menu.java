package View;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> mChon;
    protected boolean running;

    public Menu() {
    }

    public Menu(String td, String[] mc) {
        title = td;
        mChon = new ArrayList<>();
        for (String s : mc) {
            mChon.add((T) s);
        }
    }

    public void Display() {
        System.out.println(title);
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < mChon.size(); i++) {
            System.out.println("|  " + (i + 1) + ". " + mChon.get(i));
        }
        System.out.println("--------------------------------------------------");
    }

    public int getSelected() {
        Display();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter selection: ");
        return sc.nextInt();
    }

    public abstract void excute(int n);

    public void runSwitchCase() {
        running = true;
        while (running) {
            int n = getSelected();
            excute(n);
        }
    }

    public void runIfElse() {
        int n = getSelected();
        excute(n);
    }
}

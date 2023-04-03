package main;
import javax.swing.JFrame;
public class Main {
    public static void main(String[] args) {
        /**
         * Peter was here
         */
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tower Unlock");
        /**
         * Window initialization
         */
        Screen screen = new Screen();
        window.add(screen);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        screen.setUpGame();
        screen.startGameThread();


    }
}
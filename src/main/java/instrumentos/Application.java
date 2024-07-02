package instrumentos;
import instrumentos.presentation.tipos.View;
import javax.swing.*;

public class Application {
    public static void main(String[] args) throws Exception {
        View.simon = new View();
        View.simon.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        View.simon.setContentPane(View.simon.getPanel());
        View.simon.setSize(600,600);
        View.simon.setLocationRelativeTo(null);
        View.simon.setVisible(true);
    }
}

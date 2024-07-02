package instrumentos.presentation.tipos;
import javax.swing.*;
import java.awt.event.*;
//posee la parte visual de la pantalla principal
public class View extends JFrame {
    public static View simon;
    private JPanel panel1;
    private JTextField nombre;
    private JButton jugarButton;
    private JButton Salir;
    private JLabel rojo;
    private JLabel Azul;
    public View() throws Exception {
         this.dispose();
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(WIDTH);
            }
        });
        jugarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nombre.getText().isEmpty()) {
                    try {
                        game.juego = new game(nombre.getText());
                        game.juego.dispose();
                        game.juego.setVisible(true);
                        game.juego.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(panel1, "Debes ingresar un nombre");
                }
            }
        });

    }

    public JPanel getPanel() {
        return panel1;
    }


}

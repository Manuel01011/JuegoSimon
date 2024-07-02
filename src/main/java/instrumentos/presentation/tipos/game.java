package instrumentos.presentation.tipos;
import instrumentos.logic.Reder;
import instrumentos.logic.Service;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//posee la parte visual de la pantalla secundaria asi como sus componentes visuales y auditivos
public class game extends JFrame implements ActionListener, MouseListener {
    public Reder reder;
    Timer timer = new Timer(10, this);
    String nickname;
    public static game juego;
    public Clip boton;
    public Clip fail;

    public game(String a) throws Exception {

        try {
            boton = loadSound("src/main/java/instrumentos/Button.wav");
            fail = loadSound("src/main/java/instrumentos/Fail.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

        reder = new Reder();
        if (controller != null) {
            controller.empezar();
        }
        addMouseListener(this);
        add(reder);
        timer.start();
        setSize(600, 600);
        nickname = a;
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics2D gra) {
        gra.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gra.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int s = (int) (0.80 * Math.min(getWidth(), getHeight()));
        int n = 4;

        if (controller.contador() == 1) {
            int inicio = (0 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.red);
        } else {
            int inicio = (0 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.red.darker());
        }

        if (controller.contador() == 2) {
            int inicio = (1 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.green);
        } else {
            int inicio = (1 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.green.darker());
        }
        if (controller.contador() == 3) {
            int inicio = (2 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.yellow);

        } else {
            int inicio = (2 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.yellow.darker());
        }

        if (controller.contador() == 4) {
            int inicio = (3 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.blue);

        } else {
            int inicio = (3 * 360 - 180) / n;
            int finall = 360 / n;
            reder.reajustar(gra, cx, cy, s, inicio, finall, Color.blue.darker());
        }

        gra.setColor(Color.black);
        gra.fillOval(cx - s / 6, cy - s / 6, s / 3, s / 3);

        if (controller.perdio()) {
            gra.setColor(Color.BLACK);
            gra.setFont(new Font("Abadi", 1, 18));
            gra.drawString("Ronda: " + controller.ronda(), 20, 30);
            gra.drawString("Colores: " + controller.indexPatron() + "/" + controller.tamPatron(), 420, 30);
            gra.setFont(new Font("Abadi", Font.BOLD, 15));
            gra.setColor(Color.red);
            gra.drawString("¡Perdiste!", 270, 280);
            gra.drawString("¡Click para comenzar!", 224, 300);
        } else {
            gra.setColor(Color.black);
            gra.setFont(new Font("Abadi", Font.BOLD, 48));
            gra.setColor(Color.BLACK);
            gra.setFont(new Font("Abadi", 1, 18));
            gra.drawString("Ronda: " + controller.ronda(), 20, 30);
            gra.drawString("Colores: " + controller.indexPatron() + "/" + controller.tamPatron(), 420, 30);
        }

        if(controller.ronda() == 1 || controller.ronda() == 2) {
            gra.setColor(Color.black);
            gra.drawString("Tiempo: " + 10 + " s", 5, 520);
        }else if(controller.ronda() == 3 || controller.ronda() == 4){
            if(controller.ronda() == 3){
                gra.setColor(Color.red);
                gra.drawString("La siguiente ronda es Rapida! ", 5, 560);
            }
            gra.drawString("Tiempo: " + 4 + " s", 5, 520);
            if(controller.ronda() == 4){
                timer.setDelay(0);
            }
        } else if(controller.ronda() == 5 || controller.ronda() == 6){
            timer.setDelay(1);
            if(controller.ronda() == 6){
                gra.setColor(Color.red);
                gra.drawString("La siguiente ronda es Rapida! ", 5, 560);
            }
            gra.drawString("Tiempo: " + 4 + " s", 5, 520);
        } else if(controller.ronda() == 7 ||controller.ronda() == 8){
            if(controller.ronda() == 7){
                timer.setDelay(0);
            }
            gra.drawString("Tiempo: " + 3 + " s", 5, 520);
            timer.setDelay(1);
        }  else if(controller.ronda() == 9 || controller.ronda() == 10){
            timer.setDelay(1);
            gra.drawString("Tiempo: " + 3 + " s", 5, 520);
        }else{
            gra.drawString("Tiempo: " + 3 + " s", 5, 520);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            controller.crear(reder,this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    Controller controller = new Controller();
    Model model;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        int bandera = 0;

        if (!controller.patroon() && !controller.perdio()) {
            BufferedImage image = new BufferedImage(this.reder.getWidth(), this.reder.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = image.createGraphics();
            this.reder.paint(g2d);
            g2d.dispose();

            Color color = new Color(image.getRGB(x, y));
            if (color.equals(Color.RED.darker())) {
                bandera = 1;
            } else if (color.equals(Color.GREEN.darker())) {
                bandera = 2;
            } else if (color.equals(Color.YELLOW.darker())) {
                bandera = 3;
            } else if (color.equals(Color.BLUE.darker())) {
                bandera = 4;
            }
        }
        if (bandera != 0) {

            controller.check(bandera,this);
            this.playBotonSound();
        } else {
            this.playFail();
            JOptionPane.showMessageDialog(null, nickname +": su puntuacion fue de: "+controller.ronda() * 20);
            Service.instance().getData().ronda = 0;
            controller.empezar();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void playSoundForColor(int color) {
        switch (color) {
            case 1:
                this.playBotonSound();
                break;
            case 2:
                this.playBotonSound();
                break;
            case 3:
                this.playBotonSound();
                break;
            case 4:
                this.playBotonSound();
                break;
            default: break;
        }
    }

    private Clip loadSound(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File soundFile = new File(filePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat audioFormat = audioInputStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
        Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(audioInputStream);
        return clip;
    }

    public void playBotonSound() {
        if (boton.isRunning()) {
            boton.stop();
        }
        boton.setFramePosition(0);
        boton.start();
    }
    public void playFail() {
        if (fail.isRunning()) {
            fail.stop();
        }
        fail.setFramePosition(0);
        fail.start();
    }


}

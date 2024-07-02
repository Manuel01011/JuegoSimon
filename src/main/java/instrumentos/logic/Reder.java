package instrumentos.logic;
import instrumentos.presentation.tipos.game;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
//clase para poder realizar la figura deciada asi como pintarla y redimencionarla
public class Reder extends JPanel {

    protected void paintComponent(Graphics gra){
        super.paintComponent(gra);
        if(game.juego != null) {
            game.juego.paint((Graphics2D) gra);
        }
    }
    public void reajustar(Graphics2D bg, int cx, int cy, int s, int start, int end, Color c) {
        double r = Math.PI / 180.0;
        int x0 = (int) (cx + s * 0.5 * Math.cos(-start * r));
        int y0 = (int) (cy + s * 0.5 * Math.sin(-start * r));
        int x1 = (int) (cx + s * 0.5 * Math.cos(-(start + end) * r));
        int y1 = (int) (cy + s * 0.5 * Math.sin(-(start + end) * r));

        bg.setColor(c);
        bg.fillArc(cx - s / 2, cy - s / 2, s, s, start, end);

        bg.setColor(Color.black);
        bg.setStroke(new BasicStroke(16f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        bg.drawLine(cx, cy, x0, y0);
        bg.drawLine(cx, cy, x1, y1);
        bg.drawArc(cx - s / 2, cy - s / 2, s, s, start, end);
    }
}
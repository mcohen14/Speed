import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Card extends JLabel{
    int number;
    String suit;

    public Card(int n, String s){
        super(n + s);
        number = n;
        suit = s;
        if(number == 11){
            setText("J"+ suit);
        }
        if(number == 12){
            setText("Q"+ suit);
        }
        if(number == 13){
            setText("K"+ suit);
        }
        if(number == 1){
            setText("A"+ suit);
        }
        if(suit.equals("♥") || suit.equals("♦")){
            setForeground(Color.RED);
        }

        Font f = new Font("SansSerif", Font.BOLD, 32);
        Border b = BorderFactory.createLineBorder(Color.black);
        setBorder(b);
        setFont(f);
        setBackground(Color.WHITE);
        setOpaque(true);
        setVerticalAlignment(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
    }


}
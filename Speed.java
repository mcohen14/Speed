import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.util.*;

public class Speed extends JFrame{
    Deck deck1 = new Deck();
    ArrayList<Card> drawPile1 = new ArrayList<Card>();
    ArrayList<Card> drawPile2 = new ArrayList<Card>();
    ArrayList<Card> replacePile = new ArrayList<Card>();
    Card[] topCards = new Card[2];
    JPanel jp = new JPanel();
    JButton moreCardsBtn = new JButton("More Cards");
    ArrayList<Card> hand1 = new ArrayList<Card>();
    ArrayList<Card> hand2 = new ArrayList<Card>();
    javax.swing.Timer timer;
    javax.swing.Timer timer2;
    java.util.Timer timer3;
    JPanel gameOverPanel = new JPanel();
    JLabel result = new JLabel();
    int b = 0;
    JPanel titlePanel = new JPanel();
    JButton startBtn = new JButton("START");
    JLabel welcome = new JLabel("WELCOME TO SPEED");
    JComboBox<Double> cb;
    JLabel titleLbl = new JLabel("<html>Choose how fast the computer goes through its cards (in seconds per card).<br>A smaller number means a harder game.<br>Then click START to begin.<html>");
    int time;
    JLabel endPrompt = new JLabel("Do you want to play again?");
    JButton yesBtn = new JButton("YES");
    JButton noBtn = new JButton("NO");

    public Speed(){
        int y = 52;
        for(int i = 1; i<=20; i++){
            int x = (int)(Math.random() * y);
            drawPile1.add(deck1.remove(x));
            y--;
        }
        for(int i = 1; i<=20; i++){
            int x = (int)(Math.random() * y);
            drawPile2.add(deck1.remove(x));
            y--;
        }
        replacePile = deck1.deck;

        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setTitle( "" );

        jp.setBackground( Color.WHITE );
        jp.setPreferredSize( new Dimension( 600, 300 ) );
        jp.setLayout( null );

        Font f1 = new Font("SansSerif", Font.BOLD, 18);

        moreCardsBtn.setBounds(470, 230, 120, 60);
        moreCardsBtn.addActionListener(new ButtonListener());
        moreCardsBtn.setFont(f1);
        moreCardsBtn.setMargin(new Insets(0, 0, 0, 0));
        moreCardsBtn.setBackground(Color.CYAN);
        moreCardsBtn.setFocusable(false);
        jp.add(moreCardsBtn);

        timer = new javax.swing.Timer( 100, new TimerListener() );

        timer3 = new java.util.Timer();

        for(int i = 0; i<5; i++){
            hand1.add(drawPile1.remove(0));
            hand1.get(i).setBounds(90*i+20, 230, 80, 60);
            hand1.get(i).addMouseListener(new MouseAdapter1());
            hand1.get(i).addMouseMotionListener(new MouseAdapter1());
            jp.add(hand1.get(i));
        }
        for(int i = 0; i<5; i++){
            hand2.add(drawPile2.remove(0));
            hand2.get(i).setBounds(90*i+20, 10, 80, 60);
            jp.add(hand2.get(i));
        }

        for(int i = 0; i<2; i++){
            topCards[i] = replacePile.remove(0);
            topCards[i].setBounds(130*i+150, 120, 80, 60);
            jp.add(topCards[i]);
        }

        Font f2 = new Font("SansSerif", Font.BOLD, 32);
        Font f3 = new Font("SansSerif", Font.BOLD, 28);

        jp.setVisible(false);

        titlePanel.setBackground( Color.WHITE );
        titlePanel.setPreferredSize( new Dimension( 600, 300 ) );
        titlePanel.setLayout( null );
        titlePanel.setVisible(true);

        startBtn.setBounds(250, 200, 100, 50);
        startBtn.setFont(f3);
        startBtn.setFocusable(false);
        startBtn.addActionListener(new ButtonListener());
        startBtn.setMargin(new Insets(0, 0, 0, 0));
        titlePanel.add(startBtn);

        welcome.setBounds(130, 50, 340, 50);
        welcome.setFont(f2);
        titlePanel.add(welcome);

        gameOverPanel.setBackground( Color.WHITE );
        gameOverPanel.setPreferredSize( new Dimension( 600, 300 ) );
        gameOverPanel.setLayout( null );
        gameOverPanel.setVisible(true);

        result.setBounds(0, 50, 600, 100);
        result.setFont(f2);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverPanel.add(result);

        endPrompt.setBounds(0, 180, 600, 50);
        endPrompt.setFont(f3);
        endPrompt.setHorizontalAlignment(SwingConstants.CENTER);
        gameOverPanel.add(endPrompt);

        yesBtn.setBounds(250, 230, 80, 50);
        yesBtn.setFont(f3);
        yesBtn.setMargin(new Insets(0, 0, 0, 0));
        yesBtn.addActionListener(new ButtonListener());
        gameOverPanel.add(yesBtn);

        noBtn.setBounds(350, 230, 80, 50);
        noBtn.setFont(f3);
        noBtn.setMargin(new Insets(0, 0, 0, 0));
        noBtn.addActionListener(new ButtonListener());
        gameOverPanel.add(noBtn);

        Double[] d = {0.25, 0.5, 0.75, 1.0};
        cb = new JComboBox<Double>(d);
        cb.setBounds(100, 200, 75, 20);
        cb.setSelectedItem(0.75);
        titlePanel.add(cb);

        titleLbl.setBounds(80, 100, 450, 50);
        titleLbl.setVerticalAlignment(SwingConstants.TOP);
        titlePanel.add(titleLbl);

        getContentPane().add( titlePanel );
        pack();

    }

    public void display() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        } );
    }

    private class TimerTask1 extends TimerTask{
        public void run(){
            for(int i = 0; i<hand2.size(); i++){
                hand2.get(i).setBounds(90*i+20, 10, 80, 60);
            }
        }
    }


    int a = 0;
    private class TimerListener2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            jp.repaint();
            Card c = hand2.get(a%hand2.size());
            int x = c.getX();
            int y = c.getY();
            c.setLocation(x, y+20);
            jp.repaint();
            if((topCards[0].number + 1 == c.number) || (topCards[0].number - 1 == c.number) || (topCards[0].number == 1 && c.number == 13) || (topCards[0].number == 13 && c.number == 1)){
                b = 0;
                replacePile.add(new Card(topCards[0].number, topCards[0].suit));
                topCards[0].setText(c.getText());
                topCards[0].number = c.number;
                topCards[0].suit = c.suit;
                topCards[0].setForeground(c.getForeground());
                jp.remove(c);
                hand2.remove(c);
                jp.repaint();
            }
            else if((topCards[1].number + 1 == c.number) || (topCards[1].number - 1 == c.number) || (topCards[1].number == 1 && c.number == 13) || (topCards[1].number == 13 && c.number == 1)){
                b = 0;
                replacePile.add(new Card(topCards[1].number, topCards[1].suit));
                topCards[1].setText(c.getText());
                topCards[1].number = c.number;
                topCards[1].suit = c.suit;
                topCards[1].setForeground(c.getForeground());
                jp.remove(c);
                hand2.remove(c);
                jp.repaint();
            }
            a++;
            b++;
        }
    }


    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(drawPile1.size() == 0 && hand1.size() == 0){
                timer.stop();
                timer2.stop();
                jp.setVisible(false);
                gameOverPanel.setVisible(true);
                result.setForeground(new Color(0, 128, 0));
                result.setText("GAME OVER \n YOU WIN");
                getContentPane().add( gameOverPanel );
                pack();
            }else if(drawPile2.size() == 0 && hand2.size() == 0){
                timer.stop();
                timer2.stop();
                jp.setVisible(false);
                gameOverPanel.setVisible(true);
                result.setForeground(Color.RED);
                result.setText("GAME OVER \n YOU LOSE");
                getContentPane().add( gameOverPanel );
                pack();
            }

            if(drawPile1.size() <= 0){
                moreCardsBtn.setVisible(false);
            }


            if(hand2.size() <= 5 && drawPile2.size()>0){
                int x = hand2.size();
                for(int i = 4; i>=x; i--){
                    hand2.add(drawPile2.remove(0));
                }
                for(int i = 0; i<hand2.size(); i++){
                    jp.add(hand2.get(i));
                }
                jp.repaint();
            }
            if(b == 2*Math.max(hand1.size(), hand2.size())){
                for(int i = 0; i<2; i++){
                    topCards[i].setText(replacePile.get(0).getText());
                    topCards[i].number = replacePile.get(0).number;
                    topCards[i].suit = replacePile.get(0).suit;
                    topCards[i].setForeground(replacePile.get(0).getForeground());
                    replacePile.remove(0);
                }
                b = 0;
            }
        }
    }

    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(moreCardsBtn)){
                if(hand1.size() <= 5 && drawPile1.size()>0){
                    int x = hand1.size();
                    for(int i = 4; i>=x; i--){
                        if(drawPile1.size()>0){
                            hand1.add(drawPile1.remove(0));
                        }
                    }
                    for(int i = 0; i<hand1.size(); i++){
                        hand1.get(i).setBounds(90*i+20, 230, 80, 60);
                        hand1.get(i).addMouseListener(new MouseAdapter1());
                        hand1.get(i).addMouseMotionListener(new MouseAdapter1());
                        jp.add(hand1.get(i));
                    }
                    jp.repaint();
                }
            }
            if(e.getSource().equals(startBtn)){
                timer.start();
                time = (int) ((double)cb.getSelectedItem()*1000.0);
                timer2 = new javax.swing.Timer(time , new TimerListener2() );
                timer2.start();
                timer3.schedule(new TimerTask1(), 0, 100);
                titlePanel.setVisible(false);
                jp.setVisible(true);
                getContentPane().add(jp);
                pack();
            }
            if(e.getSource().equals(yesBtn)){
                main(new String[0]);
            }
            if(e.getSource().equals(noBtn)){
                System.exit(0);
            }

        }
    }


    private class MouseAdapter1 extends MouseAdapter{
        Rectangle r;
        @Override
        public void mousePressed( MouseEvent e ){
            Card c = (Card) e.getComponent();
            r = c.getBounds();
        }
        @Override
        public void mouseDragged( MouseEvent e ){
            Card c = (Card) e.getComponent();
            c.setLocation(e.getLocationOnScreen().x-35, e.getLocationOnScreen().y-70);
        }
        public void mouseReleased( MouseEvent e ){
            Card c = (Card) e.getComponent();
            Area a1 = new Area(topCards[0].getBounds());
            Area a2 = new Area(topCards[1].getBounds());
            Area a3 = new Area(c.getBounds());
            if(a1.intersects(a3.getBounds2D())){
                if((topCards[0].number + 1 == c.number) || (topCards[0].number - 1 == c.number) || (topCards[0].number == 1 && c.number == 13) || (topCards[0].number == 13 && c.number == 1)){
                    b = 0;
                    replacePile.add(new Card(topCards[0].number, topCards[0].suit));
                    topCards[0].setText(c.getText());
                    topCards[0].number = c.number;
                    topCards[0].suit = c.suit;
                    topCards[0].setForeground(c.getForeground());
                    jp.remove(c);
                    hand1.remove(c);
                    for(int i = 0; i<hand1.size(); i++){
                        hand1.get(i).setBounds(90*i+20, 230, 80, 60);
                    }
                    jp.repaint();
                }
            }
            if(a2.intersects(a3.getBounds2D())){
                if((topCards[1].number + 1 == c.number) || (topCards[1].number - 1 == c.number) || (topCards[1].number == 1 && c.number == 13) || (topCards[1].number == 13 && c.number == 1)){
                    b = 0;
                    replacePile.add(new Card(topCards[1].number, topCards[1].suit));
                    topCards[1].setText(c.getText());
                    topCards[1].number = c.number;
                    topCards[1].suit = c.suit;
                    topCards[1].setForeground(c.getForeground());
                    jp.remove(c);
                    hand1.remove(c);
                    for(int i = 0; i<hand1.size(); i++){
                        hand1.get(i).setBounds(90*i+20, 230, 80, 60);
                    }
                    jp.repaint();
                }
            }
            c.setBounds(r);
        }
    }

    public static void main(String[] args){
        Speed s = new Speed();
        s.display();
        s.setResizable(false);
    }
}
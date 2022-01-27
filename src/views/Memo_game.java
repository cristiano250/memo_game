package views;

import controllers.CardController;
import model.Card;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import  javax.swing.*;

public class Memo_game implements ActionListener {
    private JFrame mainFrame;
    private Container mainContentPane;
    private ImageIcon cardIcon[];

    public Memo_game(){
        this.mainFrame=new JFrame("Memo game");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(500,500);
        this.mainContentPane=this.mainFrame.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane,BoxLayout.PAGE_AXIS));

        JMenuBar menuBar=new JMenuBar();
        this.mainFrame.setJMenuBar((menuBar));
        JMenu gameMenu=new JMenu("Menu");
        menuBar.add(gameMenu);
        newMenuItem("Nowa gra",gameMenu,this);
        newMenuItem("Wyjście",gameMenu,this);
        this.cardIcon=loadCardIcons();
    }

    private ImageIcon[] loadCardIcons() {
        ImageIcon icon[]=new ImageIcon[9];
        for (int i=0;i<9;i++){
            String fileName="images/default/card"+i+".png";
            icon[i]=new ImageIcon(fileName);
        }
        return icon;
    }

    public JPanel makeCards(){
        JPanel panel=new JPanel(new GridLayout(4,4));
        ImageIcon backIcon=this.cardIcon[8];
        CardController controller=new CardController();
        int cardsToAdd[]=new int[16];
        for (int i=0;i<8;i++){
            cardsToAdd[2*i]=i;
            cardsToAdd[2*i+1]=i;
        }
        randomizeCardArray(cardsToAdd);
        for(int i=0;i<cardsToAdd.length;i++){
            int num=cardsToAdd[i];
            Card newCard=new Card(controller, this.cardIcon[num],backIcon,num);
            panel.add(newCard);
        }
        return  panel;

    }

    private void randomizeCardArray(int[] t) {
        Random randomizer=new Random();
        for (int i = 0; i<t.length; i++){
            int d=randomizer.nextInt(t.length);
            int s=t[d];
            t[d]=t[i];
            t[i]=s;

        }
    }


    private void newMenuItem(String string, JMenu menu, ActionListener listener) {
        JMenuItem newItem=new JMenuItem(string);
        newItem.setActionCommand(string);
        newItem.addActionListener(listener);
        menu.add(newItem);

    }

    public void newGame(){
        this.mainContentPane.removeAll();
        this.mainContentPane.add(makeCards());
        this.mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getActionCommand().equals("Nowa gra")) newGame();
        if(arg0.getActionCommand().equals("Wyjście")) System.exit(0);
    }
}

package controllers;

import model.Card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Vector;

public class CardController implements ActionListener {
    private Vector turnedCards;
    private Timer turnDownTimer;
    private final int turnDownDelay=1000;

    public CardController(){
        this.turnedCards=new Vector(2);
        this.turnDownTimer=new Timer(this.turnDownDelay,this);
        this.turnDownTimer.setRepeats(false);
    }
    public boolean turnUp(Card card){
        if(this.turnedCards.size()<2) return doAddCard(card);
        return false;
    }

    private boolean doAddCard(Card card){
        this.turnedCards.add(card);
        if(this.turnedCards.size()==2){
            Card otherCard=(Card)this.turnedCards.get(0);
            if(otherCard.getNum()==card.getNum())
                this.turnedCards.clear();
            else this.turnDownTimer.start();
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent arg0){
        for(int i=0;i<this.turnedCards.size();i++){
            Card card=(Card)this.turnedCards.get(i);
            card.turnDown();
        }
        this.turnedCards.clear();
    }
}

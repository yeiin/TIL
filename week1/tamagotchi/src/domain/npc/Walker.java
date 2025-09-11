package domain.npc;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Walker extends Npc{

    Random rand = new Random();
    public Walker(String name) {
        super(name);
    }

    public void goToWalk(){
        System.out.println(greeting());
        try {
            sleep(rand.nextInt(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String greeting(){
        return "[Walker]: Hi! I'm " + name + ". Nice to meet you!\n" +
                "I'll make sure to take good care of your Tamagotchi on walks!";
    }

    public int calculateEffects(int number){
        return number * (age%10 +1);
    }

    public String walkReport(String dogName, int happinessGain, int healthGain, int hungerIncrease) {
        return "[Walker]: " + dogName + " and I just finished a nice walk! 🐾\n" +
                "Happiness + " + happinessGain + "\n" +
                "Health    + " + healthGain + "\n" +
                "Hunger    + " + hungerIncrease + "\n" +
                "It was a fun and refreshing time!";
    }
}

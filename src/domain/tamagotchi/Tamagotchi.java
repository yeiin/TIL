package domain.tamagotchi;

import domain.Character;
import enums.WeatherType;

public class Tamagotchi extends Character {

    protected int health;
    protected int hunger;
    protected int bond;

    public Tamagotchi(String name) {
        super(name);
        this.health = 50;
        this.hunger = 50;
        this.bond = 50;
    }

    public void increaseHealth(int health){
        this.health += health;
        this.health =  Math.min(this.health, 100);
    }

    public void decreaseHealth(int health){
        this.health -= health;
        this.health = Math.max(this.health, 0);
    }

    public void increaseHunger(int hunger){
        this.hunger += hunger;
        this.hunger =  Math.min(this.hunger, 100);
    }

    public void decreaseHunger(int hunger){
        this.hunger -= hunger;
        this.hunger = Math.max(this.hunger, 0);
    }

    public void increaseBond(int bond){
        this.bond += bond;
        this.bond =  Math.min(this.bond, 100);
    }

    public void decreaseBond(int bond){
        this.bond -= bond;
        this.bond = Math.max(this.bond, 0);
    }

    public void updateStatusByTime(){
        increaseHunger(10);
        decreaseBond(5);
        decreaseHappiness(5);

        if (happiness>=80 && bond>=80){
            growOlder();
            happiness = 50;
            bond = 50;
        }
    }

    public void updateStatusByWeather(WeatherType weatherType){
        switch(weatherType){
            case SUNNY -> increaseHealth(10);
            case RAINY -> decreaseHealth(10);
            case STORMY -> increaseBond(10);
            case CLOUDY -> decreaseHealth(5);
            case SNOWY -> increaseHappiness(5);
        }
    }

    public void feed(){
        decreaseHunger(20);
    }

    public void play(){
        increaseHappiness(20);
        increaseBond(20);
    }

    public boolean checkHealthAndBond(){
        return health > 0 && bond > 20;
    }


    public String getStatus(boolean isSelected){
        return String.format(
                "===================================== Tamagotchi Status =====================================\n" +
                        "Name      : %s\n" +
                        "isSelected: %s\n" +
                        "Age       : %d\n" +
                        "Happiness : %d\n" +
                        "Alive     : %s\n" +
                "=============================================================================================",
                name,
                isSelected ? "Yes" : "No",
                age,
                happiness,
                alive ? "Yes" : "No"
        );
    }
}

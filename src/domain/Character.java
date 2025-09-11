package domain;

public class Character {
    public String name;
    protected int age;
    protected int happiness;
    protected boolean alive;

    public Character(String name) {
        this.name = name;
        this.age = 0;
        this.happiness = 50;
        this.alive = true;
    }

    public void increaseHappiness(int happiness) {
        this.happiness += happiness;
        this.happiness = Math.min(this.happiness, 100);
    }

    public void decreaseHappiness(int happiness) {
        this.happiness -= happiness;
    }

    public void growOlder() {
        this.age ++;
    }

    public int getAge() {
        return age;
    }

    public void die(){
        this.alive = false;
    }

    public String getStatus(){
        return String.format(
                "===================================== Character Status =====================================\n" +
                        "Name      : %s\n" +
                        "Age       : %d\n" +
                        "Happiness : %d\n" +
                        "Alive     : %s\n" +
                "=============================================================================================",
                name,
                age,
                happiness,
                alive ? "Yes" : "No"
        );
    }

}

package domain.tamagotchi;

public class Pet extends Tamagotchi{
    private final String cry;
    private final String type;

    public Pet(String name, String type, String cry) {
        super(name);
        this.type = type;
        this.cry = cry;
    }

    private void sound(){
        System.out.println(cry);
    }

    @Override
    public void feed(){
        decreaseHunger(10);
    }

    @Override
    public void play(){
        increaseHappiness(10);
        increaseBond(10);
    }

    @Override
    public void die(){
        this.alive = false;
        sound();
    }

    @Override
    public String getStatus(){
        return String.format(
                "[Status] Name: %s | Type: %s | Age: %d | Health: %d | Hunger: %d | Happiness: %d | Bond: %d | Alive: %s",
                name,
                type,
                age,
                health,
                hunger,
                happiness,
                bond,
                alive ? "Yes" : "No"
        );
    }

    @Override
    public String getStatus(boolean isSelected){
        return String.format(
                "[Status] Name: %s | IsSelected: %s | Type: %s | Age: %d | Health: %d | Hunger: %d | Happiness: %d | Bond: %d | Alive: %s",
                name,
                isSelected ? "Yes" : "No",
                type,
                age,
                health,
                hunger,
                happiness,
                bond,
                alive ? "Yes" : "No"
        );
    }

}

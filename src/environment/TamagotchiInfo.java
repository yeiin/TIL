package environment;

import enums.TamagotchiType;

public record TamagotchiInfo(
        String name,
        String description,
        TamagotchiType tamagotchiType,
        String shape,
        String cry
) {

    public String toString(){
        return name + " - " + description;
    }
}

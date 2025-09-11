package environment;

import domain.npc.Npc;
import domain.npc.Walker;
import domain.tamagotchi.Pet;
import domain.tamagotchi.Tamagotchi;
import enums.TamagotchiType;
import enums.WeatherType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {

    public List<Tamagotchi> tamagotchis = new ArrayList<>();
    public List<TamagotchiInfo> characters = new ArrayList<>();
    public List<Npc> npcs = new ArrayList<>();

    public boolean isRunning = true;

    private WeatherType weather;

    private Tamagotchi selectedTamagotchi;

    public Game() {
        weather = WeatherType.SUNNY;
        initCharacters();
        initNpc();
    }

    private void initNpc(){
        npcs.add(new Walker("walker John"));
    }

    private void initCharacters() {
        characters.add(new TamagotchiInfo("Maltese", "Loves cuddles and affection", TamagotchiType.PET,
                """
                             /‾‾\\ /‾‾\\
                            / ^  o  \\
                           (   ᴥ   )
                            \\       /
                         🐾  '-----'
                             /     \\
                            (       )
                             ‾‾‾‾‾‾
                        """,
                """
                             /‾‾\\ /‾‾\\
                            / T  T  \\
                           (   ᴥ   )
                            \\       /
                             '-----'
                             /     \\
                            (       )
                             ‾‾‾‾‾‾
                           Woof! Woof!\
                        """));

        characters.add(new TamagotchiInfo("Munchkin", "Energetic and playful",TamagotchiType.PET,
                """
                     /\\_/\\
                    ( o_^ )
                     (   )
                      ) (
                     (_|_)
                    """,
                """
                     /\\_/\\
                    ( T_T )  meow meow...
                     (   )
                      ) (
                     (_|_)
                    """));
    }

    public Tamagotchi getSelectedTamagotchi() {
        return selectedTamagotchi;
    }

    public void updateSelectedTamagotchi(int index) {
        selectedTamagotchi = tamagotchis.get(index);
    }

    public void adoptNewTamagotchi(int index, String name) {

        TamagotchiInfo tamagotchiInfo = characters.get(index);
        if(tamagotchiInfo.tamagotchiType() == TamagotchiType.PET) {
            tamagotchis.add(new Pet(name, tamagotchiInfo.name(), tamagotchiInfo.cry()));
        }else{
            tamagotchis.add(new Tamagotchi(name));
        }
        selectedTamagotchi = tamagotchis.get(tamagotchis.size()-1);
        System.out.println(selectedTamagotchi.getStatus(true));
    }

    public void feed(){
        if(selectedTamagotchi instanceof Pet pet){
            pet.feed();
            System.out.println(pet.getStatus());
        }else{
            selectedTamagotchi.feed();
        }
    }

    public void play(){
        if(selectedTamagotchi instanceof Pet pet){
            pet.play();
            System.out.println(pet.getStatus());
        }else{
            selectedTamagotchi.play();
        }
    }

    public void status(){
        for(Tamagotchi tamagotchi : tamagotchis){
            boolean isSelected = tamagotchi==selectedTamagotchi;
            System.out.println(tamagotchi.getStatus(isSelected));
        }
    }

    public void exit(){
        isRunning = false;
    }

    public void updateWeatherEffects(WeatherType weatherType){
        if(weather == weatherType){
            return;
        }

        System.out.println("Weather is changed " + weather + " to "+weatherType);
        weather = weatherType;

        for(Tamagotchi tamagotchi : tamagotchis){
            tamagotchi.updateStatusByWeather(weather);
            tamagotchi.getStatus();
        }

    }

    public void updateTimeEffects(){
        Iterator<Tamagotchi> it = tamagotchis.iterator();
        while (it.hasNext()) {
            Tamagotchi tamagotchi = it.next();
            tamagotchi.updateStatusByTime();

            if (!tamagotchi.checkHealthAndBond()) {
                System.out.println(tamagotchi.name + " is dead");
                if (tamagotchi instanceof Pet pet) {
                    pet.die();
                } else {
                    tamagotchi.die();
                }
                it.remove();
                continue;
            }

            if (tamagotchi.getAge() >= 5) {
                System.out.println(tamagotchi.name + " is starting new journal");
                System.out.println("Please say goodbye.");
                tamagotchi.die();
                it.remove();
            }
        }
    }
}

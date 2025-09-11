package environment;

import domain.npc.Npc;
import domain.npc.Walker;
import enums.WeatherType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Time implements Runnable {
    public Game game;
    private LocalDateTime lastNpcInteractionTime;
    private LocalDateTime lastWeatherInteractionTime;
    private final WeatherType[] weatherTypes = WeatherType.values();
    Random random = new Random();

    public Time(Game game) {
        this.game = game;
        this.lastNpcInteractionTime = LocalDateTime.now();
        this.lastWeatherInteractionTime = LocalDateTime.now();
    }

    @Override
    public void run() {
        while(game.isRunning){
            try {
                if(game.tamagotchis.isEmpty()){
                    sleep(1000);
                    continue;
                }

                if (lastNpcInteractionTime.isBefore(LocalDateTime.now().minusSeconds(4))) {
                    Collections.shuffle(game.npcs);
                    Optional<Npc> tNpc = game.npcs.stream().findFirst();

                    if(tNpc.isPresent()) {
                        if(tNpc.get() instanceof Walker walker) {
                            walker.goToWalk();
                            int happiness = walker.calculateEffects(10);
                            int health  = walker.calculateEffects(10);
                            int hunger = walker.calculateEffects(10);
                            System.out.println(walker.walkReport(game.getSelectedTamagotchi().name, happiness, health, hunger));
                            System.out.println("Now, please continue by selecting a command: ");

                        }else{
                            tNpc.get().greeting();
                        }
                    }

                    lastNpcInteractionTime = LocalDateTime.now();
                }

                if (lastWeatherInteractionTime.isBefore(LocalDateTime.now().minusSeconds(30))) {
                    int rNum = random.nextInt(weatherTypes.length);
                    WeatherType nextWeatherType = weatherTypes[rNum];
                    game.updateWeatherEffects(nextWeatherType);

                    lastWeatherInteractionTime = LocalDateTime.now();
                }

                game.updateTimeEffects();
                sleep(3000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

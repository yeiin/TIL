package domain.npc;

import domain.Character;

import java.time.LocalDateTime;

public class Npc extends Character {

    public LocalDateTime lastInteractionTime;

    public Npc(String name) {
        super(name);
        lastInteractionTime = LocalDateTime.now();
    }

    public String greeting(){
        return "[ NPC ]: Hi! I'm "+name+" Nice to meet you!";
    }

}

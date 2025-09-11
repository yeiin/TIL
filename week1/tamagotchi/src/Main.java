import environment.Game;
import environment.Time;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static Game game;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        printWelcomeMessage();
        game = new Game();

        Runnable time = new Time(game);
        Thread timeThread = new Thread(time);
        timeThread.start();

        while (game.isRunning){

            if(game.tamagotchis.isEmpty()){
                adoptNewPet();
                continue;
            }

            int funcKey = selectFunction();

            switch (funcKey){
                case 1:
                    game.feed();
                    break;
                case 2:
                    game.play();
                    break;
                case 3:
                    game.status();
                    break;
                case 4:
                    adoptNewPet();
                    break;
                case 5:
                    changeSelected();
                    break;
                case 6:
                    game.exit();
                    break;
                default:
            }
        }

    }

    private static void changeSelected(){
        System.out.println("\n\"=============================================================================================");
        System.out.println("                 🐾 Which pet you selected to do? 🐾");
        System.out.println("\"=============================================================================================");

        for(int i=0; i<game.tamagotchis.size(); i++){
            boolean isSelected = game.tamagotchis.get(i).equals(game.getSelectedTamagotchi());
            System.out.println(i+1+")"+game.tamagotchis.get(i).getStatus(isSelected));
        }

        while(true){

            try{
                int choice = sc.nextInt();

                if(choice>=1 && choice<=game.tamagotchis.size()){
                    game.updateSelectedTamagotchi(choice-1);
                    break;
                }
                System.out.println("Please choose a number between 1 and "+game.characters.size()+"!");
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }


        }

    }

    private static void adoptNewPet() {
        if(game.tamagotchis.size() >= 2){
            System.out.println("You cannot adopt more Tamagotchis. (Maximum reached!)");
            return;
        }
        int characterNum = selectCharacter();
        String name = selectName(game.characters.get(characterNum).shape());
        game.adoptNewTamagotchi(characterNum, name);
    }

    private static int selectFunction(){
        System.out.println("\n\"=============================================================================================");
        System.out.println("                 🐾 What would you like to do? 🐾");
        System.out.println("\"=============================================================================================");
        System.out.println("1️⃣  feed    : Give your pet some food 🍖 (Reduces hunger)");
        System.out.println("2️⃣  play    : Play together 🎾 (Increases happiness & bond, but increases hunger)");
        System.out.println("3️⃣  status  : Check current pet status 📊");
        System.out.println("4️⃣  adopt   : You can adopt new pets. but the maximum pet is 2");
        System.out.println("5️⃣  change  : You can change selected pet. Only selected pet is effects by commands");
        System.out.println("6️⃣  exit    : Quit the game 🏠.. but you can't meet your dog anymore..");
        System.out.println("\"=============================================================================================");
        System.out.print("Enter command number: ");

        while(true){
            try{
                int funcKey = sc.nextInt();

                if(funcKey>=1 && funcKey<=5){
                    return funcKey;
                }
                System.out.println("Please choose a number between 1 between 6!");
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private static int selectCharacter(){
        System.out.println("Let’s get started!\n Choose your breed and start taking care of your new friend! 🐾");

        System.out.println("Choose your pet breed:\n");
        for(int i=0; i<game.characters.size(); i++){
            System.out.println(i+1+")"+game.characters.get(i).toString());
        }

        while(true){
            try{
                int choice = sc.nextInt();

                if(choice>=1 && choice<=game.characters.size()){
                    return choice-1;
                }
                System.out.println("Please choose a number between 1 and 2!");
            }catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
    }

    private static String selectName(String shape){
        System.out.println(shape);
        System.out.println("Your new friend has arrived!\n" +
                "What would you like to name it? ");
        sc.nextLine();
        String temp = sc.nextLine();
        return temp;
    }

    private static void printWelcomeMessage() {
        System.out.println("=============================================================================================");
        System.out.println("                      🐶 Welcome to Tamagotchi Game! 🐾");
        System.out.println("=============================================================================================");
    }

}
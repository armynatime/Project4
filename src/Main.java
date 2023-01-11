import java.util.Random;

public class Main {
    public static final String ANTI_RESET = "\u001B[0m";
    public static final String ANTI_YELLOW = "\u001B[33m";
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int[] heroesHealth = {300, 250, 200,650};
    public static int[] heroesDamage = {20, 25, 30,0};
    public static String[] heroesAttackType = {"Logan", "Cyclops", "Wizard", "Medic"};
    public static int medicIndex;
    public static int index = 0;
    public static void medic(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[i] != heroesHealth[medicIndex] && heroesHealth[medicIndex] > 0){
                int medicHelp = 100;
                heroesHealth[i] = heroesHealth[i] + medicHelp;
                System.out.println("Medic Help" + medicHelp + " " +heroesAttackType[i]);
                break;
            }
        }
    }

    public static void medicIndex(){
        for (String v:
             heroesAttackType) {
            if (v == "Medic"){
                medicIndex = index;
                System.out.println("medicIndex = " + index);
                break;
            }
            index++;
        }
    }
    public static String bossBarrier;


    public static void main(String[] args){
        printStatistic();
        medicIndex();
        while (!isGameFinish()){
            round();
            medic();
        }
    }


    public static void bossTypeBarrier(){
        Random random = new Random();
        int index = random.nextInt(heroesAttackType.length);
        bossBarrier = heroesAttackType[index];
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (bossBarrier.equals(heroesAttackType[i])){
                System.out.println(ANTI_YELLOW + "Boss barrier equals" + ANTI_RESET + bossBarrier);
                bossHealth += heroesDamage[i];
            }
        }
    }

    public static void heroesHits(){
        bossTypeBarrier();
        for (int hero:
             heroesDamage) {
            if (bossHealth > 0){
                if (hero > bossHealth){
                    bossHealth = 0;
                }else {
                    bossHealth -= hero;
                }
            }
        }
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                heroesHealth[i] -= bossDamage ;
            }
        }
    }

    public static void round(){
        bossHits();
        heroesHits();
        printStatistic();
    }
    public static boolean isGameFinish(){
        if (bossHealth <= 0){
            System.out.println("HEROES WIN!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("BOSS WIN!!");
        }
        return allHeroesDead;
    }
    public static void printStatistic(){
        System.out.println("Boss health " + bossHealth + ", " + " damage [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("Hero " + heroesAttackType[i] + " " + "health " + heroesHealth[i] + " damage [" + heroesDamage[i] + "]" );
        }
        System.out.println("*************************");
    }

}
package kg.geeks.game.players;

import static kg.geeks.game.logic.RPG_Game.random;

public class Ludoman extends Hero {

    public Ludoman(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.GAMBLE);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        int die_1 = random.nextInt(6) + 1;
        int die_2 = random.nextInt(6) + 1;

        if (die_1 == die_2) {
            int damage = die_1 * die_2;
            boss.setHealth(boss.getHealth() - damage);
            System.out.println(this.getName() + " rolled double " + die_1 + " and dealt " + damage + " damage to " + boss.getName());
        } else {
            int damage = die_1 + die_2;
            Hero targetHero = heroes[random.nextInt(heroes.length)];
            if (targetHero.getHealth() <= 0) {
                targetHero = heroes[random.nextInt(heroes.length)];
                targetHero.setHealth(targetHero.getHealth() - damage);
            } else{
                targetHero.setHealth(targetHero.getHealth() - damage);
            }

            System.out.println(this.getName() + " rolled " + die_1 + " and " + die_2 + " and dealt " + damage + " damage to " + targetHero.getName());
        }
    }
}

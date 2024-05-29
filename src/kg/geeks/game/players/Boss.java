package kg.geeks.game.players;

import kg.geeks.game.logic.RPG_Game;

public class Boss extends GameEntity {
    private SuperAbility defence;

    public Boss(int health, int damage, String name) {
        super(health, damage, name);
    }

    public SuperAbility getDefence() {
        return defence;
    }

    public void chooseDefence() {
        SuperAbility[] variants = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(variants.length);
        this.defence = variants[randomIndex];
    }

    public void attack(Hero[] heroes) {
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() <= 0) {
                continue;
            }
            if (heroes[i] instanceof Berserk && this.defence != SuperAbility.BLOCK_DAMAGE_AND_REVERT) {
                int coefficient = RPG_Game.random.nextInt(2) + 1; // 1,2
                ((Berserk) heroes[i]).setBlockedDamage(this.getDamage() / (5 * coefficient));
                heroes[i].setHealth(heroes[i].getHealth() - (this.getDamage() - ((Berserk) heroes[i]).getBlockedDamage()));
            } else {
                heroes[i].setHealth(heroes[i].getHealth() - this.getDamage());
            }
        }
    }

    @Override
    public String toString() {
        return "BOSS " + super.toString() + " defence: " + defence;
    }
}

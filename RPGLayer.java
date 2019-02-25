/**
 * Temporary class to handle RPG style combat for text demo. Only handles 1 character v X enemy combat, with Y pickups.
 */
public class RPGLayer
{
    private Player character;
    private ActiveEntity[] enemies; // swap with enemy class
    private Entity[] pickups; // swap with pickup class

    RPGLayer(Player characterInCombat, ActiveEntity[] enemiesInCombat, Entity[] pickupsInCombat) // add in arrays of enemy and pickups.
    {
        character = characterInCombat;
        enemies = enemiesInCombat;
        pickups = pickupsInCombat;
    }

    /**
     * Outputs the current situation of combat.
     */
    private void combatReport()
    {
        for(int i = 0; i < enemies.length - 1; i++)
        {
            System.out.print("[" + i + "]" + enemies[i].name + "(" + enemies[i].health + ")" + ", ");
        }
        if(enemies.length > 1)
        {
            System.out.print("and ");
        }
        System.out.print("[" + (enemies.length - 1) + "]" + enemies[enemies.length - 1].name + "(" + enemies[enemies.length - 1].health + ")" + " are fighting.");

        System.out.print("\n\n");

        for(int i = 0; i < pickups.length - 1; i++)
        {
            System.out.print("[" + i + "]" + pickups[i].name + ", ");
        }
        if(pickups.length > 1)
        {
            System.out.print("and ");
        }
        System.out.print("[" + (pickups.length - 1) + "]" + pickups[pickups.length - 1].name + " are on the field.");
    }

    /**
     * Handles a turn of combat.
     * @param initiator Entity initiating turn.
     * @param input Input from console.
     * @return If the player's turn is consumed by the action.
     */
    private boolean combatPlayerPhase(Entity initiator, String input)
    {
        String[] inputProcessed = input.toLowerCase().split(" ");
        String action = inputProcessed[0];
        int index = 0;
        if(inputProcessed.length > 1)
        {
            try
            {
                index = Integer.parseInt(inputProcessed[1]);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid Input");
                return false;
            }
        }

        switch(action)
        {
            case "Help":
            {
                System.out.println("Attack <Enemy Index> to attack specified enemy.");
                System.out.println("Retreat to retreat to last room.");
                System.out.println("Take <Pickup Index> to take pickup.");
                return false;
            }
            case "Attack":
            {
                if(index == -1)
                {
                    System.out.printf("%s attacked %s for %d damage.\n", initiator.name, character.name, initiator.damage);
                    character.checkInteraction(initiator);
                    return false;
                }
                if(enemies.length >= index)
                {
                    System.out.printf("%s attacked %s for %d damage.\n", initiator.name, enemies[index].name, character.damage);
                    // Enemy interaction handling here
                    return true;
                }
            }
            case "Retreat":
            {
                character.Controls("Retreat");
                return true;
            }
        }

        System.out.println("Invalid Input");
        return false;
    }
}

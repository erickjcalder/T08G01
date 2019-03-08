import java.util.ArrayList;

public class INTERACTION_CHECKER_BASE_PUT_IN_MAIN
{
    /**
     * List of all entities to keep track of. {Arraylist of pickups, Arraylist of Active entities}
     * Pickups should respond to same team, Actives should respond to different teams.
     */
    private ArrayList[] entities = {new ArrayList<Entity>(), new ArrayList<ActiveEntity>()};

    private Entity[] Interaction_Handler()
    {
        for(int activeIndex = 0; activeIndex < entities[1].size(); activeIndex++)
        {
            for(int activeIndexComparison = activeIndex + 1; activeIndexComparison < entities[1].size(); activeIndexComparison++)
            {
                if(entities[1].get(activeIndex).getTeam() != entities[1].get(activeIndexComparison).getTeam())
                {
                    //if they intersect.
                    entities[1].get(activeIndex).checkInteraction(entities[1].get(activeIndexComparison));
                }
            }
            for(int pickupIndex = 0; pickupIndex < entities[0].size(); pickupIndex++)
            {
                if(entities[1].get(activeIndex).getTeam() != entities[0].get(pickupIndex).getTeam())
                {
                    //if they intersect.
                    entities[1].get(activeIndex).checkInteraction(entities[0].get(pickupIndex));
                    entities[0].get(pickupIndex).checkInteraction(entities[1].get(activeIndex));
                }
            }
        }
    }
}

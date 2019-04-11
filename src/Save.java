import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Used for saving and loading.
 * @author Justin
 * @version Final
 */
public class Save
{
    /**
     * Used to save game.
     * @param map Map to save.
     * @param handler Handler to save.
     * @param filename Filename to save to.
     */
    static void saveGame(Map map, Handler handler, String filename)
    {
        // Building document
        Document save;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();

            save = db.newDocument();

            Node rootElement = save.createElement("data");

            // Saves map.
            rootElement.appendChild(map.Save(save));

            // Saves handler
            rootElement.appendChild(handler.Save(save));

            save.appendChild(rootElement);


            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                tr.transform(new DOMSource(save), new StreamResult(new FileOutputStream(filename)));

            }
            catch (TransformerException | IOException e)
            {
                e.printStackTrace();
            }
        }
        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Used to load game.
     * @param game Instance of game to load to.
     * @param filename Filename to load from.
     */
    static void loadGame(Game game, String filename)
    {
        // Building document to load xml to.
        Document load;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();

            load = db.parse(filename);

            load.getDocumentElement().normalize();
            Element root = load.getDocumentElement();

            Handler handler = new Handler();
            LevelHandler levelHandler = new LevelHandler(handler);

            // Loading handler.
            Element handlerElement = (Element) root.getElementsByTagName("Handler").item(0);
            Element objects = (Element) handlerElement.getElementsByTagName("objects").item(0);

            // Loading entities in handler.
            for(Node node = objects.getFirstChild(); node.getNextSibling() != null; node = node.getNextSibling())
            {
                if(node instanceof Element)
                {
                    Entity e = null;
                    String entityType = node.getNodeName();
                    Element element = (Element) node;

                    switch(entityType)
                    {
                        // Building Player class.
                        case "Player":
                            e = new Player(0, 0, levelHandler);
                            break;
                        // Building Projectile class.
                        case "Projectile":
                            e = new Projectile(0, 0, 0, 0, handler);
                            break;
                        // Building Pickup class.
                        case "Pickups":
                            e = new Pickups(handler, levelHandler);
                            break;
                        // Building arbitrary class with class reflection. Works for all classes using same constructor parameters as Entity.
                        default:
                            Class<?> clazz = Class.forName(entityType);
                            Constructor<?> constructor = clazz.getConstructor(int.class, int.class, LevelHandler.class);
                            e = (Entity) constructor.newInstance(0, 0, levelHandler);
                            break;
                    }

                    setEntityValues(e, element);

                    // Adds built Entity to handler.
                    handler.addObject(e);
                }
            }

            // Adds built handler to levelHandler.
            levelHandler.setHandler(handler);

            // Loading map.
            Element mapElement = (Element) root.getElementsByTagName("Map").item(0);
            int width = Integer.parseInt(getTextFromNode(mapElement, "width"));
            int height = Integer.parseInt(getTextFromNode(mapElement, "height"));
            Map map = new Map(width, height, levelHandler);
            map.setStartX(Integer.parseInt(getTextFromNode(mapElement, "startX")));
            map.setStartY(Integer.parseInt(getTextFromNode(mapElement, "startY")));
            map.setCurrentX(Integer.parseInt(getTextFromNode(mapElement, "currentX")));
            map.setCurrentY(Integer.parseInt(getTextFromNode(mapElement, "currentY")));

            ArrayList<ArrayList<Room>> roomMap = new ArrayList<>();

            // Loading rooms.
            for(Node node = mapElement.getFirstChild(); node.getNextSibling() != null; node = node.getNextSibling())
            {
                if(node instanceof Element)
                {
                    Element element = (Element) node;
                    if(node.getNodeName().equals("roomList"))
                    {
                        ArrayList<Room> roomList = new ArrayList<>();
                        for(Node roomNode = element.getFirstChild(); roomNode.getNextSibling() != null; roomNode = roomNode.getNextSibling())
                        {
                            if(roomNode instanceof Element)
                            {
                                Element roomElement = (Element) roomNode;
                                if(roomNode.getNodeName().equals("Room"))
                                {
                                    String[] directions = getTextFromNode(roomElement, "NorthSouthEastWest").split(" ");
                                    Room room = new Room(Boolean.parseBoolean(directions[0]), Boolean.parseBoolean(directions[1]), Boolean.parseBoolean(directions[2]), Boolean.parseBoolean(directions[3]),
                                            Boolean.parseBoolean(getTextFromNode(roomElement,"unresolvedEvent")), Integer.parseInt(getTextFromNode(roomElement, "roomType")), levelHandler);


                                    // Loading pickups in room.
                                    LinkedList<Pickups> pickupsArrayList = new LinkedList<>();
                                    Element pickupsList = (Element) roomElement.getElementsByTagName("pickupsList").item(0);

                                    if(pickupsList != null)
                                    {
                                        for(Node pickupNode = pickupsList.getFirstChild(); pickupNode.getNextSibling() != null; pickupNode = pickupNode.getNextSibling())
                                        {
                                            if(pickupNode instanceof Element)
                                            {
                                                Element pickupElement = (Element) pickupNode;
                                                Pickups pickup = new Pickups(handler, levelHandler);

                                                setEntityValues(pickup, pickupElement);

                                                // Adds built pickup to pickupsArrayList.
                                                pickupsArrayList.add(pickup);
                                            }
                                        }
                                    }


                                    // Loading enemies in room.
                                    LinkedList<Enemy> enemyArrayList = new LinkedList<>();
                                    Element enemyList = (Element) roomElement.getElementsByTagName("enemyList").item(0);

                                    if(enemyList != null)
                                    {
                                        for(Node enemyNode = enemyList.getFirstChild(); enemyNode.getNextSibling() != null; enemyNode = enemyNode.getNextSibling())
                                        {
                                            if(enemyNode instanceof Element)
                                            {
                                                Element enemyElement = (Element) enemyNode;

                                                // Builds arbitrary class with class reflection.
                                                Class<?> clazz = Class.forName(enemyElement.getTagName());
                                                Constructor<?> constructor = clazz.getConstructor(int.class, int.class, LevelHandler.class);
                                                Enemy enemy = (Enemy) constructor.newInstance(0, 0, levelHandler);

                                                setEntityValues(enemy, enemyElement);

                                                // Adds built Enemy to enemyArrayList.
                                                enemyArrayList.add(enemy);
                                            }
                                        }
                                    }

                                    // Finishes creating room by adding built arrays and levelHandler.
                                    room.setPickupList(pickupsArrayList);
                                    room.setEnemyList(enemyArrayList);
                                    room.setLevelHandler(levelHandler);

                                    // Adds built room to list.
                                    roomList.add(room);
                                }
                            }
                        }
                        // Adds built list of rooms to map.
                        roomMap.add(roomList);
                    }
                }
            }

            // Converts 2D arraylist into 2D array.
            Room[][] roomArrayMap = new Room[width][];

            for(int i = 0; i < roomMap.size(); i++)
            {
                roomArrayMap[i] = roomMap.get(i).toArray(new Room[0]);
            }

            // Adds built room map to map.
            map.setRoomLoc(roomArrayMap);

            // Adds built map to levelHandler.
            levelHandler.setMap(map);

            // Sends built levelHandler to create new game.
            game.newGame(levelHandler);
        }
        catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Gets the child element's value.
     * @param node Element "base" to search from.
     * @param tagName Child element to get value from.
     * @return String with value of child element.
     */
    static private String getTextFromNode(Element node, String tagName)
    {
        if(node.getElementsByTagName(tagName).item(0).getFirstChild() != null)
        {
            return node.getElementsByTagName(tagName).item(0).getFirstChild().getTextContent();
        }
        else
        {
            // Sanity check returns empty string if the chosen child element is null.
            return "";
        }
    }

    /**
     * Sets entity values.
     * @param e Entity to set values for.
     * @param element Element to extract fields from.
     */
    static private void setEntityValues(Entity e, Element element)
    {
        // Gets fields from Entity.
        e.setX(Integer.parseInt(getTextFromNode(element, "X")));
        e.setY(Integer.parseInt(getTextFromNode(element, "Y")));
        e.setMapX(Integer.parseInt(getTextFromNode(element, "mapX")));
        e.setMapY(Integer.parseInt(getTextFromNode(element, "mapY")));
        e.setVelocityX(Float.parseFloat(getTextFromNode(element, "velocityX")));
        e.setVelocityY(Float.parseFloat(getTextFromNode(element, "velocityY")));
        e.setDamage(Integer.parseInt(getTextFromNode(element, "damage")));
        e.setDamageMult(Double.parseDouble(getTextFromNode(element, "damageMult")));
        e.setName(getTextFromNode(element, "name"));
        e.setTeam(getTextFromNode(element, "team"));

        // Checks if class inherits from ActiveEntity. If so, gets fields from ActiveEntity.
        if(ActiveEntity.class.isAssignableFrom(e.getClass()))
        {
            ((ActiveEntity) e).setHealth(Integer.parseInt(getTextFromNode(element, "health")));
            ((ActiveEntity) e).setHealthMax(Integer.parseInt(getTextFromNode(element, "healthMax")));
            ((ActiveEntity) e).setArmor(Integer.parseInt(getTextFromNode(element, "armor")));
        }
    }
}

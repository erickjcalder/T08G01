import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class Save
{
    static void saveGame(Map map, Handler handler, String filename)
    {
        Document save;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();

            save = db.newDocument();

            Node rootElement = save.createElement("data");

            rootElement.appendChild(map.Save(save));

            rootElement.appendChild(handler.Save(save));

            save.appendChild(rootElement);


            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(save), new StreamResult(new FileOutputStream(filename)));

            }
            catch (TransformerException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch(ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    static void loadGame(Game game)
    {
        Document load;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();

            load = db.parse("save.xml");

            Element root = load.getDocumentElement();

            Handler handler = new Handler();
            LevelHandler levelHandler = new LevelHandler(handler);

            NodeList objects = root.getAttributeNode("Handler").getFirstChild().getChildNodes();
            Entity e = null;
            for(int i = 0; i < objects.getLength(); i++)
            {
                NamedNodeMap attributes = objects.item(i).getAttributes();

                // Replace with class reflection.
                switch(objects.item(i).getTextContent())
                {
                    case "Player":
                    {
                        e = new Player(0, 0, levelHandler, game);
                    }

                    case "Projectile":
                    {
                        e = new Projectile(0, 0, 0, 0, handler);
                    }

                    case "Pickups":
                    {
                        e = new Pickups(handler, levelHandler);
                    }
                }

                e.setX(Integer.parseInt(attributes.getNamedItem("X").getTextContent()));
                e.setY(Integer.parseInt(attributes.getNamedItem("Y").getTextContent()));
                e.setMapX(Integer.parseInt(attributes.getNamedItem("mapX").getTextContent()));
                e.setMapY(Integer.parseInt(attributes.getNamedItem("mapY").getTextContent()));
                e.setVelocityX(Float.parseFloat(attributes.getNamedItem("velocityX").getTextContent()));
                e.setVelocityY(Float.parseFloat(attributes.getNamedItem("velocityY").getTextContent()));
                e.setDamage(Integer.parseInt(attributes.getNamedItem("damage").getTextContent()));
                e.setDamageMult(Double.parseDouble(attributes.getNamedItem("damageMult").getTextContent()));
                e.setName(attributes.getNamedItem("name").getTextContent());
                e.setTeam(attributes.getNamedItem("team").getTextContent());

                if(ActiveEntity.class.isAssignableFrom(e.getClass()))
                {
                    ((ActiveEntity) e).setHealth(Integer.parseInt(attributes.getNamedItem("health").getTextContent()));
                    ((ActiveEntity) e).setHealthMax(Integer.parseInt(attributes.getNamedItem("healthMax").getTextContent()));
                    ((ActiveEntity) e).setArmor(Integer.parseInt(attributes.getNamedItem("armor").getTextContent()));
                }

                handler.addObject(e);
            }

            NamedNodeMap mapAttributes = root.getAttributeNode("Map").getAttributes();

            Map map = new Map(Integer.parseInt(mapAttributes.getNamedItem("width").getTextContent()), Integer.parseInt(mapAttributes.getNamedItem("height").getTextContent()), levelHandler);
            map.setStartX(Integer.parseInt(mapAttributes.getNamedItem("startX").getTextContent()));
            map.setStartY(Integer.parseInt(mapAttributes.getNamedItem("startY").getTextContent()));
            map.setCurrentX(Integer.parseInt(mapAttributes.getNamedItem("currentX").getTextContent()));
            map.setCurrentY(Integer.parseInt(mapAttributes.getNamedItem("currentY").getTextContent()));

            ArrayList<ArrayList<Room>> roomMap = new ArrayList<>();

            for(int i = 0; i < mapAttributes.getLength(); i++)
            {
                if(mapAttributes.item(i).getNodeName().equals("roomList"))
                {
                    ArrayList<Room> roomList = new ArrayList<Room>();
                    NamedNodeMap roomAttributes = mapAttributes.item(i).getAttributes();

                    for(int j = 0; j < roomAttributes.getLength(); j++)
                    {
                        String[] directions = roomAttributes.getNamedItem("NorthSouthEastWest").getTextContent().split(" ");
                        Room room = new Room(Boolean.parseBoolean(directions[0]), Boolean.parseBoolean(directions[1]), Boolean.parseBoolean(directions[2]), Boolean.parseBoolean(directions[3]),
                                Boolean.parseBoolean(roomAttributes.getNamedItem("unresolvedEvent").getTextContent()), Integer.parseInt(roomAttributes.getNamedItem("roomType").getTextContent()), levelHandler);
                        roomList.add(room);
                    }
                    roomMap.add(roomList);
                }
            }

            ArrayList<Room[]> tempArrayList = new ArrayList<>();
            for(ArrayList<Room> lst : roomMap)
            {
                tempArrayList.add((Room[]) lst.toArray());
            }
            map.setRoomLoc((Room[][]) tempArrayList.toArray());

        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

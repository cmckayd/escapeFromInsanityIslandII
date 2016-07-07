/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.andresIsland.view;

import byui.cit260.andresIsland.control.SceneControl;
import byui.cit260.andresIsland.model.Game;
import byui.cit260.andresIsland.model.GameCharacter;
import byui.cit260.andresIsland.model.Location;
import byui.cit260.andresIsland.model.Map;
import byui.cit260.andresIsland.model.Move;

/**
 *
 * @author andrenell
 */
public class ViewMapView extends View {

    private static GameCharacter me = null;
    private static Map map = null;

    public ViewMapView() {
        super("\n"
                + "\n------------------------------------------------"
                + "\n| How To Move                                  |"
                + "\n------------------------------------------------"
                + "\nW - Move North"
                + "\nA - Move West"
                + "\nS - Move South"
                + "\nD - Move East"
                + "\nE - Explore Scene"
                + "\nQ - Quit"
                + "\n------------------------------------------------"
                + "\n Please enter your selection:");

        drawMap(Map.getMap());
    }

    public void setPlayer(GameCharacter player) {
        this.me = player;
    }

    public void drawMap(Location[][] inData) {
        System.out.println("\n");
        for (int i = 0; i < inData.length; i++) {
            for (int j = 0; j < inData[i].length; j++) {
                if (Game.me != null) {
                    if (Game.me.getCoordX() == j && Game.me.getCoordY() == i) {
                        System.out.print(new Location("♀"));
                    } else {
                        System.out.print(inData[i][j]);
                           
                    }
                } else {
                    System.out.println("No Character");
                }

            }
            System.out.println();            
        }
        System.out.println("\n");
        System.out.println(Game.me);

    }

    @Override
    public boolean doAction(String value) {

        value.toUpperCase(); // convert choice to upper case

        switch (value) {
            case "W": // Move North
                Game.me.setDirection(Move.NORTH);
                Game.me.move();
                Game.me.setHealth(Game.me.getHealth() -10);
                break;
            case "A": // Move West
                Game.me.setDirection(Move.WEST);
                Game.me.move();
                Game.me.setHealth(Game.me.getHealth() -10);
                break;
            case "S": // Move South
                Game.me.setDirection(Move.SOUTH);
                Game.me.move();
                Game.me.setHealth(Game.me.getHealth() -10);
                break;
            case "D": // Move East
                Game.me.setDirection(Move.EAST);
                Game.me.move();
                Game.me.setHealth(Game.me.getHealth() -10);
                break;
            case "E": // Move East
                this.exploreScene();
                break;
            default:
                System.out.println("\n*** Invalid selection *** Try again");
                break;
        }
        if(value != "Q"){
            drawMap(Map.getMap());
            
        }
        return false;
    }

    private void exploreScene() {
        String myScene = SceneControl.getDescription(Game.me.getCoordX(), Game.me.getCoordY());
        
        
        
        SceneView myView = new SceneView(myScene);
        myView.display();
    }

}

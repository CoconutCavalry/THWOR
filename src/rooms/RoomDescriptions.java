/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rooms;

/**
 *
 * @author esose
 */
public class RoomDescriptions {
    
    public static final String defaultSearchDescription = "You see ";
    
    /*******************
     *     LIBRARY     *
     *******************/
    public static final String library = ""
            + "You are in a large room, surrounded by a \n"
            + "labyrinth of tall bookshelves made of a \n"
            + "magnificent dark wood. Golden autumnal \n"
            + "light floods the place from high windows \n"
            + "somewhere outside of your field of vision. \n"
            + "Straight ahead in the distance, you see a \n"
            + "wooden door.";
    public static final String libraryFirstSearch = ""
            + "You wander about the room for a minute, \n"
            + "glancing at the titles of the old, worn books. \n"
            + "Lying about on the shelves and between the novels, \n"
            + "you see ";
    
    /*******************
     *      STUDY      *
     *******************/
    public static final String study = ""
            + "You are in a small, dimly lit room. A \n"
            + "high-backed armchair sits behind a large oak \n"
            + "desk on one side. The other side has a \n"
            + "fireplace, in which some struggling embers \n"
            + "fight to produce a meager source of heat.\n"
            + "On your left, beside the desk, is a dark-\n"
            + "colored door, and behind you is the door \n"
            + "to the library.";
    public static final String studyFirstSearch = ""
            + "You see ";
    public static final String studyDeskFirstSearch = ""
            + "You rummage through the drawers of the \n"
            + "large desk. In the bottom of the last one, \n"
            + "under an army of cobwebs, you find an old \n"
            + "black iron key.";
    public static final String studyDeskOtherSearch = ""
            + "It is a large, dusty oak desk, with many \n"
            + "drawers.";
    public static final String studyFireplaceFirstSearch = ""
            + "You run your hands across the old stone \n"
            + "mantlepiece, and you feel a certain rock give \n"
            + "way beneath your fingers. You see a small scrap \n"
            + "of paper flutter between your fingers towards the \n"
            + "burning coals. You fumble with the torn scrap for \n"
            + "a second and it lands on the stone hearth.";
    public static final String studyFireplaceOtherSearch = ""
            + "It is an old stone fireplace, with some coals \n"
            + "burning on the grate.";
    
    /******************
     *      HALL      *
     ******************/
    public static final String hall = ""
            + "You are in a long hall lit by flickering torches \n"
            + "placed along the walls. There are stairs going \n"
            + "upward to the railing of a second floor above you.\n"
            + "On your left is a door. \n"
            + "On your right is a metallic door with a keypad set \n"
            + "into the wall beside it. \n"
            + "In front of you is a high, ornately carved arched \n"
            + "doorway. \n"
            + "The door to the Study is behind you.";
    public static final String hallFirstSearch = ""
            + "A shadowy figure steps towards you out of the dark \n"
            + "corner. The face is obscured by a dark cowl and bony \n"
            + "hands reach up as if to grab you.";
    public static final String hallOtherSearch = ""
            + "Several torches are placed along the walls.";
    public static final String guardianIsAngryCannotLeave = "" +
            "The shadowy form laughs an eerie, haunting laugh \n"
            + "that sends chills down your spine. \n"
            + "'You have angered the Guardian,' it says.\n"
            + "'You shall not leave.'";
    
    /******************
     *  DINING ROOM   *
     ******************/
    public static final String dining = ""
            + "You are in a dim, candle-lit dining room. A table in \n"
            + "front of you is set for seven, but the seventh set of \n"
            + "dishes and utensils, at the far end of the table, \n"
            + "is old, cracked, and covered in a thick layer of dust.\n"
            + "'Who could live here?' you ask yourself. \n"
            + "The door to the Hall is behind you.";
    public static final String diningFirstSearch = ""
            + "There is nothing here.";
    
    /***********************
     *    COMPUTER ROOM    *
     ***********************/
    public static final String compRoom = ""
            + "You are in a wide, dark room. An electronic hum comes \n"
            + "from a wall on your left, where hundreds of tiny lights \n"
            + "blink blue, red, orange, and green. Behind you is the \n"
            + "door to the Hall.";
    public static final String compSearchNoLight = ""
            + "There is a lightswitch on the wall beside the door.";
    public static final String compFirstSearchWithLight = ""
            + "The left wall of the wide room is coverd by computer \n"
            + "monitors from floor to ceiling. The monitor screens are \n"
            + "sectioned into grids which are all blank, except for a few \n"
            + "flickering lights in the corners of the small frames. \n"
            + "A sticky-note on the center-most monitor has 'RAG??' written \n"
            + "on it in jagged, angular handwriting with permanent marker. \n"
            + "Four benches, oddly similar to hospital beds, are spaced \n"
            + "evenly across the rest of the room."
            + "\n" + compSearchNoLight;
    public static final String compOtherSearchWithLight = ""
            + "Computer monitors with flickering lights cover the left \n"
            + "wall. A sticky-note in the middle reads 'RAG??'. \n"
            + "Four beds are spread across the room. "
            + "\n" + compSearchNoLight;
}

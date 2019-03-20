package rooms;

public class Pantry extends RoomFull {

    private static final int id = RoomId.PANTRY.getId();
    private static final String name = "Pantry";
    private final int[] neighbors = {RoomId.KITCHEN.getId()};
    private final String description = RoomDescriptions.pantry;

    /**
     * Constructor for the Pantry
     */
    public Pantry() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return this.description;
    }

    public int go(String direction) {
        switch (direction) {
            case "back":
            case "backward":
            case "backwards":
                return this.neighbors[0];
            default:
                return -1;
        }
    }
}

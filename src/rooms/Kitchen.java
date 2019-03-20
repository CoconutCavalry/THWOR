package rooms;

public class Kitchen extends RoomFull {

    private static final int id = RoomId.KITCHEN.getId();
    private static final String name = "Kitchen";
    private final int[] neighbors = {RoomId.DININGROOM.getId(), RoomId.PANTRY.getId()};
    private final String description = RoomDescriptions.kitchen;

    /**
     * Constructor for the Kitchen
     */
    public Kitchen() {}

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
            case "right":
                return this.neighbors[1];
            default:
                return -1;
        }
    }
}

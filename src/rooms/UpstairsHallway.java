package rooms;

public class UpstairsHallway extends RoomFull {

    private static final int id = RoomId.UPSTAIRSHALLWAY.getId();
    private static final String name = "Upstairs Hallway";
    private static final String description = RoomDescriptions.upstairsHallway;
    private final int[] neighbors = {RoomId.HALL.getId()};

    /**
     * Constructor for the UpstairsHallway
     */
    public UpstairsHallway() {}

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int go(String direction) {
        switch (direction) {
            case "back":
            case "backward":
            case "backwards":
            case "down":
                return this.neighbors[0];
            default:
                return -1;
        }
    }
}

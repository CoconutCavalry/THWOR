package rooms;

// ID must match its order within the House list
public enum RoomId {
    LIBRARY(0),
    STUDY(1),
    HALL(2),
    DININGROOM(3),
    COMPUTERROOM(4);

    RoomId(int id) {
        this.id = id;
    }

    private final int id;
    public int getId() {
        return this.id;
    }
}

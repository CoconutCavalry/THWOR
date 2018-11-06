package items;

import rooms.RoomId;

public class HallKey implements iItem, iKey {

    public HallKey() {}

    @Override
    public String getName() {
        return "key";
    }

    @Override
    public String getDescription() {
        return "A small, black, iron key.";
    }

    @Override
    public double getSize() {
        return 0.5;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public String toStringShort() {
        return "a black key";
    }

    @Override
    public int unlocks() {
        return RoomId.HALL.getId();
    }
}

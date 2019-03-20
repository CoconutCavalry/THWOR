package items;

import rooms.RoomId;

public class HallKey implements iKey {

    public HallKey() {}

      
    public String getName() {
        return "key";
    }

      
    public String getDescription() {
        return "A small, black, iron key.";
    }

      
    public double getSize() {
        return 0.5;
    }

      
//    public String getStats() {
//        return null;
//    }

      
    public String toStringShort() {
        return "a black key";
    }

      
    public int unlocks() {
        return RoomId.HALL.getId();
    }
}

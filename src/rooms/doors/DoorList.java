package rooms.doors;

import java.util.ArrayList;

public class DoorList {

    private ArrayList<Door> doors;

    public DoorList() {}

    public boolean addDoor(Door door) {
        if (this.doors.contains(door)) {
            return false;
        } else {
            this.doors.add(door);
            return true;
        }
    }

    public Door getDoorByDirection(String direction) {
        for (Door door : this.doors) {
            for (int i = 0; i < door.direction.length; i++) {
                if (door.direction[i].equals(direction)) {
                    return door;
                }
            }
        }

        // if no matches, return empty door object
        return new Door();
    }

    private String doorsToString() {
        String doorsString = "";
        for (Door d : doors) {
            doorsString += "\n" + d.getDescription();
        }
        return doorsString;
    }

}

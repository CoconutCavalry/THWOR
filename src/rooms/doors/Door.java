package rooms.doors;

import items.iKey;

public class Door {

    private int roomId;
    private String description;
    private iKey key;
    private boolean isLocked;
    public String[] direction;

    /**
     * Create a new lockable door instance
     * @param roomId of the room on the other side of the door
     * @param desc of the door itself for showing in the room in which this Door exists
     * @param keyToUnlock the specific iKey that can unlock this particular door
     * @param locked true if door is locked
     */
    public Door(int roomId, String desc, String[] direction, iKey keyToUnlock, boolean locked) {
        this.roomId = roomId;
        this.description = desc;
        this.direction = direction;
        this.key = keyToUnlock;
        this.isLocked = locked;
    }

    /**
     * Create a new Door instance that is not lockable (free travel)
     * @param roomId of the room on the other side of the door
     * @param desc of the door itself for showing in the room in which this Door exists
     */
    public Door(int roomId, String desc, String[] direction) {
        this.roomId = roomId;
        this.description = desc;
        this.direction = direction;
        this.key = null;
        this.isLocked = false;
    }

    /**
     * Create an instance of the door class to be used when there is no door in that ordinal direction
     */
    public Door() {
        this.roomId = -1;
        this.description = "There is no door in that direction.";
        this.direction = new String[]{"none"};
        this.key = null;
        this.isLocked = false;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getDescription() {
        return this.description;
    }

    public iKey getKey() {
        return this.key;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public void lock() {
        this.isLocked = true;
    }

    public void unlock() {
        this.isLocked = false;
    }

    public String toString() {
        String retVal = this.description;
        retVal += this.isLocked ? "\nThis door is locked. You need " + this.key.toStringShort() + " to unlock it."
                : "\nThis door is unlocked.";

        return retVal;
    }
}

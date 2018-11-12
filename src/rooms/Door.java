package rooms;

import items.iKey;

public class Door {

    private int roomId;
    private String description;
    private iKey key;
    private boolean isLocked;

    /**
     * Create a new lockable door instance
     * @param roomId of the room on the other side of the door
     * @param desc of the door itself for showing in the room in which this Door exists
     * @param keyToUnlock the specific iKey that can unlock this particular door
     * @param locked true if door is locked
     */
    public Door(int roomId, String desc, iKey keyToUnlock, boolean locked) {
        roomId = roomId;
        description = desc;
        key = keyToUnlock;
        isLocked = locked;
    }

    /**
     * Create a new Door instance that is not lockable (free travel)
     * @param roomId of the room on the other side of the door
     * @param desc of the door itself for showing in the room in which this Door exists
     */
    public Door(int roomId, String desc) {
        roomId = roomId;
        description = desc;
        key = null;
        isLocked = false;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getDescription() {
        return description;
    }

    public iKey getKey() {
        return key;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}

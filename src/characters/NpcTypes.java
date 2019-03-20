package characters;

import items.Excalibur;
import items.iItem;

public enum NpcTypes {

    ORC("Orc",5, new Excalibur());

    private String name;
    private int strength;
    private iItem drop;

    NpcTypes(String name, int strength, iItem drop) {
        this.name = name;
        this.strength = strength;
        this.drop = drop;
    }

}

package items;

public class Flashlight implements iItem {

    public Flashlight() {}

      
    public String getName() {
        return "flashlight";
    }

      
    public String getDescription() {
        return "A large flashlight with a cracked and faded \n" +
                "blue plastic casing.";
    }

      
    public double getSize() {
        return 2.5;
    }

      
    public String getStats() {
        return null;
    }

      
    public String toStringShort() {
        return "a flashlight";
    }

}

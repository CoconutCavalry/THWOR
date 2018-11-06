package items;

public class Flashlight implements iItem {

    public Flashlight() {}

    @Override
    public String getName() {
        return "flashlight";
    }

    @Override
    public String getDescription() {
        return "A large flashlight with a cracked and faded \n" +
                "blue plastic casing.";
    }

    @Override
    public double getSize() {
        return 2.5;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public String toStringShort() {
        return "a flashlight";
    }

}

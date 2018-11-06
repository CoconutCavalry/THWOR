package items;

public class Message implements iItem {

    public Message() {}

    @Override
    public String getName() {
        return "message";
    }

    @Override
    public String getDescription() {
        return "The scrap of paper has a single word scrawled \n" +
                "into it: CoconutCavalry";
    }

    @Override
    public double getSize() {
        return 0.1;
    }

    @Override
    public String getStats() {
        return null;
    }

    @Override
    public String toStringShort() {
        return "a small, torn message";
    }
}

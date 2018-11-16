package items;

public class Message implements iItem {

    public Message() {}

      
    public String getName() {
        return "message";
    }

      
    public String getDescription() {
        return "The scrap of paper has a single word scrawled into it: CoconutCavalry";
    }

      
    public double getSize() {
        return 0.1;
    }

      
    public String getStats() {
        return null;
    }

      
    public String toStringShort() {
        return "a small, torn message";
    }
}

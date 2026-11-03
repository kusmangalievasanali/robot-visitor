package robot_visitor;

public class Sensor implements RobotPart {
    private final String type;
    private final int accuracyPercent;
    private final int price;

    public Sensor(String type, int accuracyPercent, int price) {
        if (accuracyPercent < 0 || accuracyPercent > 100) {
            throw new IllegalArgumentException("Accuracy must be 0-100, got: " + accuracyPercent);
        }
        this.type = type;
        this.accuracyPercent = accuracyPercent;
        this.price = price;
    }

    @Override
    public void accept(RobotPartVisitor visitor) {
        visitor.visitSensor(this);
    }

    public String getType() { return type; }

    public int getAccuracyPercent() { return accuracyPercent; }

    public int getPrice() { return price; }
}
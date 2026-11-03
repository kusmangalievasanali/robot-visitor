package robot_visitor;

public class Motor implements RobotPart {
    private final int powerWatts;
    private final int hoursWorked;
    private final int price;

    public Motor(int powerWatts, int hoursWorked, int price) {
        this.powerWatts = powerWatts;
        this.hoursWorked = hoursWorked;
        this.price = price;
    }

    @Override
    public void accept(RobotPartVisitor visitor) {
        visitor.visitMotor(this);
    }

    public int getPowerWatts() { return powerWatts; }

    public int getHoursWorked() { return hoursWorked; }

    public int getPrice() { return price; }
}
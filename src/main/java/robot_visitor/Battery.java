package robot_visitor;

public class Battery implements RobotPart {
    private final int chargePercent;
    private final int capacityWh;
    private final int price;

    public Battery(int chargePercent, int capacityWh, int price) {
        if (chargePercent < 0 || chargePercent > 100) {
            throw new IllegalArgumentException("Charge must be 0-100, got: " + chargePercent);
        }
        this.chargePercent = chargePercent;
        this.capacityWh = capacityWh;
        this.price = price;
    }

    @Override
    public void accept(RobotPartVisitor visitor) {
        visitor.visitBattery(this);
    }

    public int getChargePercent() { return chargePercent; }

    public int getCapacityWh() { return capacityWh; }

    public int getPrice() { return price; }
}
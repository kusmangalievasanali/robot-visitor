package robot_visitor;

public class CostVisitor implements RobotPartVisitor {
    private int totalCost;

    @Override
    public void visitBattery(Battery battery) {
        totalCost += battery.getPrice();
    }

    @Override
    public void visitMotor(Motor motor) {
        totalCost += motor.getPrice();
    }

    @Override
    public void visitSensor(Sensor sensor) {
        totalCost += sensor.getPrice();
    }

    @Override
    public void visitCamera(Camera camera) {
        totalCost += camera.getPrice();
    }

    public int getTotalCost() { return totalCost; }
}
package robot_visitor;

public interface RobotPartVisitor {
    void visitBattery(Battery battery);
    void visitMotor(Motor motor);
    void visitSensor(Sensor sensor);
    void visitCamera(Camera camera);
}
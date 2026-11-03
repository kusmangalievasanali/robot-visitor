package robot_visitor;

public interface RobotPart {
    void accept(RobotPartVisitor visitor);
}
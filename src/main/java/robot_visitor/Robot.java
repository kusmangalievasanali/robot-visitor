package robot_visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Robot {
    private final String name;
    private final List<RobotPart> parts = new ArrayList<>();

    public Robot(String name) {
        this.name = Objects.requireNonNull(name, "Robot name must not be null");
    }

    public void addPart(RobotPart part) {
        parts.add(Objects.requireNonNull(part, "Part must not be null"));
    }

    public void accept(RobotPartVisitor visitor) {
        for (RobotPart part : parts) {
            part.accept(visitor);
        }
    }

    public String getName() { return name; }
}
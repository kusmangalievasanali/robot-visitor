package robot_visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagnosticsVisitor implements RobotPartVisitor {
    private static final int MIN_BATTERY_CHARGE = 20;
    private static final int MAX_MOTOR_HOURS = 1000;
    private static final int MIN_SENSOR_ACCURACY = 90;
    private static final int MIN_CAMERA_MEGAPIXELS = 8;

    private final List<String> issues = new ArrayList<>();

    @Override
    public void visitBattery(Battery battery) {
        if (battery.getChargePercent() < MIN_BATTERY_CHARGE) {
            issues.add("Battery charge is low: " + battery.getChargePercent() + "%");
        }
    }

    @Override
    public void visitMotor(Motor motor) {
        if (motor.getHoursWorked() > MAX_MOTOR_HOURS) {
            issues.add("Motor is worn out: " + motor.getHoursWorked() + " h");
        }
    }

    @Override
    public void visitSensor(Sensor sensor) {
        if (sensor.getAccuracyPercent() < MIN_SENSOR_ACCURACY) {
            issues.add("Sensor '" + sensor.getType() + "' is inaccurate: "
                    + sensor.getAccuracyPercent() + "%");
        }
    }

    @Override
    public void visitCamera(Camera camera) {
        if (camera.getMegapixels() < MIN_CAMERA_MEGAPIXELS) {
            issues.add("Camera resolution is too low: " + camera.getMegapixels() + " MP");
        }
    }

    public List<String> getIssues() { return Collections.unmodifiableList(issues); }

    public boolean isHealthy() { return issues.isEmpty(); }
}
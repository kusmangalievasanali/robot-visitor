package robot_visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaintenanceVisitor implements RobotPartVisitor {
    private static final int BATTERY_RECHARGE_BELOW = 50;
    private static final int MOTOR_REPLACE_AT_HOURS = 1000;
    private static final int MOTOR_LUBRICATE_AT_HOURS = 500;
    private static final int SENSOR_RECALIBRATE_BELOW = 95;

    private final List<String> tasks = new ArrayList<>();
    private int totalHours;

    @Override
    public void visitBattery(Battery battery) {
        if (battery.getChargePercent() < BATTERY_RECHARGE_BELOW) {
            addTask("Recharge battery", 1);
        }
    }

    @Override
    public void visitMotor(Motor motor) {
        if (motor.getHoursWorked() >= MOTOR_REPLACE_AT_HOURS) {
            addTask("Replace motor", 4);
        } else if (motor.getHoursWorked() >= MOTOR_LUBRICATE_AT_HOURS) {
            addTask("Lubricate motor", 2);
        }
    }

    @Override
    public void visitSensor(Sensor sensor) {
        if (sensor.getAccuracyPercent() < SENSOR_RECALIBRATE_BELOW) {
            addTask("Recalibrate '" + sensor.getType() + "' sensor", 1);
        }
    }

    @Override
    public void visitCamera(Camera camera) {
        addTask("Clean camera lens", 1);
    }

    public List<String> getTasks() { return Collections.unmodifiableList(tasks); }

    public int getTotalHours() { return totalHours; }

    private void addTask(String description, int hours) {
        tasks.add(description + " (" + hours + " h)");
        totalHours += hours;
    }
}
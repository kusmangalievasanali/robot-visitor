package robot_visitor;

public class Main {

    public static void main(String[] args) {
        inspect(createWornRobot());
        inspect(createHealthyRobot());
    }

    private static Robot createWornRobot() {
        Robot robot = new Robot("R2-Alpha");
        robot.addPart(new Battery(15, 500, 120));
        robot.addPart(new Motor(750, 1200, 300));
        robot.addPart(new Sensor("lidar", 96, 250));
        robot.addPart(new Sensor("ultrasonic", 85, 40));
        robot.addPart(new Camera(12, 180));
        return robot;
    }

    private static Robot createHealthyRobot() {
        Robot robot = new Robot("Scout-2");
        robot.addPart(new Battery(90, 400, 100));
        robot.addPart(new Motor(500, 100, 250));
        robot.addPart(new Sensor("lidar", 98, 250));
        robot.addPart(new Camera(8, 150));
        return robot;
    }

    private static void inspect(Robot robot) {
        System.out.println("\n=== Robot: " + robot.getName() + " ===");
        printDiagnostics(robot);
        printCost(robot);
        printMaintenance(robot);
    }

    private static void printDiagnostics(Robot robot) {
        DiagnosticsVisitor diagnostics = new DiagnosticsVisitor();
        robot.accept(diagnostics);
        if (diagnostics.isHealthy()) {
            System.out.println("[Diagnostics] all parts OK");
        } else {
            System.out.println("[Diagnostics] " + diagnostics.getIssues().size() + " issue(s)");
            diagnostics.getIssues().forEach(issue -> System.out.println("  - " + issue));
        }
    }

    private static void printCost(Robot robot) {
        CostVisitor cost = new CostVisitor();
        robot.accept(cost);
        System.out.println("[Cost] total price: " + cost.getTotalCost() + " USD");
    }

    private static void printMaintenance(Robot robot) {
        MaintenanceVisitor maintenance = new MaintenanceVisitor();
        robot.accept(maintenance);
        System.out.println("[Maintenance] " + maintenance.getTasks().size() + " task(s), "
                + maintenance.getTotalHours() + " h");
        maintenance.getTasks().forEach(task -> System.out.println("  - " + task));
    }
}
package robot_visitor;

public class Camera implements RobotPart {
    private final int megapixels;
    private final int price;

    public Camera(int megapixels, int price) {
        this.megapixels = megapixels;
        this.price = price;
    }

    @Override
    public void accept(RobotPartVisitor visitor) {
        visitor.visitCamera(this);
    }

    public int getMegapixels() { return megapixels; }

    public int getPrice() { return price; }
}
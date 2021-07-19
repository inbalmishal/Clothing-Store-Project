package model.entities;

public class Shoe extends Item{
    private String drawstringColor;

    public Shoe()
    {
        super();
    }

    public String getDrawstringColor() {
        return drawstringColor;
    }
    public void setDrawstringColor(String drawstringColor) {
        this.drawstringColor = drawstringColor;
    }

    public Shoe(String color, String brand, String gender, String type, int price, int size, int currentStock, int baseStock, int itemId, String drawstringColor) {
        super(color, brand, gender, type, price, size, currentStock, baseStock, itemId);
        this.drawstringColor = drawstringColor;
    }

}

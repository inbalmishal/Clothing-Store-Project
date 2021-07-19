package model.entities;

public abstract class Item {
    private String color, brand, gender, type;
    private int price;
    private int size, currentStock, baseStock, itemId;

    public Item() {
        this.color = "valueless";
        this.brand = "valueless";
        this.gender = "valueless";
        this.type = "valueless";
        this.price = -1;
        this.size = -1;
        this.currentStock = -1;
        this.baseStock = -1;
        this.itemId = -1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getBaseStock() {
        return baseStock;
    }

    public void setBaseStock(int baseStock) {
        this.baseStock = baseStock;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Item(String color, String brand, String gender, String type, int price, int size, int currentStock, int baseStock, int itemId) {
        this.color = color;
        this.brand = brand;
        this.gender = gender;
        this.type = type;
        this.price = price;
        this.size = size;
        this.currentStock = currentStock;
        this.baseStock = baseStock;
        this.itemId = itemId;
    }
}

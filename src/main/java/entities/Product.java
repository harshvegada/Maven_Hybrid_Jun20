package entities;

public class Product {

    private String productName;
    private int size;
    private int deliveryCharges;
    private int productMrp;
    private String productMrpDetails;

    public String getProductMrpDetails() {
        return productMrpDetails;
    }

    public void setProductMrpDetails(String productMrpDetails) {
        this.productMrpDetails = productMrpDetails;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(int deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public int getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(int productMrp) {
        this.productMrp = productMrp;
    }


}

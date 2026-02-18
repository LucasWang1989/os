package nz.ac.sit.os.api.dto;

import java.util.List;

public class CheckoutRequest {
    private String tableNo;
    private List<CheckoutItem> items;

    public String getTableNo() { return tableNo; }
    public void setTableNo(String tableNo) { this.tableNo = tableNo; }

    public List<CheckoutItem> getItems() { return items; }
    public void setItems(List<CheckoutItem> items) { this.items = items; }

    public static class CheckoutItem {
        private String id;
        private Integer amount;

        private String name;
        private Long price;
        private String imagePath;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public Integer getAmount() { return amount; }
        public void setAmount(Integer amount) { this.amount = amount; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Long getPrice() { return price; }
        public void setPrice(Long price) { this.price = price; }

        public String getImagePath() { return imagePath; }
        public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    }
}


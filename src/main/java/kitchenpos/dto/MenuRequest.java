package kitchenpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class MenuRequest {
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductRequest> menuProducts;

    protected MenuRequest() {
    }

    public MenuRequest(String name, long price, Long menuGroupId, List<MenuProductRequest> menuProducts) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductRequest> getMenuProducts() {
        return menuProducts;
    }
}

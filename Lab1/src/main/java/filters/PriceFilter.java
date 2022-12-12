package filters;

import insurance.Insurance;

public class PriceFilter implements Filter {

    private double minPrice;
    private double maxPrice;
    public PriceFilter(double minPrice, double maxPrice) {
        this.setMinPrice(minPrice);
        this.setMaxPrice(maxPrice);
    }
    @Override
    public boolean check(Insurance insurance) {
	    return (insurance.getPrice() >= minPrice && insurance.getPrice() <= maxPrice);
    }
    public void setMinPrice(double minPrice) {
	this.minPrice = minPrice;
    }
    public void setMaxPrice(double maxPrice) {
	this.maxPrice = maxPrice;
    }

}
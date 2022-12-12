package filters;

import insurance.Insurance;

public class PeriodFilter implements Filter {
    private int minPeriod;
    private int maxPeriod;
    public PeriodFilter(int minPeriod, int maxPeriod) {
        this.setMinPeriod(minPeriod);
        this.setMaxPeriod(maxPeriod);
    }
    @Override
    public boolean check(Insurance insurance) {
	    return (insurance.getPeriod() >= minPeriod && insurance.getPeriod() <= maxPeriod);
    }
    public void setMinPeriod(int minPeriod) {
	this.minPeriod = minPeriod;
    }
    public void setMaxPeriod(int maxPeriod) {
	this.maxPeriod = maxPeriod;
    }

}
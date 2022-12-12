package filters;

import insurance.Insurance;

public class RiskFilter implements Filter {
    private double minRisk;
    private double maxRisk;

    public RiskFilter(double minRisk, double maxRisk) {
        this.setMinRisk(minRisk);
        this.setMaxRisk(maxRisk);
    }
    @Override
    public boolean check(Insurance insurance) {
	    return (insurance.getRisk() >= minRisk && insurance.getRisk() <= maxRisk);
    }
    public void setMinRisk(double minRisk) {
	this.minRisk = minRisk;
    }
    public void setMaxRisk(double maxRisk) {
	this.maxRisk = maxRisk;
    }

}
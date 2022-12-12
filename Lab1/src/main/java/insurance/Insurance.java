package insurance;

public abstract class Insurance implements Comparable<Insurance> {
    protected Type type;
    protected int period;
    protected double price;
    protected double risk;

    public Insurance(Type type, int period) {
        this.type = type;
        this.period = period;
    }
    public abstract String getInfo();
    @Override
    public int compareTo(Insurance i) {
	    return this.period - i.getPeriod();
    }
    public int getPeriod() {
	    return period;
    }
    public double getPrice() {
	    return price;
    }
    public double getRisk() {
	    return risk;
    }
    public Type getType() {
	    return type;
    }
}

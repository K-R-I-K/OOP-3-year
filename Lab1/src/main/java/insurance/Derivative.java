package insurance;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import filters.Filter;

public class Derivative {
    private List<Insurance> derivative = new ArrayList<>();
    private int derivativeId;
    private int insuranceAmount;
    private String fio;
	public Derivative(int derivativeId, String fio, Insurance... insurances) {
		this.derivativeId = derivativeId;
		this.fio = fio;
		this.insuranceAmount = insurances.length;
		Collections.addAll(derivative, insurances);
	}
    public void sort(Comparator<Insurance> comparator) {
		derivative.sort(comparator);
    }
    public List<Insurance> find(Filter filter){
		return derivative.stream().filter(filter::check).toList();
    }
    public String getInfo() {
		return "Derivative id: " + derivativeId + "\nAmount of insurances: " + insuranceAmount
			+ "\nOwner name: " + fio + "\n\n";
	}
	public String getInfoInsurance() {
		StringBuilder sb = new StringBuilder();
		derivative.forEach(i -> sb.append(i.getInfo()));
		return new String(sb);
    }
    public String countTotalPrice() {
		double totalPrice = derivative.stream().mapToDouble(Insurance::getPrice).sum();
		return "Total derivative price = " + totalPrice;
    }

}
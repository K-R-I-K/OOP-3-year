import java.util.Comparator;
import java.util.List;

import filters.*;
import insurance.*;

public class lab1 {
    public static void main(String[] args) {
		Insurance BMW = new CarInsurance(Type.CAR, 12, 1995, "Passanger", "BMW");
		Insurance Ferrari = new CarInsurance(Type.CAR, 10, 1995, "Passanger", "Ferrari");
		Insurance Audi = new CarInsurance(Type.CAR, 24, 2021, "Truck", "Audi");
		Insurance John = new MedicalInsurance(Type.MEDICAL, 16, 10, "John", "Smith");

		Derivative one = new Derivative(126532, "Ali Caddel", BMW, Audi, John, Ferrari);
		System.out.println("Total info about derivative\n");
		System.out.println(one.getInfo());


		System.out.println("Unsorted derivative\n");
		System.out.println(one.getInfoInsurance());

		one.sort(Comparator.comparing(Insurance::getRisk).thenComparing(Insurance::getPeriod));
		System.out.println("Sorted by risk derivative\n");
		System.out.println(one.getInfoInsurance());

		System.out.println("Find by period\n");
		List<Insurance> list1 = one.find(new PeriodFilter(15, 25));
		if(list1.isEmpty()) {
			System.out.println("Cant't find objects with this parameter\n");
		} else {
			list1.forEach(i -> System.out.println(i.getInfo()));
		}

		System.out.println("Find by price\n");
		List<Insurance> list2 = one.find(new PriceFilter(100, 1000));
		if(list2.isEmpty()) {
			System.out.println("Cant't find objects with this parameter\n");
		} else {
			list2.forEach(i -> System.out.println(i.getInfo()));
		}

		System.out.println("Find by type\n");
		List<Insurance> list3 = one.find(new TypeFilter(Type.TYPE));
		if(list3.isEmpty()) {
			System.out.println("Cant't find objects with this parameter\n");
		} else {
			list3.forEach(i -> System.out.println(i.getInfo()));
		}
		System.out.println(one.countTotalPrice());
    }

}

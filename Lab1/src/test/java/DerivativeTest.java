import filters.PeriodFilter;
import filters.PriceFilter;
import filters.TypeFilter;
import insurance.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DerivativeTest {
    Insurance BMW = new CarInsurance(Type.CAR, 12, 1995, "Passanger", "BMW");
    Insurance Ferrari = new CarInsurance(Type.CAR, 10, 1995, "Passanger", "Ferrari");
    Insurance Audi = new CarInsurance(Type.CAR, 24, 2021, "Truck", "Audi");
    Insurance John = new MedicalInsurance(Type.MEDICAL, 16, 10, "John", "Smith");
    Derivative one = new Derivative(126532, "Ali Caddel", BMW, Audi, John, Ferrari);

    @Test
    public void totalInfo() {
        assertEquals("Derivative id: 126532\n" +
                "Amount of insurances: 4\n" +
                "Owner name: Ali Caddel\n" +
                "\n", one.getInfo());
    }

    @Test
    public void unsortedDerivative() {
        assertEquals("Type: CAR\n" +
                "Year of Release: 1995\n" +
                "Car Type: Passanger\n" +
                "Brand: BMW\n" +
                "Risk: 0.135\n" +
                "Price: 810.0\n" +
                "Type: CAR\n" +
                "Year of Release: 2021\n" +
                "Car Type: Truck\n" +
                "Brand: Audi\n" +
                "Risk: 0.005\n" +
                "Price: 60.0\n" +
                "Type: MEDICAL\n" +
                "Name: John\n" +
                "Surname: Smith\n" +
                "Age: 10\n" +
                "Risk: 0.1\n" +
                "Price: 2400.0\n" +
                "Type: CAR\n" +
                "Year of Release: 1995\n" +
                "Car Type: Passanger\n" +
                "Brand: Ferrari\n" +
                "Risk: 0.135\n" +
                "Price: 675.0\n", one.getInfoInsurance());
    }

    @Test
    public void sortedDerivativeByRisk() {
        one.sort(Comparator.comparing(Insurance::getRisk).thenComparing(Insurance::getPeriod));
        assertEquals("Type: CAR\n" +
                "Year of Release: 2021\n" +
                "Car Type: Truck\n" +
                "Brand: Audi\n" +
                "Risk: 0.005\n" +
                "Price: 60.0\n" +
                "Type: MEDICAL\n" +
                "Name: John\n" +
                "Surname: Smith\n" +
                "Age: 10\n" +
                "Risk: 0.1\n" +
                "Price: 2400.0\n" +
                "Type: CAR\n" +
                "Year of Release: 1995\n" +
                "Car Type: Passanger\n" +
                "Brand: Ferrari\n" +
                "Risk: 0.135\n" +
                "Price: 675.0\n" +
                "Type: CAR\n" +
                "Year of Release: 1995\n" +
                "Car Type: Passanger\n" +
                "Brand: BMW\n" +
                "Risk: 0.135\n" +
                "Price: 810.0\n", one.getInfoInsurance());
    }

    @Test
    public void findByPeriod() {
        List<Insurance> list1 = one.find(new PeriodFilter(15, 25));
        assertEquals(List.of(Audi, John), list1);
    }

    @Test
    public void findByPrice() {
        List<Insurance> list2 = one.find(new PriceFilter(100, 1000));
        assertEquals(List.of(BMW, Ferrari), list2);
    }

    @Test
    public void findByType() {
        List<Insurance> list3 = one.find(new TypeFilter(Type.TYPE));
        assertEquals(new ArrayList<>(), list3);
    }

    @Test
    public void totalPrice() {
        assertEquals("Total derivative price = 3945.0", one.countTotalPrice());
    }
}

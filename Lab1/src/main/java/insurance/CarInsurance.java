package insurance;

import java.time.LocalDate;
import java.util.Objects;

public class CarInsurance extends Insurance {
    private int yearOfRelease;
    private String carType;
    private String brand;
    public CarInsurance(Type type, int period, int yearOfRelease, String carType, String brand) {
        super(type, period);
        this.yearOfRelease = yearOfRelease;
        this.carType = carType;
        this.brand = brand;
        this.risk = 0.005 * (LocalDate.now().getYear() - yearOfRelease);
        this.price = 500 * risk * period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarInsurance that = (CarInsurance) o;
        return yearOfRelease == that.yearOfRelease && Objects.equals(carType, that.carType) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearOfRelease, carType, brand);
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nYear of Release: " + yearOfRelease + "\nCar Type: " + carType
            + "\nBrand: " + brand + "\nRisk: " + risk + "\nPrice: " + price + "\n";
    }
}

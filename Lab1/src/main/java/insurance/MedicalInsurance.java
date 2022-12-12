package insurance;

import java.util.Objects;

public class MedicalInsurance extends Insurance {
    private int age;
    private String name;
    private String surname;
    public MedicalInsurance(Type type, int period, int age, String name, String surname) {
        super(type, period);
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.risk = 0.01 * age;
        this.price = 1500 * period * risk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalInsurance that = (MedicalInsurance) o;
        return age == that.age && Objects.equals(name, that.name) && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, surname);
    }

    @Override
    public String getInfo() {
        return "Type: " + type + "\nName: " + name + "\nSurname: " + surname + "\nAge: " + age + "\nRisk: "
            + risk + "\nPrice: " + price + "\n";
    }
}

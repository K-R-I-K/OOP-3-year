import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String country;
    private int age;
    private String name;
    private transient int height;

    public Person(String country, int age, String name, int height) {
        this.country = country;
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "country='" + country + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
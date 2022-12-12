import java.util.Arrays;
import java.util.Scanner;

public class Reflection {
    private void getClassInfo() {
        Class<?> clazz = getaClass();
        name(clazz);
        classType(clazz);
        classFilling(clazz);
    }

    private static Class<?> getaClass() {
        Class<?> clazz = null;
        boolean classNotFound = true;

        while(classNotFound) {
            System.out.println("Enter class name: ");
            String className = new Scanner(System.in).next();
            classNotFound = false;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                System.out.println("No class with such name found");
                classNotFound = true;
            }
        }
        return clazz;
    }

    private static void classFilling(Class<?> clazz) {
        System.out.println("Class declared constructors: " + Arrays.toString(clazz.getDeclaredConstructors()));
        System.out.println("Class constructors: " + Arrays.toString(clazz.getConstructors()));
        System.out.println("Class declared fields: " + Arrays.toString(clazz.getDeclaredFields()));
        System.out.println("Class fields: " + Arrays.toString(clazz.getFields()));
        System.out.println("Class declared methods: " + Arrays.toString(clazz.getDeclaredMethods()));
        System.out.println("Class methods: " + Arrays.toString(clazz.getMethods()));
        System.out.println("Class declared classes: " + Arrays.toString(clazz.getDeclaredClasses()));
        System.out.println("Class declared annotations: " + Arrays.toString(clazz.getDeclaredAnnotations()));
        System.out.println("Class's superclass: " + clazz.getSuperclass());
        System.out.println("Class's package: " + clazz.getPackage());
        System.out.println("Class interfaces: " + Arrays.toString(clazz.getInterfaces()));
    }

    private static void classType(Class<?> clazz) {
        System.out.println("Is class primitive: " + clazz.isPrimitive());
        System.out.println("Is class interface: " + clazz.isInterface());
        System.out.println("Is class enum: " + clazz.isEnum());
        if(clazz.isEnum()) {
            System.out.println("Enums constants: " + Arrays.toString(clazz.getEnumConstants()));
        }
        System.out.println("Is class annotation: " + clazz.isAnnotation());
        System.out.println("Is class member class: " + clazz.isMemberClass());
        System.out.println("Is class local class: " + clazz.isLocalClass());
        System.out.println("Is class anonymous: " + clazz.isAnonymousClass());
        if(clazz.isLocalClass() || clazz.isAnonymousClass() || clazz.isMemberClass()) {
            System.out.println("Enclosing class: " + clazz.getEnclosingClass());
            System.out.println("Enclosing constructor: " + clazz.getEnclosingConstructor());
            System.out.println("Enclosing method: " + clazz.getEnclosingMethod());
        }
        System.out.println("Is class array : " + clazz.isArray());
        System.out.println("Is class synthetic: " + clazz.isSynthetic());
    }

    private static void name(Class<?> clazz) {
        System.out.println("Class name: " + clazz.getName());
        System.out.println("Class simple name: " + clazz.getSimpleName());
    }

    public static void main(String[] args) {
        new Reflection().getClassInfo();
    }
}

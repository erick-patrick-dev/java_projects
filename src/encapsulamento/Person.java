import java.time.OffsetDateTime;

public class Person {
    private final String name;
    private int age;
    private int lastYearAgeInc = OffsetDateTime.now().getYear();

    public Person(String name){
        this.name = name;
        this.age = 1;
    }

    public String getName() {
        return name;
    }

    public void incAge(){
        if (this.lastYearAgeInc >= OffsetDateTime.now().getYear()) return;
        this.age += 1;

        this.lastYearAgeInc = OffsetDateTime.now().getYear();
    }

    public int getAge(){
        return age;
    }

    public static String test;

    public static void setTest(String testParam){
        test = testParam;
    }

    public static String getTest(){
        return test;
    }
}

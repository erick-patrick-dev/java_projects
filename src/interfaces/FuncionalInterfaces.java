import java.util.List;
import java.util.function.Function;

public class FuncionalInterfaces {
    public static void main(String[] args) {
        List<User> users = List.of(new User("Maria", 21),
                new User("Joao", 44), new User("Eduardo", 15),
                new User("Ana", 54));


        //users.forEach(System.out::println);
        printStringValue(Record::toString, users);
    }

    private static void printStringValue(Function<User, String> callback, List<User> users){
        users.forEach(u -> System.out.println(callback.apply(u)));
    }
}

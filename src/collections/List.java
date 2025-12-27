import java.util.ArrayList;

public class List {
    public void run(){
        java.util.List<User> users = new ArrayList<>();
        var user = new User(1, "Carlo");

        users.add(user);
        users.add(new User(2, "Valdo"));
        users.add(new User(3, "Maria"));
        System.out.println(users.contains(new User(1, "Carlo")));
        System.out.println(users);

        System.out.println(users.remove(new User(8, "Valdo")));
        System.out.println(users.remove(0));
    }
}

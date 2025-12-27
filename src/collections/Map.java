import domain.User;

import java.util.HashMap;

public class Map {
    public void run(){
        java.util.Map<String, User> users = new HashMap<>();
        //System.out.println(users.isEmpty());

        users.put("joao@joao.com", new User("João", 22));
        users.put("maria@maria.com", new User("Maria", 35));
        users.put("juca@juca.com", new User("Juca", 44));
        users.put("leo@leo.com", new User("Leo", 16));
        //System.out.println(users);

        //System.out.println("------------");

        //users.keySet().forEach(System.out::println);
        //System.out.println("------------");
        //users.values().forEach(System.out::println);

        //System.out.println(users.containsKey("leo@leo.com"));
        //System.out.println(users.containsValue(new User("Leo", 16)));

        //System.out.println(users.remove("joao@joao.com"));
        //System.out.println(users.remove("joao@joao.com", new User("Leo", 16)));
        //System.out.println(users.remove("joao@joao.com", new User("João", 22)));

//        users.forEach((k,v) -> System.out.printf("Ket: %s | value: %s \n", k,v));
//        System.out.println("-----------------------");
//        users.replace("joao@joao.com", new User("João Gabriel", 14));
//        users.forEach((k,v) -> System.out.printf("Ket: %s | value: %s \n", k,v));

//        System.out.println(users.get("maria@maria.com"));

//        users.merge("leo@leo.com", new User("", -1), (user, user2) -> {
//            System.out.println(user);
//            System.out.println(user2);
//            return user2;
//        });

        //System.out.println(users);

        users.putIfAbsent("", new User("", -1));
        System.out.println(users);

        //Map<String, User> users2 = new TreeMap<>();
        /*
        TreeMap funciona como árvore binária. O que for maior, joga pra um
        * lado, e o que for menor joga para o outro. E pode definir
         o método de comparação. */

        // Map<String, User> users = new LinkedHashMap<>();
        // mantém a ordem de inserção. Um elemento conhece seu próximo
    }
}

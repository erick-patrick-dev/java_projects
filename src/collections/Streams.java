import domain_stream.Contact;
import domain_stream.User;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static domain_stream.ContactType.EMAIL;
import static domain_stream.ContactType.PHONE;
import static domain_stream.Sex.FEMALE;
import static domain_stream.Sex.MALE;

public class Streams {
    // Introdução a API de streams
    public static void run(){
        var value1 = Stream.generate(() -> new Random().nextInt())
                .limit(5)
                .toArray(Integer[]::new);

        for (var v: value1){
            System.out.println(v);
        }
        System.out.println("------------------------");

        var value2 = IntStream.generate(() -> new Random().nextInt())
                .limit(5)
                .toArray();

        for (var v: value2){
            System.out.println(v);
        }
    }

    public static void run2(){
        List<String> debugValues = new ArrayList<>();

        var value = Stream.of("Maria", "João", "Marcio", "Leandro" ,"Gabriela", "Macarena")
                //.peek(System.out::println) // Método pra debug
                .peek(debugValues::add)
                .filter(name -> name.endsWith("o"))
                .toList();

        System.out.println(value);
        System.out.println(debugValues);
    }

    public static void run3(){
        // É possível converter uma list para stream
        var value = List.of("Maria", "João", "Marcio", "Leandro" ,"Gabriela", "Macarena")
                .stream()
                .filter(name -> name.endsWith("o"))
                .toList();

        System.out.println(value);
    }

    public static void run4(){
        var value = Stream.of("Maria", "João", "Marcio", "Leandro" ,"Gabriela", "Macarena")
                .filter(name -> name.endsWith("o"))
                //.allMatch(n -> n.contains("J"));
                //.findFirst();
                .anyMatch(n -> n.contains("a")); // anyMatch diferencia acentuação

        // anyMatch, allMatch, findAny e findFirst e toList são operações terminais dos streams.

        System.out.println(value);
    }

    public static void run5(){
        var value = Stream.of("Maria", "João", "Marcio", "Leandro" ,"Gabriela", "Macarena")
                .reduce("", (a,b) -> a + ";"+b)
                .replaceFirst(";", "");

        System.out.println(value);
    }

    public static void run6(){
        var value = Stream.of(1,2,3,4,5,6,7, 1, 7)
                .distinct().toList(); // removendo valores duplicados
                //.reduce(0, Integer::sum);

        System.out.println(value);
    }

    public static void run7(){
        var value = Stream.of(1,2,3,4,5,6,7, 1, 7)
                .map(n -> n%2 == 0)
                .toList();

        System.out.println(value);
    }

    public static void run8(){
        List<Integer> values1 = List.of(3,6,9,12);
        List<Integer> values2 = List.of(1,2,3,4,5,6,7,8,9,10,11,12);

        var newValues = values2.stream()
                .filter(values1::contains)
                .map(n -> values1.stream().reduce(n, (n1,n2) -> n1-n2))
                .collect(Collectors.toSet());

        System.out.println(newValues);
    }

    // Explorando API de streams
    private static List<User> generateUsers(){
        var contacts1 = List.of(
                new Contact("(72)9999-8888", PHONE),
                new Contact("joao@email.com", EMAIL)
        );
        var contacts2 = List.of(
                        new Contact("(19)9473-2030", PHONE)
                );
        var contacts3 = List.of(
                        new Contact("lucas@mail.com", EMAIL)
                );
        var contacts4 = List.of(
                        new Contact("andreia@yahoo.com", EMAIL),
                        new Contact("andreia@jsmail.com", EMAIL)
                );
        var contacts5 = List.of(
                        new Contact("(44)4656-1732", PHONE),
                        new Contact("(44)9956-1724", PHONE)
                );

        var user1 = new User("João", 26, MALE, new ArrayList<>(contacts1));
        var user2 = new User("Maria", 28, FEMALE, new ArrayList<>(contacts2));
        var user3 = new User("Lucas", 33, MALE, new ArrayList<>(contacts3));
        var user4 = new User("Andreia", 40, FEMALE, new ArrayList<>(contacts4));
        var user5 = new User("Vitor", 30, MALE, new ArrayList<>(contacts5));
        var user6 = new User("Bruna", 36, FEMALE, new ArrayList<>());

        return List.of(user1, user2, user3, user4, user5, user6);
    }

    public static void exp(){
        List<User> users = new ArrayList<>(generateUsers());
        users.sort(Comparator.comparing(User::name));
        // users.sort(Comparator.comparing(User::age, Comparator.reverseOrder()));

        users.forEach(System.out::println);
    }

    public static void exp2(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                //.filter(u -> u.contacts().size() >= 2)
                .filter(u -> u.contacts() == null || u.contacts().isEmpty())
                        .toList();

        values.forEach(System.out::println);
    }

    public static void exp3(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                // pegar todos os contatos que contenham email
                //.filter(u -> u.contacts().stream().anyMatch(c -> c.type() == EMAIL))
                // pegando todos os contatos que tenham apenas email
                .filter(u -> u.contacts().stream().allMatch(c -> c.type() == EMAIL))
                .toList();

        values.forEach(System.out::println);
    }

    public static void exp4(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                .flatMap(u -> u.contacts().stream())
                //.filter(c -> c.type() == EMAIL)
                .sorted((Comparator.comparing(Contact::description))) // ordenando retorno pela description
                .toList();

        values.forEach(System.out::println);
    }

    public static void exp5(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                .flatMap(u -> u.contacts().stream())
                .sorted((Comparator.comparing(Contact::description))) // ordenando retorno pela description
                .map(c -> String.format("{'\n    description': %s,\n    'type': %s\n}", c.description(), c.type()))
                .toList();

        values.forEach(System.out::println);
    }

    public static void exp6(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                .flatMap(u -> u.contacts().stream())
                .sorted((Comparator.comparing(Contact::description))) // ordenando retorno pela description
                .filter(c -> c.description().contains("email"))
                //.limit(1)
                .toList();

        values.forEach(System.out::println);
    }

    public static void exp7(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                .flatMap(u -> u.contacts().stream())
                .filter(c -> c.type() ==PHONE)
                .map(c -> c.description().replace("(", "")
                        .replace(")", "")
                        .replace("-", "")
                )
                .mapToLong(Long::parseLong)
                .min();
                //.toList();

        System.out.println(values);;
    }

    public static void exp8(){
        List<User> users = new ArrayList<>(generateUsers());

        var values = users.stream()
                .filter(u -> u.sex() == FEMALE)
                .collect(Collectors.toMap(User::name, user -> user));

        values.forEach((key, value) -> System.out.printf("key: %s | value: %s\n", key, value));
    }
}

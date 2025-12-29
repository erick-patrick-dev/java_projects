import domains.User;
import domains.UserV2;

import java.util.Optional;

import static domains.SexEnum.FEMALE;
import static domains.SexEnum.MALE;

public class Opcionais {
    public void run(){
        //Optional<domains.User> optional = Optional.empty();
        //Optional<User> optional = Optional.ofNullable(new User("João", 18, MALE));

        //System.out.println(optional.isPresent());
        //System.out.println(optional.isEmpty());

        Optional<User> optional = Optional.of(new User("João", 18, MALE));
//        System.out.println(optional.get()); // Não é boa prática, por conta de NullPointerException

        //optional.ifPresent(System.out::println); // Proteção contra NullPointerException
//        final int newAge = 22; // Variáveis usadas em expressões lambda devem ser final
//
//        optional.ifPresentOrElse(
//                user -> {
//                    System.out.printf("Usuário: %s \n", user);
//                    user = new User("João", newAge, MALE);
//                    System.out.printf("Usuário: %s \n", user);
//                },
//                () -> System.out.println("Usuário não informado.")
//                );

        //System.out.println(optional.orElse(new User("Maria", 33, FEMALE))); // Consulta do optional ou valor default

        // System.out.println(optional.orElseThrow()); // retornando exception padrão
        //System.out.println(optional.orElseThrow(() -> new RuntimeException(""))); // personalizando exception

//        System.out.println(optional.orElse(defaultUser())); // Valor fixo padrão

        // Trabalha com lambda, ideal para banco de dados
//        System.out.println(optional.orElseGet(Main::defaultUser));

        var newUser = optional.map(user -> new UserV2(user.name(), user.age(), user.sex())).orElseThrow();
        // A função map do optional verifica se o mesmo está vazio, e não dispara exception.
        System.out.println(newUser);
    }

    public static User defaultUser(){
        System.out.println("Buscando valor default");
        return new User("Maria", 22, FEMALE);
    }
}

import br.com.dio.dao.UserDAO;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Run {
    private final static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);
    private static long requestId(){
        System.out.println("Informe o identificador do usuário");
        return scanner.nextLong();
    }

    private static String requestName() {
        System.out.println("Informe o nome do usuário");
        var res = scanner.next();
        return res;
    }

    private static String requestEmail() {
        System.out.println("Informe o e-mail do usuário");
        var email = scanner.next();
        return email;
    }

    private static OffsetDateTime requestBirthday() {
        System.out.println("Informe o data de nascimento do usuário (dd/MM/yyyy)");
        var birthdayString = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var birthday = OffsetDateTime.parse(birthdayString, formatter);
        return birthday;
    }

    private static UserModel requestToSave(){
        var name = requestName();
        var email = requestEmail();
        var birthday = requestBirthday();

        return new UserModel(0, name, email, birthday);
    }

    private static UserModel requestToUpdate(){
        var id = requestId();
        var name = requestName();
        var email = requestEmail();
        var birthday = requestBirthday();

        return new UserModel(id, name, email, birthday);
    }

    public void run(){
        while (true){
            System.out.println("Bem-vindo ao cadastro de usuários. Selecione a operação desejada:");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("Buscar por identificador");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");
            var userInput = scanner.nextInt();
            var selectedOption = MenuOption.values()[userInput -1];
            switch (selectedOption){
                case SAVE -> {
                    var user = dao.save(requestToSave());
                    System.out.printf("Usuário cadastrado: %s", user);
                }
                case UPDATE -> {
                    var user = dao.update(requestToUpdate());
                    System.out.printf("Usuário atualizado: %s", user);
                }
                case DELETE -> {
                    dao.delete(requestId());
                    System.out.println("Usuário excluído");
                }
                case FIND_BY_ID -> {
                    var id = requestId();
                    var user = dao.findById(id);
                    System.out.printf("Usuário com id %s:", id);
                    System.out.println(user);
                }
                case FIND_ALL -> {
                    var users = dao.findAll();
                    System.out.println("====================");
                    System.out.println("Usuários cadastrados");
                    users.forEach(System.out::println);
                    System.out.println("==========fim==========");
                }
                case EXIT -> System.exit(0);
            }
        }
    }
}

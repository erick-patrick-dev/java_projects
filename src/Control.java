import java.util.Scanner;

public class Control {
    public void control(){
        var scanner = new Scanner(System.in);
        System.out.print("Informe seu nome: ");
        var name = scanner.next();

        System.out.print("Informe sua idade: ");
        var age = scanner.nextInt();
        System.out.println("Você é emancipado? (s/n)");
        var isEmancipated = scanner.next().equalsIgnoreCase("s");

        var canDrive = (age >= 18) || (age >= 16 && isEmancipated);
        if (canDrive) {
            System.out.println("Bem-vindo(a)");
            System.out.printf("%s tem  %s anos, você pode dirigir \n", name, age);
        } else {
            System.out.printf("%s você não pode dirigir! \n", name);
        }

        System.out.println("Fim de execução");
    }
}

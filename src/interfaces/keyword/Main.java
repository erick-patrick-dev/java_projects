import static keyword.Client.getStaticName;
import static keyword.Client.setStaticName;

public class Main {
    public static void main(String[] args) {
        setStaticName("Teste");
        System.out.println(getStaticName());
    }
}

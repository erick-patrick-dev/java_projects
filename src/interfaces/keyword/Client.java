package keyword;

// Sem um modificador de privacidade (default), a classe não é acessível de fora.
public class Client {
    private static String staticName;
    private int age;


    public static String getStaticName() {
        return staticName;
    }

    public static void setStaticName(String param) {
        staticName = param;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public class Nested {
        public void teste(){
            name = "";
        }
    }
}



/*
* Para ter acesso a atributos protected, você precisa:
*   1- Estar no mesmo package
*   2- Ter herança da classe
*   (um pouco mais aberto que o Default)
*/

/*
* Uma classe dentro da classe tem acesso a atributos privados,
*   mas o mesmo não ocorre quando o atributo é static.
*/

/*
* Para bloquear uma classe de ser herdada, defina-a como final.
* Para bloquear de ser instanciada, defina o construtor dela como private.
*/
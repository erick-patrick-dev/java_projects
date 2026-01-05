import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;
import br.com.dio.persistence.NIOFilePersistence;

import java.io.IOException;

public class Run {
  public void runIO(){
    FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println("=====Escrita=====");
        System.out.println(persistence.write("Marcos;marcos@marcos.com;24/07/2000;"));
        System.out.println(persistence.write("Vitor;vitin@vitin.com;15/03/1963;"));
        System.out.println(persistence.write("Prometheus;propro@propro.com;15/11/1918;"));
//        System.out.println("============");
//
//        System.out.println(persistence.findAll());
        System.out.println("\n======Busca======");

        System.out.println(persistence.findBy("Vitor;") + " - Encontrado");
        System.out.println(persistence.findBy("@vitin") + " - Encontrado");
        System.out.println(persistence.findBy("pro") + " - Encontrado");

        System.out.println("\n=====Remoção=====");
        System.out.println(persistence.remove("03/19"));
        System.out.println(persistence.findAll());

        System.out.println("\n=====Replace=====");
        System.out.println(persistence.replace(".com;24/07/", "Carlos;carlos@carlos.com;04/08/2006"));
        System.out.println(persistence.findAll());
  }

  public void runNIO(){
    FilePersistence persistence = new NIOFilePersistence("user.csv");
        System.out.println(persistence.write("Joao;joao.silva@exemplo.com;15/03/1990"));
        System.out.println("==========");
        System.out.println(persistence.write("Maria;maria.oliveira@exemplo.com;22/11/1985"));
        System.out.println("==========");
        System.out.println(persistence.write("Ana;ana.costa@exemplo.com;01/07/2001"));
        System.out.println("==========");
        System.out.println(persistence.findAll());
        System.out.println("=====FindBy=====");
        System.out.println(persistence.findBy("costa@"));
        System.out.println("==========");
        System.out.println(persistence.findBy("laura@"));
        System.out.println("==========");
        System.out.println(persistence.remove("oliveira@"));
        System.out.println("==========");
        System.out.println(persistence.findAll());
        System.out.println("=====Replace=====");
        System.out.println(persistence.replace("costa@", "Victor;victin@exemplo.com;18/04/2009"));
        System.out.println("=====Find All=====");
        System.out.println(persistence.findAll());
        System.out.println("==========");
  }
}

/*
    A IO foi a primeira API implementada no Java. Ela não é recomendada em caso de muitas leituras
    e operações mais complexas, pois bloqueia a thread principal.
    A IO deve ser usada para questões mais simples, pois a NIO é severamente complexa.
*/



/*
  A NIO é exponencialmente mais complexa que a IO, mas há um ganho muito grande em performance.
  Requer uma análise mais criteriosa dos requisitos da aplicação para decidir se vale a pena 
  implementá-la.
*/

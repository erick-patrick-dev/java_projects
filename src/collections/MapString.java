import java.util.HashMap;
import java.util.Map;

public class MapString {
    public void run(){
        var value = "java;java;java;java;java;";

        //trocar strings
//        value = value.replace("j", "J");
//        value = value.replaceFirst("j", "J");

        // Dividir strings por separador
//        var values = value.split(";");
//        for (var v: values){
//            System.out.println(v);
//        }

//        System.out.println(value.toUpperCase());
//        System.out.println(value.toLowerCase());

        var value2 = """
                {"name":"João","age":18}""";
        Map<String, String> map = new HashMap<>();
        value2 = value2.replace("{", "").replace("}", "").replace("\"", "");
        var valueArr = value2.split(",");

        for (var v: valueArr){
            var keyValue = v.split(":");
            map.put(keyValue[0], keyValue[1]);
        }
        System.out.println(map);



        //System.out.println(value);
    }
}

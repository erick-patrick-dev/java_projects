package br.com.dio.persistence;

import java.io.*;

public class IOFilePersistence extends FilePersistence{


    public IOFilePersistence(final String fileName) throws IOException {
        super(fileName, "/managerFiles/IO/");
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();
    }

    @Override
    public String write(String data) {
        try(
                var fileWriter = new FileWriter(currentDir + storedDir + fileName, true);
                var bufferedWrite = new BufferedWriter(fileWriter);
                var printWriter = new PrintWriter(bufferedWrite);
                ){
            printWriter.println(data);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line;
            do {
                line = reader.readLine();
                if (line != null) content.append(line)
                        .append(System.lineSeparator());

            } while (line != null);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        var found = "";
        try (var reader = new BufferedReader(new FileReader(currentDir + storedDir + fileName))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null){
                if (line.contains(sentence)){
                    found = line;
                    break;
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return found;
    }

}

package br.com.dio.persistence;
import java.io.*;
import java.nio.ByteBuffer;

public class NIOFilePersistence extends FilePersistence{

    public NIOFilePersistence(final String fileName) throws IOException {
        super(fileName, "/managerFiles/NIO/");
        var file = new File(currentDir + storedDir);
        if (!file.exists() && !file.mkdirs()) throw new IOException("Erro ao criar arquivo");

        clearFile();
    }

    @Override
    public String write(String data) {
        try(var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "rw");){
            file.seek(file.length());
            file.writeBytes(data);
            file.writeBytes(System.lineSeparator());
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return data;
    }

    @Override
    public String findAll() {
        var content = new StringBuilder();

        try(
                var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "r");
                var channel = file.getChannel();
        ){
            var buffer = ByteBuffer.allocate(256);
            var bytesReader = channel.read(buffer);
            while (bytesReader != -1){
                buffer.flip();
                while (buffer.hasRemaining()){
                    content.append((char) buffer.get());
                }
                buffer.clear();
                bytesReader = channel.read(buffer);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public String findBy(String sentence) {
        // Possibilidade com stream
//        var contentCall = findAll();
//        var contentList = new ArrayList<>(Stream.of(contentCall.split(System.lineSeparator())).toList());
//
//        return Stream.of(contentList).filter(c -> c.contains(sentence))
//                .findFirst()
//                .orElse("");


        var content = new StringBuilder();
        try(
                var file = new RandomAccessFile(new File(currentDir + storedDir + fileName), "r");
                var channel = file.getChannel();
        ){
            var buffer = ByteBuffer.allocate(256);
            var bytesReader = channel.read(buffer);
            while (bytesReader != -1){
                buffer.flip();
                while (buffer.hasRemaining()){

                    while (!content.toString().endsWith(System.lineSeparator())) {
                        content.append((char) buffer.get());
                    }
                    if (content.toString().contains(sentence)) {
                        break;
                    } else {
                        content.setLength(0);
                    }

                    if (!content.isEmpty()) break;
                }
                buffer.clear();
                bytesReader = channel.read(buffer);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return content.toString();
    }

}

package appPack;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class UseFile {

    File folder = new File("files");
    File file = null;
    private String fileName = "files/sample.txt";

    public UseFile() {
        folder.mkdir();
        file = new File(fileName);
    }

    public UseFile(String fileName) {
        this.fileName = fileName;
        folder.mkdir();
        file = new File("files/"+fileName+".txt");
    }

    // create file
    public void createFile() {
        try {
            if ( !file.exists() ) {
                file.createNewFile();
            }
        }catch (Exception ex) {
            System.err.println("createFile err : " + ex);
        }
    }

    // delete file
    public void deleteFile() {
        try {
            if ( file.exists() ) {
                file.delete();
            }
        }catch (Exception ex) {
            System.err.println("deleteFile err : " + ex);
        }
    }

    // FileWriter
    public void write( String line ) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(line);
            fileWriter.append( System.lineSeparator() );
            fileWriter.close(); // kapatma olmadan kayıt başarılı olmaz.
        }catch (Exception ex) {
            System.err.println("write err : " + ex);
        }
    }

    // file Read
    public void read() {
        try {
            Scanner scanner = new Scanner(file);
            // for ( ; scanner.hasNext() ; ) {}
            while ( scanner.hasNext() ) {
                String item = scanner.nextLine();
                System.out.println( item );
            }
            scanner.close();
        }catch (Exception ex) {
            System.err.println("read err : " + ex);
        }
    }

}

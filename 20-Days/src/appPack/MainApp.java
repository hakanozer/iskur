package appPack;

public class MainApp {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));

        UseFile useFile = new UseFile("ornek");
        //useFile.createFile();
        //useFile.deleteFile();
        useFile.write("Kardelen");
        useFile.read();

    }
}

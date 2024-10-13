public class Main {
    public static void main(String[] args) {
        FileStorage localStorage = new LocalStorage();
        FileManager fileManagerLocal = new FileManager(localStorage);
        fileManagerLocal.saveFile("myFile.txt");
        fileManagerLocal.removeFile("myFile.txt");

        FileStorage cloudStorage = new CloudStorage();
        FileManager fileManagerCloud = new FileManager(cloudStorage);
        fileManagerCloud.saveFile("myCloudFile.txt");
        fileManagerCloud.removeFile("myCloudFile.txt");
    }
}

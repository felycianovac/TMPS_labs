import Compression.FileCompression;
import Encryption.FileEncryption;
import Managers.AdvancedFileManager;
import Managers.FileManager;
import Storage.CloudStorage;
import Storage.FileStorage;
import Storage.LocalStorage;

public class Main {
    public static void main(String[] args) {
        FileStorage localStorage = new LocalStorage();
        FileCompression compression = new FileCompression();
        FileEncryption encryption = new FileEncryption();

        FileManager advancedFileManager = new AdvancedFileManager(localStorage, compression, encryption);
        advancedFileManager.saveFile("myImportantFile.txt");
        System.out.println("Retrieved: " + advancedFileManager.retrieveFile("myImportantFile.txt.enc.zip"));

        FileStorage cloudStorage = new CloudStorage();
        AdvancedFileManager advancedFileManagerCloud = new AdvancedFileManager(cloudStorage, compression, encryption);
        advancedFileManagerCloud.saveFile("myCloudFile.txt");
        System.out.println("Retrieved: " + advancedFileManagerCloud.retrieveFile("myCloudFile.txt.enc.zip"));
    }
}

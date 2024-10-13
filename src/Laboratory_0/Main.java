package Laboratory_0;

import Laboratory_0.Compression.FileCompression;
import Laboratory_0.Encryption.FileEncryption;
import Laboratory_0.Managers.AdvancedFileManager;
import Laboratory_0.Managers.FileManager;
import Laboratory_0.Storage.CloudStorage;
import Laboratory_0.Storage.FileStorage;
import Laboratory_0.Storage.LocalStorage;

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

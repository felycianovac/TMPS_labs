package Laboratory_0.Storage;

public interface FileStorage {

    void storeFile(String fileName);
    void deleteFile(String fileName);
    String retrieveFile(String fileName);
}

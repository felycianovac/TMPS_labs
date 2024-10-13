package Managers;

import Compression.FileCompression;
import Encryption.FileEncryption;
import Storage.FileStorage;

public class AdvancedFileManager extends FileManager {
    private FileCompression compression;
    private FileEncryption encryption;

    public AdvancedFileManager(FileStorage fileStorage, FileCompression compression, FileEncryption encryption) {
        super(fileStorage);
        this.compression = compression;
        this.encryption = encryption;
    }

    @Override
    public void saveFile(String fileName) {
        String compressedFile = compression.compressFile(fileName);
        String encryptedFile = encryption.encryptFile(compressedFile);
        super.saveFile(encryptedFile);
    }

    @Override
    public void removeFile(String fileName) {
        super.removeFile(fileName);
    }

    public String retrieveFile(String fileName) {
        String decryptedFile = encryption.decryptFile(fileName);
        return compression.decompressFile(decryptedFile);
    }
}



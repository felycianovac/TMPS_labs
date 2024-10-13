package Encryption;

public class FileEncryption {
    public String encryptFile(String fileName) {
        System.out.println("Encrypting file " + fileName);
        return fileName + ".enc";
    }

    public String decryptFile(String fileName) {
        System.out.println("Decrypting file " + fileName);
        return fileName.replace(".enc", "");
    }
}

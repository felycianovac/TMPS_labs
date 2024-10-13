package Laboratory_0.Compression;

public class FileCompression {
    public String compressFile(String fileName) {
        System.out.println("Compressing file " + fileName);
        return fileName + ".zip";
    }

    public String decompressFile(String fileName) {
        System.out.println("Decompressing file " + fileName);
        return fileName.replace(".zip", "");
    }
}

package Storage;

public class CloudStorage implements FileStorage {
    /**
     * Single Responsibility Principle
     **/
        @Override
        public void storeFile(String fileName) {
            System.out.println("Storing file " + fileName + " in cloud storage");
        }

        @Override
        public void deleteFile(String fileName) {
            System.out.println("Deleting file " + fileName + " from cloud storage");
        }

        @Override
        public String retrieveFile(String fileName) {
            return "Retrieving file " + fileName + " from cloud storage";
        }
}

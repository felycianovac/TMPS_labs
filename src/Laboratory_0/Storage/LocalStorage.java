package Laboratory_0.Storage;

public class LocalStorage implements FileStorage {

        @Override
        public void storeFile(String fileName) {
            System.out.println("Storing file " + fileName + " in local storage");
        }

        @Override
        public void deleteFile(String fileName) {
            System.out.println("Deleting file " + fileName + " from local storage");
        }
        @Override
        public String retrieveFile(String fileName) {
            return "Retrieving file " + fileName + " from local storage";
        }
}

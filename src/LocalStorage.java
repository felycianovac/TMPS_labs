public class LocalStorage implements FileStorage{
    /**
     * Single Responsibility Principle
     **/

        @Override
        public void storeFile(String fileName) {
            System.out.println("Storing file " + fileName + " in local storage");
        }

        @Override
        public void deleteFile(String fileName) {
            System.out.println("Deleting file " + fileName + " from local storage");
        }
}

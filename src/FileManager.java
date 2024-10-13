public class FileManager {

        private FileStorage fileStorage;
        /**
         * Dependency Inversion Principle
         * The high-level module (FileManager) depends on the FileStorage abstraction (interface) rather than specific implementations like LocalStorage or CloudStorage.
         * Liskov Substitution Principle
         * The FileManager class can work with any class that implements the FileStorage interface.
        **/
        public FileManager(FileStorage fileStorage) {
            this.fileStorage = fileStorage;
        }

        public void saveFile(String fileName) {
            fileStorage.storeFile(fileName);
        }

        public void removeFile(String fileName) {
            fileStorage.deleteFile(fileName);
        }
}

package Laboratory_0.Managers;

import Laboratory_0.Storage.FileStorage;

public class FileManager {

        private FileStorage fileStorage;

        public FileManager(FileStorage fileStorage) {
            this.fileStorage = fileStorage;
        }

        public void saveFile(String fileName) {
            fileStorage.storeFile(fileName);
        }

        public void removeFile(String fileName) {
            fileStorage.deleteFile(fileName);
        }

        public String retrieveFile(String fileName) {
            return fileStorage.retrieveFile(fileName);
        }
}

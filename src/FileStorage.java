public interface FileStorage {
    /**
     * Open/Closed Principle
     * The FileStorage interface is open for extension but closed for modification.
     *
     * Interface Segregation Principle
     * no code should be forced to depend on methods it does not use. It is better to have many small, specific interfaces than a few large, general ones.
     **/

    void storeFile(String fileName);
    void deleteFile(String fileName);
}

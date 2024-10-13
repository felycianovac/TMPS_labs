# Laboratory Work #0 - SOLID


## Author: Felicia Novac

----

## Objectives:

* Get familiar with the SOLID principles.
* Implement at least two SOLID principles in a simple program.
* Demonstrate adherence to these principles through clean, maintainable, and extendable code.


## Used Princples: 

* **S** - Single-responsibility Principle
* **O** - Open-closed Principle
* **L** - Liskov Substitution Principle
* **I** - Interface Segregation Principle
* **D** - Dependency Inversion Principle


## Implementation

### Overview

In this laboratory work, I have developed a file storage system that applies SOLID principles to demonstrate how the code can be flexible, maintainable, and scalable. The core components include the `FileStorage` interface, which defines the contract for basic file operations such as storing, retrieving, and deleting files. Two classes—`LocalStorage` and `CloudStorage`—implement this interface, handling file operations in different storage mediums.

The `FileManager` serves as the core class responsible for handling basic file management operations.  It interacts with different storage systems via a common interface `FileStorage`. This allows the `FileManager` to perform tasks like saving, retrieving, and deleting files, while delegating the actual storage operations to the specific storage type being used. This design provides the flexibility to use various storage mechanisms without altering the core logic of file management.

Building on top of the FileManager, the `AdvancedFileManager` adds extra functionality, including file compression and encryption. This class extends the basic file operations by first compressing and then encrypting files before storing them, and reversing the process when retrieving them. By integrating the `FileCompression` and `FileEncryption` classes, the AdvancedFileManager enhances the system’s capabilities without interfering with the existing storage logic. This separation of concerns allows additional features to be added while keeping the core management structure intact.

### Code Snippets & SOLID Principles Explanation

#### 1. FileStorage Interface
As mentioned before, this interface defines the contract for basic file operations. By using this interface, we ensure that any class implementing FileStorage adheres to the same behavior, allowing `FileManager` to work with any storage type.

```
public interface FileStorage {
    void storeFile(String fileName);
    void deleteFile(String fileName);
    String retrieveFile(String fileName);
}
```
*Interface Segregation Principle:* The interface is simple and focused on storage operations, ensuring that any implementing class only needs to provide functionality relevant to storing, deleting, and retrieving files.

#### 2. LocalStorage and CloudStorage Implementations
These classes implement the FileStorage interface. Both focus on specific file operations, either in local or cloud environments.

*Single-responsibility Principle:* Each class has a clearly defined responsibility—`LocalStorage` manages file storage in the local environment, while `CloudStorage` manages file operations in a cloud environment. They both focus only on their respective file operations.

*Liskov Substitution Principle:* Since both `LocalStorage` and `CloudStorage` implement the `FileStorage` interface, they can be used interchangeably in `FileManager`. This ensures the correctness of the program regardless of which storage system is used.


#### 3. FileManager Class
The FileManager class is responsible for managing files using any storage implementation. It interacts with the FileStorage interface, allowing flexibility in the type of storage used (local or cloud storage).
```
    public void saveFile(String fileName) {
        fileStorage.storeFile(fileName);
    }

    public void removeFile(String fileName) {
        fileStorage.deleteFile(fileName);
    }

    public String retrieveFile(String fileName) {
        return fileStorage.retrieveFile(fileName);
    }

```
*Dependency Inversion Principle:* FileManager depends on the FileStorage abstraction rather than concrete classes such as LocalStorage or CloudStorage. This makes the system flexible, as you can switch between different storage types by injecting a different implementation of FileStorage.

#### 4. AdvancedFileManager Class
The `AdvancedFileManager` extends `FileManager` by adding additional features such as file compression and encryption. It enhances the file operations by first compressing and then encrypting files before storing them, and reversing the process when retrieving files.

```
@Override
    public void saveFile(String fileName) {
        String compressedFile = compression.compressFile(fileName);
        String encryptedFile = encryption.encryptFile(compressedFile);
        super.saveFile(encryptedFile);
    }

    @Override
    public String retrieveFile(String fileName) {
        String decryptedFile = encryption.decryptFile(fileName);
        return compression.decompressFile(decryptedFile);
    }
```
*Open-closed Principle:* `AdvancedFileManager` extends the functionality of the `FileManager` by adding file compression and encryption. This demonstrates how the system is open to extension (new functionalities) without modifying the core file management logic. New features can be added without altering existing classes.
* If needed, screenshots.

#### 5. FileCompression and FileEncryption Classes
These classes handle file compression and encryption, which are used by the `AdvancedFileManager`.

*Single-responsibility Principle:* Each of these classes has a single, well-defined responsibility—`FileCompression` handles compressing and decompressing files, while `FileEncryption` handles encrypting and decrypting files.

## Results & Screenshots

To demonstrate how the program works, I've implemented a simple `Main` class that shows the functionality of the file storage system using both LocalStorage and CloudStorage, along with file compression and encryption.
```
 public static void main(String[] args) {
        FileStorage localStorage = new LocalStorage();
        FileCompression compression = new FileCompression();
        FileEncryption encryption = new FileEncryption();

        FileManager advancedFileManager = new AdvancedFileManager(localStorage, compression, encryption);
        advancedFileManager.saveFile("myImportantFile.txt");
        System.out.println("Retrieved: " + advancedFileManager.retrieveFile("myImportantFile.txt.enc.zip"));

        FileStorage cloudStorage = new CloudStorage();
        AdvancedFileManager advancedFileManagerCloud = new AdvancedFileManager(cloudStorage, compression, encryption);
        advancedFileManagerCloud.saveFile("myCloudFile.txt");
        System.out.println("Retrieved: " + advancedFileManagerCloud.retrieveFile("myCloudFile.txt.enc.zip"));
    }
```
**Outputs:**

For the Local Storage, the file myImportantFile.txt is compressed into myImportantFile.txt.zip, encrypted into myImportantFile.txt.enc.zip, and then stored in `LocalStorage`. Upon retrieval, the file is decrypted, decompressed, and the original file myImportantFile.txt is retrieved successfully.
```
Compressing file myImportantFile.txt
Encrypting file myImportantFile.txt.zip
Storing file myImportantFile.txt.zip.enc in local storage
Decrypting file myImportantFile.txt.enc.zip
Decompressing file myImportantFile.txt.zip
Retrieved: myImportantFile.txt
```

Similarly, for Cloud Storage, the file myCloudFile.txt is compressed and encrypted before being stored in `CloudStorage`. The file is decrypted, decompressed, and retrieved in its original form, myCloudFile.txt.
```
Compressing file myCloudFile.txt
Encrypting file myCloudFile.txt.zip
Storing file myCloudFile.txt.zip.enc in cloud storage
Decrypting file myCloudFile.txt.enc.zip
Decompressing file myCloudFile.txt.zip
Retrieved: myCloudFile.txt
```

## Conclusions

In this laboratory work, I developed a flexible and maintainable file storage system. The system handles file operations across both local and cloud storage, with added features such as file compression and encryption. The design allowed seamless integration of these features without altering the core file management logic. Thanks to the integration of SOLID principles, the system is adaptable, allowing for easy addition of new storage types or processing features in the future. This approach ensures the codebase remains clean, organized, and ready for future enhancements.

package in.bitcode.FileManagementApi.Service;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class Services {

    private static final String FILE_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    public String saveFile(MultipartFile file) throws IOException {
        File directory = new File(FILE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String filePath = FILE_DIRECTORY + "/" + file.getOriginalFilename();
        Files.write(Paths.get(filePath), file.getBytes());
        return "File saved successfully: " + filePath;
    }

    public byte[] getFile(String fileName) throws IOException {
        String filePath = FILE_DIRECTORY + "/" + fileName;
        return Files.readAllBytes(Paths.get(filePath));
    }

    public String updateFile(String fileName, MultipartFile file) throws IOException {
        String filePath = FILE_DIRECTORY + "/" + fileName;
        Files.write(Paths.get(filePath), file.getBytes());
        return "File updated successfully: " + filePath;
    }

    public String deleteFile(String fileName) throws IOException {
        String filePath = FILE_DIRECTORY + "/" + fileName;
        Files.deleteIfExists(Paths.get(filePath));
        return "File deleted successfully: " + filePath;
    }

    public Stream<String> listFiles() throws IOException {
        return Files.list(Paths.get(FILE_DIRECTORY))
                .map(path -> path.getFileName().toString());
    }
}
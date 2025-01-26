package graber.thomas.feastverse.service;

import graber.thomas.feastverse.configuration.upload.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class FileUploadService {

    private final UploadProperties uploadProperties;

    @Autowired
    public FileUploadService(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String storageType = uploadProperties.getStorageType();

        if ("local".equalsIgnoreCase(storageType)) {
            return uploadToLocal(file);
        } else if ("cloudinary".equalsIgnoreCase(storageType)) {
            return uploadToCloudinary(file);
        }

        throw new IllegalStateException("Invalid storage type configured: " + storageType);
    }

    public String uploadToLocal(MultipartFile file) throws IOException {
        String uploadDir = uploadProperties.getLocal().getDirectory();
        // Construire le chemin complet vers "src/main/resources/static/upload"
        Path uploadPath = Paths.get("src/main/resources/static", uploadDir).toAbsolutePath().normalize();

        // Créer le répertoire s'il n'existe pas
        File directory = uploadPath.toFile();
        if (!directory.exists()) {
            boolean dirsCreated = directory.mkdirs();
            if (!dirsCreated) {
                throw new IOException("Failed to create directory: " + uploadPath);
            }
        }

        // Sauvegarder le fichier
        Path targetPath = uploadPath.resolve(file.getOriginalFilename());
        try {
            file.transferTo(targetPath.toFile());
            System.out.println("File successfully uploaded to: " + targetPath);
        } catch (IOException e) {
            System.err.println("Failed to upload file to: " + targetPath);
            throw e;
        }

        // Retourner seulement le nom du fichier
        return file.getOriginalFilename();
    }



    private String uploadToCloudinary(MultipartFile file) throws IOException {
        var cloudinary = new com.cloudinary.Cloudinary(Map.of(
                "cloud_name", uploadProperties.getCloudinary().getCloudName(),
                "api_key", uploadProperties.getCloudinary().getApiKey(),
                "api_secret", uploadProperties.getCloudinary().getApiSecret()
        ));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
        return uploadResult.get("secure_url").toString();
    }
}

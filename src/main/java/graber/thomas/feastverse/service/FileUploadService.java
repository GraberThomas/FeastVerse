package graber.thomas.feastverse.service;

import graber.thomas.feastverse.configuration.upload.UploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

@Service
public class FileUploadService {

    private final UploadProperties uploadProperties;

    @Autowired
    public FileUploadService(UploadProperties uploadProperties) {
        this.uploadProperties = uploadProperties;
    }

    // Méthode générique pour uploader un fichier
    public String uploadFile(MultipartFile file) throws IOException {
        String storageType = uploadProperties.getStorageType();

        if ("local".equalsIgnoreCase(storageType)) {
            return uploadToLocal(file);
        } else if ("cloudinary".equalsIgnoreCase(storageType)) {
            return uploadToCloudinary(file);
        }

        throw new IllegalStateException("Invalid storage type configured: " + storageType);
    }

    // Méthode générique pour supprimer un fichier
    public boolean deleteFile(String identifier) {
        String storageType = uploadProperties.getStorageType();

        if ("local".equalsIgnoreCase(storageType)) {
            return deleteLocalFile(identifier); // Supprimer un fichier local par nom
        } else if ("cloudinary".equalsIgnoreCase(storageType)) {
            return deleteCloudinaryFile(identifier); // Supprimer un fichier sur Cloudinary par publicId
        }

        throw new IllegalStateException("Invalid storage type configured: " + storageType);
    }

    // Upload vers stockage local
    public String uploadToLocal(MultipartFile file) throws IOException {
        String uploadDir = uploadProperties.getLocal().getDirectory();
        Path uploadPath = Paths.get("src/main/resources/static", uploadDir).toAbsolutePath().normalize();

        // Créer le répertoire s'il n'existe pas
        File directory = uploadPath.toFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Failed to create directory: " + uploadPath);
        }

        // Sauvegarder le fichier
        Path targetPath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        file.transferTo(targetPath.toFile());
        return file.getOriginalFilename(); // Retourner seulement le nom du fichier
    }

    // Upload vers Cloudinary
    private String uploadToCloudinary(MultipartFile file) throws IOException {
        var cloudinary = new com.cloudinary.Cloudinary(Map.of(
                "cloud_name", uploadProperties.getCloudinary().getCloudName(),
                "api_key", uploadProperties.getCloudinary().getApiKey(),
                "api_secret", uploadProperties.getCloudinary().getApiSecret()
        ));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
        return uploadResult.get("secure_url").toString(); // Retourner l'URL sécurisée
    }

    // Suppression d'une image en local
    public boolean deleteLocalFile(String fileName) {
        String uploadDir = uploadProperties.getLocal().getDirectory();
        Path filePath = Paths.get("src/main/resources/static", uploadDir, fileName).toAbsolutePath().normalize();

        File file = filePath.toFile();
        if (file.exists()) {
            return file.delete(); // Retourne true si le fichier est supprimé, false sinon
        }
        return false; // Retourne false si le fichier n'existe pas
    }

    // Suppression d'une image sur Cloudinary
    public boolean deleteCloudinaryFile(String publicId) {
        try {
            var cloudinary = new com.cloudinary.Cloudinary(Map.of(
                    "cloud_name", uploadProperties.getCloudinary().getCloudName(),
                    "api_key", uploadProperties.getCloudinary().getApiKey(),
                    "api_secret", uploadProperties.getCloudinary().getApiSecret()
            ));

            Map deleteResult = cloudinary.uploader().destroy(publicId, Map.of());
            return "ok".equals(deleteResult.get("result")); // Retourne true si la suppression est réussie
        } catch (Exception e) {
            System.err.println("Error deleting file from Cloudinary: " + e.getMessage());
            return false; // Retourne false en cas d'échec
        }
    }
}

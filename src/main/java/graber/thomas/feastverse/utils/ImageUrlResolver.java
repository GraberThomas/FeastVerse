package graber.thomas.feastverse.utils;

import graber.thomas.feastverse.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlResolver {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public String resolveUrl(boolean isStatic, String folderName ,String fileName, String cloudinaryUrl) {
        if ("prod".equals(activeProfile)) {
            return cloudinaryUrl; // URL Cloudinary
        } else {
            if (isStatic) {
                return "/images/" + folderName + "/" + fileName;
            }
            return "/upload/"  + fileName;
        }
    }

    public String extractFileNameWithoutExtension(String url) {
        if(activeProfile.equals("dev")){
            return url;
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        String fileNameWithExtension = url.substring(url.lastIndexOf("/") + 1);

        int dotIndex = fileNameWithExtension.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileNameWithExtension.substring(0, dotIndex);
        }

        return fileNameWithExtension;
    }
}

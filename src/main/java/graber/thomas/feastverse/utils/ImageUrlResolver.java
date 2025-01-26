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
}

package graber.thomas.feastverse.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImageUrlResolver {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public String resolveUrl(String fileName, String cloudinaryUrl) {
        if ("prod".equals(activeProfile)) {
            return cloudinaryUrl; // URL Cloudinary
        } else {
            return "/upload/" + fileName; // Chemin local
        }
    }
}

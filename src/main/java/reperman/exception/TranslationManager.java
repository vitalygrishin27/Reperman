package reperman.exception;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public enum TranslationManager {
    INSTANCE;

    private static final String PROPERTIES_PATH_EN = "/error-messages_en.properties";
    private static final String PROPERTIES_PATH_DE = "/error-messages_de.properties";
    private Properties enMessages = getPropertiesFromFile(PROPERTIES_PATH_EN);
    private Properties deMessages = getPropertiesFromFile(PROPERTIES_PATH_DE);

    public String getEnglishErrorMessage(String key) {
        return enMessages.getProperty(key);
    }

    public String getGermanErrorMessage(String key) {
        return deMessages.getProperty(key);
    }

    private static Properties getPropertiesFromFile(String path) {
        Properties result = new Properties();
        try (InputStreamReader stream = (new InputStreamReader(AppException.class.getResourceAsStream(path), StandardCharsets.UTF_8))) {
            result.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read error messages", e);
        }
        return result;
    }
}

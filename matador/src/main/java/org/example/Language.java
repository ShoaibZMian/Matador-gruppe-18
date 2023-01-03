package org.example;

import com.google.gson.Gson;
import org.example.models.LanguageModel;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import com.google.gson.JsonIOException;
import java.io.FileReader;

public class Language {

    private String languageCode;
    private LanguageModel languageData;
    private String unixPath = "/matador/src/main/java/org/example/translations/%s.json";
    private String windowsPath = "\\matador\\src\\main\\java\\org\\example\\translations\\%s.json";

    public Language(String languageCode) {
        this.languageCode = languageCode;
        loadLanguage();
    }

    public LanguageModel getLanguageData() {
        return languageData;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    private void loadLanguage() {
        Gson gson = new Gson();

        // Check for unix / windows system
        String path = System.getProperty("user.dir");

        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.equals("mac") || operatingSystem.equals("linux")) {
            path += String.format(unixPath, languageCode);

        } else if (operatingSystem.contains("windows")) {
            path += String.format(windowsPath, languageCode);
        }

        // Try to read a JSON from the defined language code
        try {
            languageData = gson.fromJson(new FileReader(path), LanguageModel.class);

        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

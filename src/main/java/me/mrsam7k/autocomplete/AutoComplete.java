package me.mrsam7k.autocomplete;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoComplete implements ModInitializer {

    public static Collection<String> autoCompleteWords = new ArrayList<>();
    public static Languages selectedLang = Languages.english;
    public static String multiLangCheck = "";

    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        updateLanguage();
        try {
            Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
            Class<? extends Config.MultiLangObject> clazz = config.multiLanguageObject.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                boolean bool = false;

                bool = field.getBoolean(config.multiLanguageObject);

                if (!field.getName().equals("allowMultipleLanguages") && bool) {
                    Languages language = Languages.valueOf(field.getName());

                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLanguage() {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        selectedLang = config.selectedLang;
        autoCompleteWords = fetchLanguage(selectedLang);
    }

    public static void updateMultiLanguages() throws IllegalAccessException {
        System.out.println("Starting to update multiple languages");
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        Class<? extends Config.MultiLangObject> clazz = config.multiLanguageObject.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Collection<String> collection = new ArrayList<>();
        for(Field field : declaredFields) {
            boolean bool = field.getBoolean(config.multiLanguageObject);
            if(!field.getName().equals("allowMultipleLanguages") && bool) {
            Languages language = Languages.valueOf(field.getName());
            collection.addAll(fetchLanguage(language));
            }
            autoCompleteWords = collection;
        }
        multiLangCheck = getMultiLangCheck();
        System.out.println("Finished updating multiple languages");
    }

    public static String getMultiLangCheck() throws IllegalAccessException {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        Class<? extends Config.MultiLangObject> clazz = config.multiLanguageObject.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for(Field field : declaredFields) {
            boolean bool = field.getBoolean(config.multiLanguageObject);
            sb.append(bool);
        }
        return sb.toString();
    }

    public static Collection<String> fetchLanguage(Languages language) {
        try {
            URI uri = URI.create(String.format("https://raw.githubusercontent.com/MrSam7K/data/main/AutoComplete/words%s.txt", language.getCode()));
            HttpResponse<String> httpResponse;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri).build();
            httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String[] content = httpResponse.body().split("\n");
            List<String> content2 = new ArrayList<>(List.of(content));
            content2.remove(0);
            return content2;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

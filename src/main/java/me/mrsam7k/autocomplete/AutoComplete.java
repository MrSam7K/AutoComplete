package me.mrsam7k.autocomplete;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoComplete implements ModInitializer {

    public static Collection<String> autoCompleteWords = new ArrayList<>();
    public static Languages selectedLang = Languages.English;

    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        updateLanguage();
    }

    public static void updateLanguage() {
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        selectedLang = config.selectedLang;
        try {
            URI uri = URI.create(String.format("https://raw.githubusercontent.com/MrSam7K/data/main/AutoComplete/words%s.txt", selectedLang.getCode()));
            HttpResponse<String> httpResponse;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri).build();
            httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String[] content = httpResponse.body().split("\n");
            List<String> content2 = new ArrayList<>(List.of(content));
            content2.remove(0);
            autoCompleteWords = content2;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

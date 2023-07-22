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

    @Override
    public void onInitialize() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);

        try {
            URI uri = URI.create("https://raw.githubusercontent.com/MrSam7K/data/main/AutoComplete/words.txt");
            HttpResponse<String> httpResponse;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri).build();
                httpResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String[] content = httpResponse.body().split("\n");
            autoCompleteWords = List.of(content);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

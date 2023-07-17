package me.mrsam7k.autocomplete;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import net.fabricmc.api.ModInitializer;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class AutoComplete implements ModInitializer {

    public static Collection<String> autoCompleteWords = new ArrayList<>();

    @Override
    public void onInitialize() {
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

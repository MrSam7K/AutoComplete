package me.mrsam7k.autocomplete.mixin;

import me.mrsam7k.autocomplete.AutoComplete;
import me.mrsam7k.autocomplete.Config;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.multiplayer.ClientSuggestionProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;

@Mixin(ClientSuggestionProvider.class)
public class ClientSuggestionProviderMixin {

    @Inject(method = "getCustomTabSugggestions", at = @At("RETURN"))
    public void addAutoCompleteWords(CallbackInfoReturnable<Collection<String>> cir){
        Config config = AutoConfig.getConfigHolder(Config.class).getConfig();
        if(!config.enableAutoComplete) return;
        if(config.selectedLang != AutoComplete.selectedLang){
            AutoComplete.updateLanguage();
        }
        Collection<String> currentCollection = cir.getReturnValue();
        currentCollection.addAll(AutoComplete.autoCompleteWords);
    }
}

package me.mrsam7k.autocomplete;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;

@me.shedaniel.autoconfig.annotation.Config(name = "autocomplete")
public class Config implements ConfigData {
    public boolean enableAutoComplete = true;

    @EnumHandler(option = EnumDisplayOption.BUTTON)
    public Languages selectedLang = Languages.English;
}

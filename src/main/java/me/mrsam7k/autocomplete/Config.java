package me.mrsam7k.autocomplete;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = "autocomplete")
public class Config implements ConfigData {
    public boolean enableAutoComplete = true;
}

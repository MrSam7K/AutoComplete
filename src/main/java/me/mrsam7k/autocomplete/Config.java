package me.mrsam7k.autocomplete;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;

@me.shedaniel.autoconfig.annotation.Config(name = "autocomplete")
public class Config implements ConfigData {
    public boolean enableAutoComplete = true;

    @EnumHandler(option = EnumDisplayOption.BUTTON)
    public Languages selectedLang = Languages.english;

    @CollapsibleObject()
    public MultiLangObject multiLanguageObject = new MultiLangObject();

    public static class MultiLangObject {
        @CollapsibleObject
        public boolean allowMultipleLanguages;
        @CollapsibleObject
        public boolean english;
        @CollapsibleObject
        public boolean big_english;
        @CollapsibleObject
        public boolean russian;
        @CollapsibleObject
        public boolean chinese;
        @CollapsibleObject
        public boolean portuguese;

        public MultiLangObject() {
            this(false, false, false, false, false, false);
        }

        public MultiLangObject(boolean allowMultipleLanguages, boolean english, boolean big_english, boolean russian, boolean chinese, boolean portuguese) {
            this.allowMultipleLanguages = allowMultipleLanguages;
            this.english = english;
            this.big_english = big_english;
            this.russian = russian;
            this.chinese = chinese;
            this.portuguese = portuguese;
        }
    }


}



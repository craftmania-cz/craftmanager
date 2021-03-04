package cz.wake.manager.utils;

public enum ServerType {

    SURVIVAL("Survival"),
    SKYBLOCK("Skyblock"),
    CREATIVE("Creative"),
    PRISON("Prison"),
    VANILLA("Vanilla"),
    SKYCLOUD("SkyCloud"),
    SKYGRID("SkyGrid"),
    HARDCORE_VANILLA("Hardcore Vanilla"),
    ANARCHY("Vanilla Anarchy"),
    UNKNOWN("Unknown");

    public final String name;

    ServerType(String name) {
        this.name = name;
    }

    public String getFormatedname() {
        return name;
    }
}

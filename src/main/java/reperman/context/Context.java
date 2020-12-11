package reperman.context;

public class Context {
    public static Long ACTIVE_SONG_ID = 0L;

    public static void setActiveSongId(Long activeSongId) {
        ACTIVE_SONG_ID = activeSongId;
    }

    public static Long getActiveSongId() {
        return ACTIVE_SONG_ID;
    }
}

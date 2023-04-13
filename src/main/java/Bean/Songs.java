package Bean;

public class Songs implements Comparable <Songs> {
    private int songId;
    private String song_name;
    private String album;
    private String genre;
    private String artist;
    private String duration;
    private String path;

    public Songs(int songId, String song_name, String album, String genre, String artist, String duration,String path) {
        this.songId = songId;
        this.song_name = song_name;
        this.album = album;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.path=path;

    }

    public Songs() {
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Bean.Songs{" +
                "songId=" + songId +
                ", song_name='" + song_name + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", path='" + path + '\'' +
                '}';
    }


    @Override
    public int compareTo(Songs o) {
        return this.song_name.compareTo(o.song_name);
    }
}


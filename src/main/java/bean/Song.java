package bean;

public class Song implements Comparable<Song> {
    private int songId;
    private String name;
    private String album;
    private String genre;
    private String artist;
    private String duration;
    private String path;

    public Song(int songId, String name, String album, String genre, String artist, String duration, String path) {
        this.songId = songId;
        this.name = name;
        this.album = album;
        this.genre = genre;
        this.artist = artist;
        this.duration = duration;
        this.path = path;

    }

    public Song() {
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Bean.Song{" + "songId=" + songId + ", song_name='" + name + '\'' + ", album='" + album + '\'' + ", genre='" + genre + '\'' + ", artist='" + artist + '\'' + ", duration='" + duration + '\'' + ", path='" + path + '\'' + '}';
    }


    @Override
    public int compareTo(Song o) {
        return this.name.compareTo(o.name);
    }
}


package music_shop.entities;

public class Composition {
    private long id;
    private String name;
    private int length;
    private long albumId;

    public Composition(long id, String name, int length, long albumId) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.albumId = albumId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long album_id) {
        albumId = album_id;
    }
}

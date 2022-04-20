package root.model;

import org.jetbrains.annotations.NotNull;
import root.model.SoundClip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album {
    protected String name;
    protected Album parent;
    protected List<Album> subAlbums;
    protected List<SoundClip> songs;


    public Album(@NotNull String name) {
        assert name != null;
        this.name = name;
        this.subAlbums = new ArrayList<>();
        this.songs = new ArrayList<>();
    }
    /**
     * Adds an album to this albums sub albums
     * @param album The album to add
     * @throws AssertionError Throws an AssertionsError if the passed in album is null */

    public void addAlbum(@NotNull Album album) {
        assert album != null;
        album.parent = this;
        this.subAlbums.add(album);
    }

    /**
     * Removes an album from this albums sub albums
     * @param album The album to remove
     * @throws AssertionError Throws an AssertionsError if the passed in album is null*/

    public void removeAlbum(@NotNull Album album) {
        assert album != null;
        album.parent = null;
        this.subAlbums.remove(album);
    }
    /**
     * @param album The album that this album might contain
     * @return Returns true if the album is part of this albums sub albums, otherwise false
     * @throws AssertionError Throws an AssertionsError if the passed in album is null*/
    public boolean containsAlbum(@NotNull Album album) {
        assert album != null;
        return this.subAlbums.contains(album);
    }

    /**
     * Adds a song to this albums songs
     * @param song The song to add
     * @throws AssertionError Throws an AssertionsError if the passed in song is null*/

    public void addSong(@NotNull SoundClip song) {
        assert song != null;
        this.songs.add(song);
    }
    /**
     * Removes a song from this albums songs
     * @param song The song to remove
     * @throws AssertionError Throws an AssertionsError if the passed in song is*/

    public void removeSong(@NotNull SoundClip song) {
        assert song != null;
        this.songs.remove(song);
    }
    /**
     * @param song The song that this album might contain
     * @return Returns true if the song is part of this albums songs, otherwise false
     * @throws AssertionError Throws an AssertionsError if the passed in song is null*/
    public boolean containsSong(@NotNull SoundClip song) {
        assert song != null;
        return this.songs.contains(song);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Album album = (Album) o;
        return name.equals(album.name) && Objects.equals(parent, album.parent) && subAlbums.equals(album.subAlbums) && songs.equals(album.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent, subAlbums, songs);
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", subAlbums=" + subAlbums +
                ", songs=" + songs +
                '}';
    }
}

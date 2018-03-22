package music_shop.dao;

import music_shop.entities.Composition;

import java.util.List;

public interface CompositionDAO {
    String ID = "id";
    String NAME = "name";
    String LENGTH = "length";
    String ALBUM_ID = "album_id";

    List<Composition> selectCompositions();
    void addComposition(Composition c);
    void deleteComposition(Long id);
    void updateComposition(Composition composition1, Composition composition2);
    List<Composition> selectSortedCompositions();
    String getAllColumnNames();
    String getAllTableNames();
}

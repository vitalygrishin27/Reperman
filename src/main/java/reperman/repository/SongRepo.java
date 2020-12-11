package reperman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reperman.entity.Song;

public interface SongRepo extends JpaRepository<Song, Long> {
}

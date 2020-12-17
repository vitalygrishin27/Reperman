package reperman.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String mainPicture;
    private String tonality;
    private Integer temp;
    private String measure;
    private String lyrics;
    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Part> parts = new ArrayList<>();

    public void addPart(Part part) {
        this.parts.add(part);
        part.setSong(this);
    }

    public void removePart(Part part) {
        this.parts.remove(part);
        part.setSong(null);
    }
}

package planner.entity;

import javax.persistence.*;

/**
 * Created by Anna Platash on 5/3/16.
 */
@Entity
@Table(name = "tag", schema = "public", catalog = "planner")
public class TagData {
    private Integer id;
    private String tag;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id")
    @SequenceGenerator(name = "tag_id", sequenceName = "tag_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = 50)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagData tagData = (TagData) o;

        if (id != null ? !id.equals(tagData.id) : tagData.id != null) return false;
        if (tag != null ? !tag.equals(tagData.tag) : tagData.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}

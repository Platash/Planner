package planner.entity;

import javax.persistence.*;

/**
 * Created by Anna Platash on 5/3/16.
 */
@Entity
@Table(name = "tag", schema = "public", catalog = "planner")
public class TagData {
    private String name;

    @Id
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagData tagData = (TagData) o;
        if (name != null ? !name.equals(tagData.name) : tagData.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 + (name != null ? name.hashCode() : 0);
        return result;
    }
}

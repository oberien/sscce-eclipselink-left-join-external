package foo.bar.leftjoin.entities;

import javax.persistence.*;

@Entity
@Table
public class Other {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public Other() {
    }

    public Other(String name) {
        this.name = name;
    }
}

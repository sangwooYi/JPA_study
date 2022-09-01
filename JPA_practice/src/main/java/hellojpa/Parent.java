package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "PARENT_ID")
    private Long Id;

    private String name;

    //@OneToMany, @ManyToMany는 기본 디폴트가 fetch = FetchType.LAZY 임
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)  // ALL, PERSIST, REMOVE, MERGE, REFRESH, DETACH 들이 있음
    private List<Child> childList = new ArrayList<>();

    public Parent() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void addChild(Child child) {
        childList.add(child);

        if (child.getParent() != this) {
            child.setParent(this);
        }
    }

}

package hellojpa;


import javax.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue
    @Column(name = "CHILD_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY) // @ManyToOne, @OneToOne은 디폴트가 EAGER 따라서 왠만하면 LAZY로
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public Child() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;   
        // 순환참조 방지
        if (!parent.getChildList().contains(this)) {
            parent.getChildList().add(this);
        }
    }
}

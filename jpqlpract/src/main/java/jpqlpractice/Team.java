package jpqlpractice;

import org.hibernate.dialect.Oracle12cDialect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity {

    @Column(length = 30)
    private String teamName;

    public Team() {

    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Member> getMembers() {
        return members;
    }

}

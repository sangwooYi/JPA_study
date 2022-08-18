package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    @Column(length = 20)
    private String teamName;

    public Long getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public Team() {

    }
    public Team(String teamName) {
        this.teamName = teamName;
    }

}

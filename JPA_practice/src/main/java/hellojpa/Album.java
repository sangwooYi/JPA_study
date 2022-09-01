package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// Inheritance 전략의 디폴트 값은 SINGLE_TABLE이다 주의!!
public class Album extends Item{

    private String artist;
    private String etc;


    public Album() {

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}

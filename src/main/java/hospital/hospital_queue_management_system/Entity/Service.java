package hospital.hospital_queue_management_system.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity 
@Table (name = "service")
public class Service {

    @Id 
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore 
    @ManyToMany(mappedBy ="services")
    private List<Nurse> nurses = new ArrayList<>();

    public Service() {

    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    
    }
    public void setName( String name) {
        this.name =name;
    }
    public List<Nurse> getNurses() {
        return nurses;
    }
    public void setNurses(List<Nurse>nurses) {
        this.nurses = nurses;
    }


}

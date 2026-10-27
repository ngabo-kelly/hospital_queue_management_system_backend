package hospital.hospital_queue_management_system.Entity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity 
@Table (name ="nurse")
public class Nurse {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String staffNumber;

    @ManyToOne 
    @JoinColumn (name="counter_id")
    private Counter counter;

    @ManyToMany 
    @JoinTable (
        name ="nurse_service",
        joinColumns = @JoinColumn(name ="nurse_id"),
        inverseJoinColumns = @JoinColumn(name ="service_id")

    )
    private List<Service> services = new ArrayList<>();
     
    public Nurse() {

    }
    public UUID getId() {
        return id;

    }
    public String getFirstName() {
        return firstName;
    }
public void setFirstName(String firstName) {
        this.firstName = firstName;


}
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName =lastName;
    }
    public String getStaffNumber() {
        return staffNumber;
    }
    public void setStaffNumber(String staffNumber){
        this.staffNumber = staffNumber;
    }
    public Counter getCounter() {
        return counter;
    }
    public void setCounter(Counter counter){
        this.counter = counter;
    }
    public List<Service>getServices(){
        return services;
    }
    public void setServices(List<Service>services){
        this.services =services;
    }



}

package hospital.hospital_queue_management_system.Entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity 
@Table (name ="outpatient")
public class Outpatient {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;

    @Column (unique =true)
    private String fileNumber;

    private String phone;
    private String insurance;

    public Outpatient() {

    }
    public UUID getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;

    }
    public void setFirstName(String FirstName) {
        this.firstName = FirstName;

    }
    public String getLastName() {
        return lastName;

    }
    public void setLastName(String LastName) {
        this.lastName = LastName;
    }
    public String getFileNumber() {
        return fileNumber;
    }
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }
    public String getPhone() {
        return phone; 
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getInsurance() {
        return insurance;
    }
    public void setInsurance(String insurance) {
        this.insurance =insurance;

    }
    



}

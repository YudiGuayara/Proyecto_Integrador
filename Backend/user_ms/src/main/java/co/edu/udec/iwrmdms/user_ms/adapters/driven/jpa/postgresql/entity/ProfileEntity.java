package co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.entity;

import co.edu.udec.iwrmdms.user_ms.domains.model.enums.AcademicDegree;
import co.edu.udec.iwrmdms.user_ms.domains.model.enums.DniType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor  
@Data
@Entity
@Table(name = "profiles") 
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId; 
    private DniType dniType;
    private String dniNumber;
    private String firstName;
    private String lastName;
    private String genre;
    private String email;
    private String phone;
    private String address;
    private AcademicDegree academicDegree;
    private String occupation;
} 
    
    @Enumerated(EnumType.STRING) // Añadir esto si es necesario
    private DniType dniType;
    
    private String dniNumber;
    private String firstName;
    private String lastName;
    private String genre; // Considera cambiar a "gender" para mayor claridad
    private String email;
    private String phone;
    private String address;

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public DniType getDniType() {
        return dniType;
    }

    public void setDniType(DniType dniType) {
        this.dniType = dniType;
    }

    public String getDniNumber() {
        return dniNumber;
    }

    public void setDniNumber(String dniNumber) {
        this.dniNumber = dniNumber;
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
        this.lastName = lastName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    
    @Enumerated(EnumType.STRING) // Añadir esto si es necesario
    private AcademicDegree academicDegree;
    
    private String occupation;
}


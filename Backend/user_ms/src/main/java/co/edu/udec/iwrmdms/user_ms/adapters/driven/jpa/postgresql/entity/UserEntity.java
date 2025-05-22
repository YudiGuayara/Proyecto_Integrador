package co.edu.udec.iwrmdms.user_ms.adapters.driven.jpa.postgresql.entity;

import co.edu.udec.iwrmdms.user_ms.domains.model.Profile;
import co.edu.udec.iwrmdms.user_ms.domains.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor  
@Data
@Entity
@Table(name = "users") 
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; 
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "id_profile")
    private ProfileEntity profile;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;
 

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    private RoleEntity role;

    private String username;
    private String password;

 
    // Getters y setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 
}

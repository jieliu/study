package com.tianma.api.domain.user;



import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable{



	public enum Status {
		ACTIVATED, BANNED, UNACTIVATED, UNVALIDATED
	}

    public enum Role{
        ROLE_USER,ROLE_ADMIN
    }


	private Long id;

	private String email;

	private String name;

	private String password;

	private Role role;

	private Status status;

	public User(Long userId) {
		this.id = userId;
	}

    public User(){}

    public User(String Name,String Password,Role rol){
        this.name=Name;
        this.password=Password;
        this.role=rol;
        this.status=Status.ACTIVATED;

    }

    public Long getId(){return id;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public Status getStatus(){return status;}
    public String getName(){return name;}
    public Role getRole(){return role;}
    public String getStringRole(){
        if(role!=Role.ROLE_ADMIN) return "ROLE_USER";
        return "ROLE_ADMIN";
    }


    public void setEmail(String e){email=e;}
    public void setName(String e){name=e;}
    public void setRole(Role i){role=i;}
    public void setPassword(String pass){password=pass;}
    public void setStatus(Status s){status=s;}

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}

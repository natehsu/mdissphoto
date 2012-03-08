package org.mdissjava.mdisscore.model.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="users")
public class User implements Serializable {
	
	public static enum Gender {Male,Female};
	
	@Id private int id;	
	private String nick;
	private String name;	
	private String surname;	
	private Gender gender;	
	private Date birthdate;	
	private int phone;
	private int avatar;
	private Date registeredDate;	
	private Date lastSession;
	private boolean active;	
	private String preferences;
	private String role;
	
	private String email;
	private String pass;


	@ManyToMany
	@JoinTable(name="friends",
	 joinColumns=@JoinColumn(name="userId"),
	 inverseJoinColumns=@JoinColumn(name="friendId")
	)
	private List<User> friends;
	
	@ManyToMany
	@JoinTable(name="friends",
	 joinColumns=@JoinColumn(name="friendId"),
	 inverseJoinColumns=@JoinColumn(name="userId")
	)
	private List<User> friendOf;
	

	@Embedded
	private Address address;
	@Embedded 
	private Configuration configuration;
	
	@Transient
	public List<Camera> cameras;
	//@Transient
	//public List<Album> albums;*/
	
	
	public User(){
		configuration = new Configuration();		
		registeredDate = new Date();
		lastSession = new Date();		
	}

		
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public int getPhone() {
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public int getAvatar() {
		return avatar;
	}
	
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	
	public Date getRegisteredDate() {
		return registeredDate;
	}
	
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Date getLastSession() {
		return lastSession;
	}
	
	public void setLastSession(Date lastSession) {
		this.lastSession = lastSession;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getPreferences() {
		return preferences;
	}
	
	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
	public List<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(List<User> friendsOf) {
		this.friendOf = friendsOf;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	public void addPreference(String preference){
		this.preferences += preference;	
	}			

	
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (id != user.id) return false;
        if (nick != null ? !nick.equals(user.nick) : user.nick != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;        
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (birthdate != null ? !birthdate.equals(user.birthdate) : user.birthdate != null) return false;
        if (phone != (user.phone)) return false;
        if (registeredDate != null ? !registeredDate.equals(user.registeredDate) : user.registeredDate != null) return false;
        if (lastSession != null ? !lastSession.equals(user.lastSession) : user.lastSession != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return true;
    }
	
	
	@Override
	public String toString(){
		String rest;
		rest = "Name:" + name +   " Surname:" + surname + "\n" +
			   "Birthdate:" + birthdate + " Phone:" + phone + "\n" +
			   "Avatar:" + avatar +  " RegisteredDate:" + registeredDate + "\n";

		return rest;
	}
	
}

package org.mdissjava.mdisscore.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;


@Entity
public class Album {

	
	@Id
	private ObjectId id;

	private String albumId;
	private String title;
	private Date creationDate;
	private String userNick;
	
	@JsonIgnore
	@Reference
	private List<Photo> photos;
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@JsonIgnore
	public List<Photo> getPhotos() {
		return photos;
	}
	
	@JsonIgnore
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	public void addPhotoToAlbum(Photo p) {
		if(this.photos == null){
			this.photos = new ArrayList<Photo>(); 
		}
		this.photos.add(p);
	}
	
}
	
	

	
package org.mdissjava.mdisscore.model.dao.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mdissjava.mdisscore.model.dao.AlbumDao;
import org.mdissjava.mdisscore.model.pojo.Album;
import org.mdissjava.mdisscore.model.pojo.Photo;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.mapping.Mapper;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.UpdateOperations;

public class AlbumDaoImpl extends BasicDAO<Album, ObjectId> implements
		AlbumDao {

	public AlbumDaoImpl(Datastore ds) {
		super(ds);
	}

	private Query<Album> queryToFindMe(ObjectId id) {
		return ds.createQuery(Album.class).field(Mapper.ID_KEY).equal(id);
	}

	@Override
	public void insertAlbum(Album album) {

		this.save(album);
	}

	@Override
	public List<Album> findAlbum(Album album) {

		Query<Album> query = ds.createQuery(Album.class);
		if (album.getId() != null) {
			query.field("id").equal(album.getId());
		}if (album.getAlbumId() != null) {
			query.field("albumId").equal(album.getAlbumId());
		}
		if (album.getTitle() != null) {
			query.field("title").equal(album.getTitle());
		}
		if (album.getCreationDate() != null) {
			query.field("creationDate").equal(album.getCreationDate());
		}
		if (album.getUserNick() != null) {
			query.field("userNick").equal(album.getUserNick());
		}
		if (album.getPhotos() != null) {
			query.field("photos").equal(album.getPhotos());
		}
		
		List<Album> albums = query.asList();

		return albums;

	}

	@Override
	public void updateAlbum(Album album) {
		UpdateOperations<Album> ops = ds.createUpdateOperations(Album.class);
		if (album.getAlbumId() != null) {
			ops.set("albumId", album.getAlbumId());
		}
		if (album.getTitle() != null) {
			ops.set("title", album.getTitle());
		}
		if (album.getCreationDate() !=null){
			ops.set("creationDate", album.getCreationDate());
		}
		if (album.getUserNick() != null){
			ops.set("userNick", album.getUserNick());
		}
		if (album.getPhotos() !=null){
			ops.set("photos", album.getPhotos());
		}
			
		ds.update(this.queryToFindMe(album.getId()), ops);
	}

	@Override
	public void deleteAlbum(Album album) {
		ds.delete(album);
	}

	@Override
	public int getTotalAlbums() {
		return ((Long)ds.createQuery(Album.class).countAll()).intValue();
	}

	@Override
	public List<Album> findUserAlbumsOffset(String userNick, int quantityNumberAlbums, int skipNumberAlbums) {
		List<Album> albums;
		Query<Album> query = ds.createQuery(Album.class);
		query.filter("userNick", userNick);
		query.offset(skipNumberAlbums); //skip the first X
		if (quantityNumberAlbums > 0) //If 0 then get all 
			query.limit(quantityNumberAlbums); //limit the number
		
		albums = query.asList();
		return albums;
	}

	@Override
	public int getTotalAlbumsUser(String userNick) {
		return ((Long)ds.createQuery(Album.class).disableValidation().filter("userNick", userNick).countAll()).intValue();
	}


}

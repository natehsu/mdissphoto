package org.mdissjava.api;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.HttpRequest;
import org.mdissjava.api.helpers.ApiHelper;
import org.mdissjava.commonutils.properties.PropertiesFacade;
import org.mdissjava.mdisscore.model.dao.AlbumDao;
import org.mdissjava.mdisscore.model.dao.factory.MorphiaDatastoreFactory;
import org.mdissjava.mdisscore.model.dao.impl.AlbumDaoImpl;
import org.mdissjava.mdisscore.model.pojo.Album;

import com.google.code.morphia.Datastore;

@Path("/albums")
public class Albums {
	
	//constants
	private final String GLOBAL_PROPS_KEY = "globals";
	private final String MORPHIA_DATABASE_KEY = "morphia.db";
	private Datastore datastore = null;
	private String userName = null;
	
	public Albums(@Context HttpRequest request) throws IllegalArgumentException, IOException {
		this.userName = ApiHelper.getUserFromHttpRequest(request);
		PropertiesFacade propertiesFacade = new PropertiesFacade();
		String database = propertiesFacade.getProperties(GLOBAL_PROPS_KEY).getProperty(MORPHIA_DATABASE_KEY);
		this.datastore = MorphiaDatastoreFactory.getDatastore(database);
	}
	
	@GET
	@Path("/{albumId}")
	@Produces("application/json")
	public Response getAlbum(@PathParam("albumId") String albumId){
		
		Album album = new Album();
		album.setAlbumId(albumId);
		
		AlbumDao albumDao = new AlbumDaoImpl(this.datastore);
		List<Album> albums = albumDao.findAlbum(album);
		System.out.println("yeah");
		if (albums.size() == 1)
			return Response.status(200).entity(albums.get(0)).build();
		else
			return Response.status(400).entity("Error retrieving album").build();
	}
	
	@POST
	@Consumes("application/json")
	public Response createAlbum(Album album){
		
		//Test: curl -i -X POST -H Accept:application/json -H Content-Type:application/json  -d '{"title":"Me","userNick":"horl"}' '127.0.0.1:8080/mdissapi/api/1.0/albums/'
		if (album != null){
			
			AlbumDao albumDao = new AlbumDaoImpl(this.datastore);
			
			//search if the title exists
			Album a = new Album();
			a.setTitle(album.getTitle());
			a.setUserNick(album.getUserNick());
			if (albumDao.findAlbum(a).size() > 0)
				return Response.status(200).entity("Album already exists").build();
			
			//save in database
			album.setAlbumId(UUID.randomUUID().toString());
			album.setCreationDate(new Date());
			albumDao.insertAlbum(album);
			return Response.status(400).entity(album).build();
			
		}else
			return Response.status(200).entity("Error creating album").build();
	}

}

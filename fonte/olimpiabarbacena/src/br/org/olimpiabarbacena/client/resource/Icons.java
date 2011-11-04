package br.org.olimpiabarbacena.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Icons extends ClientBundle {
	
	public static final Icons INSTANCE = GWT.create(Icons.class);

	@Source("pencil.png")
	ImageResource pencil();

	@Source("delete.png")
	ImageResource delete();

}

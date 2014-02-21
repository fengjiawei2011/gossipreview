package beans;

import java.io.Serializable;

public class GossipBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String movie_name;
	private String key_word;
	private String title ;
	private String date;
	private String content;
	private String image_add;
	private String gossip_url;
	private String image_url;
	private String movie_id;
	private String isInteresting;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage_add() {
		return image_add;
	}
	public void setImage_add(String image_add) {
		this.image_add = image_add;
	}
	public String getGossip_url() {
		return gossip_url;
	}
	public void setGossip_url(String gossip_url) {
		this.gossip_url = gossip_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	public String getIsInteresting() {
		return isInteresting;
	}
	public void setIsInteresting(String isInteresting) {
		this.isInteresting = isInteresting;
	}
	
	
	

}

package com.mendes.Labeedit.modules.posts.entities;

import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.AbstractEntitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.*;
@Entity
@Table(name = "post_tb")
public class PostEntitie  extends AbstractEntitie{
  

  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private AppUser user;

  public AppUser getUser() {
    return user;
  }
  @Column(name = "user_id", nullable = false)
  private UUID userId;

  public UUID getUserId() {
    return userId;
  }
  private String content;
  public String getContent() {
    return content;
  }
  private int likes;
  public void setLikes(int likes) {
    this.likes = likes;
  }
  public int getLikes() {
    return likes;
  }
  private int dislikes;

  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }
  public int getDislikes() {
    return dislikes;
  }
  public PostEntitie(){}
  public PostEntitie(UUID userId,String content){
    this.userId = userId;
    this.content = content;
    this.likes = 0;
    this.dislikes = 0;
  }



}

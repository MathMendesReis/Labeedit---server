package com.mendes.Labeedit.modules.posts.entities;

import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.AbstractEntitie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  private String content;
  private int likes;
  private int dislikes;

  public PostEntitie(){}
  public PostEntitie(UUID userId,String content){
    this.userId = userId;
    this.content = content;
    this.likes = 0;
    this.dislikes = 0;
  }



}
/* private userId: string,
		private userName: string,
		private contents: string,
		private creation_date: string,
		private information_update: string,
		private likes: number,
		private dislikes: number,
		private coments: number */
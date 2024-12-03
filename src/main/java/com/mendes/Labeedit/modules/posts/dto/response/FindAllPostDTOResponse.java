package com.mendes.Labeedit.modules.posts.dto.response;

import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import java.time.LocalDateTime;

public class FindAllPostDTOResponse {
  private String id;
  private String content;
  private int likes;
  private int dislikes;

  private String userId;
  private String userName;
  private String userEmail;
  private LocalDateTime createdDate;
  private LocalDateTime updateDate;


  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public LocalDateTime getUpdateDate() {
    return updateDate;
  }
  public String getId() {
    return id;
  }
  public String getContent() {
    return content;
  }
  public int getLikes() {
    return likes;
  }
  public int getDislikes() {
    return dislikes;
  }
  public String getUserId() {
    return userId;
  }
  public String getUserName() {
    return userName;
  }
  public String getUserEmail() {
    return userEmail;
  }
  public FindAllPostDTOResponse(){}
  public FindAllPostDTOResponse(PostEntitie postEntitie){
    this.id = postEntitie.getId().toString();
    this.content = postEntitie.getContent();
    this.likes = postEntitie.getLikes();
    this.dislikes = postEntitie.getDislikes();
    this.createdDate = postEntitie.getCreatedDate();
    this.updateDate = postEntitie.getUpdateDate();
    if (postEntitie.getUser() != null) {
      this.userId = postEntitie.getUser().getId().toString();
      this.userName = postEntitie.getUser().getName();
      this.userEmail = postEntitie.getUser().getEmail();
  }
  }
}

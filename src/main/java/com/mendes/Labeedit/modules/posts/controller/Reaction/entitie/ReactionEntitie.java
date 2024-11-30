package com.mendes.Labeedit.modules.posts.controller.Reaction.entitie;

import java.util.UUID;

import com.mendes.Labeedit.modules.posts.controller.Reaction.dto.RequestReactControllerDTo;
import com.mendes.Labeedit.modules.posts.entities.PostEntitie;
import com.mendes.Labeedit.modules.user.entities.AppUser;
import com.mendes.Labeedit.utils.AbstractEntitie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "react_tb")
public class ReactionEntitie extends AbstractEntitie {
  @Column(name = "react_value", nullable = false)
  private int reactValue;

  public void setReactValue(int reactValue) {
    this.reactValue = reactValue;
  }
  public int getReactValue() {
    return reactValue;
  }
  @ManyToOne
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private AppUser appUser;

  @Column(name = "user_id")
  private UUID userId;
  
  public UUID getUserId() {
    return userId;
  }
  @ManyToOne
  @JoinColumn(name = "post_id", insertable = false, updatable = false)
  private PostEntitie PostEntitie;

  @Column(name = "post_id")
  private UUID postId;

  public UUID getPostId() {
    return postId;
  }
  public ReactionEntitie(){}
  public ReactionEntitie(RequestReactControllerDTo requestReactControllerDTo, UUID useUuid){
    this.reactValue = requestReactControllerDTo.getReact();
    this.userId = useUuid;
    this.postId = requestReactControllerDTo.getPostId();
  }
}

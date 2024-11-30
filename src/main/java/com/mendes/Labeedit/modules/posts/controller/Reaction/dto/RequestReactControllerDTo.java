package com.mendes.Labeedit.modules.posts.controller.Reaction.dto;

import java.util.*;
public class RequestReactControllerDTo {
  private UUID postId;
  private int react;

  public UUID getPostId() {
    return postId;
  }

  public int getReact() {
    return react;
  }
  public RequestReactControllerDTo(){}
  public RequestReactControllerDTo(UUID postId,UUID userId,int react){
    this.postId = postId;
    this.react = react;
  }
}

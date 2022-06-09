package com.example.locationpet.dto;

import com.google.gson.annotations.SerializedName;

public class Recycler {

    public static class Item {
        @SerializedName("create_at")
        private String create_at;
        @SerializedName("update_at")
        private String update_at;
        @SerializedName("postId")
        private long postId;
        @SerializedName("userUid")
        private long userUid;
        @SerializedName("postDesc")
        private String postDesc;
        @SerializedName("postImage")
        private String postImage;
        @SerializedName("postComment")
        private int postComment;
        @SerializedName("postLike")
        private int postLike;

        public Item(String create_at, String update_at, long postId, long userUid, String postDesc, String postImage, int postComment, int postLike) {
            this.create_at = create_at;
            this.update_at = update_at;
            this.postId = postId;
            this.userUid = userUid;
            this.postDesc = postDesc;
            this.postImage = postImage;
            this.postComment = postComment;
            this.postLike = postLike;
        }
    }

    public static class Request {
        @SerializedName("create_at")
        private String create_at;
        @SerializedName("update_at")
        private String update_at;
        @SerializedName("postId")
        private long postId;
        @SerializedName("userUid")
        private long userUid;
        @SerializedName("postDesc")
        private String postDesc;
        @SerializedName("postImage")
        private String postImage;
        @SerializedName("postComment")
        private int postComment;
        @SerializedName("postLike")
        private int postLike;

        public Request(String create_at, String update_at, long postId, long userUid, String postDesc, String postImage, int postComment, int postLike) {
            this.create_at = create_at;
            this.update_at = update_at;
            this.postId = postId;
            this.userUid = userUid;
            this.postDesc = postDesc;
            this.postImage = postImage;
            this.postComment = postComment;
            this.postLike = postLike;
        }
    }

    public static class Response {
        @SerializedName("create_at")
        private String create_at;
        @SerializedName("update_at")
        private String update_at;
        @SerializedName("postId")
        private long postId;
        @SerializedName("userUid")
        private long userUid;
        @SerializedName("postDesc")
        private String postDesc;
        @SerializedName("postImage")
        private String postImage;
        @SerializedName("postComment")
        private int postComment;
        @SerializedName("postLike")
        private int postLike;


        public Response(String create_at, String update_at, long postId, long userUid, String postDesc, String postImage, int postComment, int postLike) {
            this.create_at = create_at;
            this.update_at = update_at;
            this.postId = postId;
            this.userUid = userUid;
            this.postDesc = postDesc;
            this.postImage = postImage;
            this.postComment = postComment;
            this.postLike = postLike;
        }

        public String getUpdate_at() { return this.update_at; }
        public String getCreate_at() { return this.create_at; }
        public long getUserUid() { return this.userUid; }
        public long getPostId() { return this.postId; }
        public String getPostDesc() { return this.postDesc; }
        public String getPostImage() { return this.postImage; }
        public int getPostComment() { return this.postComment; }
        public int getPostLike() { return this.postLike; }
    }
}

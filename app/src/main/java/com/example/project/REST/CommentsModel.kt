package com.example.project.REST

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CommentsModel(
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("idPost")
    @Expose
    val idPost: Int? = null,
    @SerializedName("num")
    @Expose
    val num: String? = null,
    @SerializedName("likes")
    @Expose
    var likes: String? = null,
    @SerializedName("data")
    @Expose
    val data: String? = null,
    @SerializedName("userId")
    @Expose
    val userId: String? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null,
    @SerializedName("replies")
    @Expose
    var replies: ArrayList<ReplyCommentModel>? = null
) {
    var comment: Comment?  = null
}
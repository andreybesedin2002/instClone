package com.example.project.REST

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReplyCommentModel (
    @SerializedName("id")
    @Expose
    val id: Int? = null,
    @SerializedName("idComment")
    @Expose
    val idComment: Int? = null,
    @SerializedName("num")
    @Expose
    val num: String? = null,
    @SerializedName("likes")
    @Expose
    var likes: String? = null,
    @SerializedName("data")
    @Expose
    val time: String? = null,
    @SerializedName("userId")
    @Expose
    val userId: String? = null,
    @SerializedName("text")
    @Expose
    val text: String? = null

) {
    var comment: Comment? = null
}
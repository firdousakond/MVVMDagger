package com.firdous.mvvmdagger.ui.post

import androidx.lifecycle.MutableLiveData
import com.firdous.mvvmdagger.base.BaseViewModel
import com.firdous.mvvmdagger.model.Post

class PostViewModel : BaseViewModel(){

    private val title =  MutableLiveData<String>()
    private val body =  MutableLiveData<String>()

    fun bind (post: Post){
        title.value = post.title
        body.value = post.body
    }

    fun getPostTitle() : MutableLiveData<String>{
        return title
    }

    fun getPostBody() : MutableLiveData<String>{
        return body
    }
}
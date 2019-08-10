package com.firdous.mvvmdagger.ui.post

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.firdous.mvvmdagger.R
import com.firdous.mvvmdagger.base.BaseViewModel
import com.firdous.mvvmdagger.model.Post
import com.firdous.mvvmdagger.network.PostApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel : BaseViewModel(){

     var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
     var errorMessage : MutableLiveData<Int> = MutableLiveData()
     var errorClickListener  = View.OnClickListener { loadPosts() }
     val postListAdapter: PostListAdapter = PostListAdapter()


    @Inject
    lateinit var postApi: PostApi
    lateinit var subscription : Disposable

    init {
        loadPosts()
    }

    private fun loadPosts(){

        subscription = postApi.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postResult: List<Post>) {
        postListAdapter.updatePostList(postResult)
    }

    private fun onRetrievePostListError(){

        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}
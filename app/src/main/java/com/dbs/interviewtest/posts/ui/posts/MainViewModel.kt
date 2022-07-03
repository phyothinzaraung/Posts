package com.dbs.interviewtest.posts.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dbs.interviewtest.posts.data.model.Post
import com.dbs.interviewtest.posts.data.repository.MainRepository
import com.dbs.interviewtest.posts.utils.NetworkHelper
import com.dbs.interviewtest.posts.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {

    private val _post = MutableLiveData<Resource<List<Post>>>()
    val posts: LiveData<Resource<List<Post>>>
    get() = _post

    init {
        fetchPosts()
    }

    private fun fetchPosts(){
        viewModelScope.launch() {
            _post.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()){
                mainRepository.getPosts().let {
                    if(it.isSuccessful){
                        _post.postValue(Resource.success(it.body()))
                    }else _post.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }else _post.postValue(Resource.error("No Internet Connection", null))
        }
    }
}
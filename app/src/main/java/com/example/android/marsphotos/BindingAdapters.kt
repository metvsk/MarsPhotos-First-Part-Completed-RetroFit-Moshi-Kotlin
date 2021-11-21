package com.example.android.marsphotos

import PhotoGridAdapter
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.marsphotos.network.MarsPhoto
import com.example.android.marsphotos.overview.OverviewViewModel

//todo 20 setting up the url imageView loader for displaying the image
@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imageUrl: String?) {
    imageUrl?.let {
        val imgUri = imageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri) {
            //todo 21 adding place holder image to show while loading or in the event of error
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MarsPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView:ImageView,status:OverviewViewModel.MarsApiStatus?){
    when(status){
        OverviewViewModel.MarsApiStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        OverviewViewModel.MarsApiStatus.ERROR->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        OverviewViewModel.MarsApiStatus.DONE->{
            statusImageView.visibility=View.GONE
        }
    }
}
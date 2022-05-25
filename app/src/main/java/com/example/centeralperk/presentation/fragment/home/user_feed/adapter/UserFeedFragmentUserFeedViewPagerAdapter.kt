package com.example.centeralperk.presentation.fragment.home.user_feed.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.centeralperk.R
import com.example.centeralperk.databinding.UserFeedViewPagerAdapterBinding
import com.example.centeralperk.domain.model.Image
import com.example.centeralperk.util.AppConstant

class UserFeedFragmentUserFeedViewPagerAdapter(
    private val images: ArrayList<Image>,
    private val context: Context
) :
    RecyclerView.Adapter<UserFeedFragmentUserFeedViewPagerAdapter.ViewPagerHolder>() {

    /** Holder of viewPager
     * @param view
     * */
    class ViewPagerHolder(view: UserFeedViewPagerAdapterBinding) :
        RecyclerView.ViewHolder(view.root) {

        val userFeedImage = view.ivUserFeed
    }

    /** ViewPager onCreate
     * @param parent
     * @param viewType
     * @return Holder
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view: UserFeedViewPagerAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_feed_view_pager_adapter,
            parent,
            false
        )
        return ViewPagerHolder(view)
    }

    /** Setting element when viewPager is bind with view */
    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {

        /** Setting the images in viewPager */
        Glide.with(context).load("${AppConstant.BASE_IMAGE_URL}${images[position].image}").into(holder.userFeedImage)
    }

    /**
     *  Recycler view item count
     *  @return itemCount
     *  */
    override fun getItemCount(): Int {
        return images.size
    }
}
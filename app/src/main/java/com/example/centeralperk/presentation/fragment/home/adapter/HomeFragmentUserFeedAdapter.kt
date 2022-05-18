package com.example.centeralperk.presentation.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.centeralperk.R
import com.example.centeralperk.databinding.UserFeedRecyclerViewTemplateBinding
import com.example.centeralperk.domain.model.Image
import com.example.centeralperk.domain.model.ResultX
import com.example.centeralperk.util.AppConstant
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragmentUserFeedAdapter(
    private val userFeedList: ArrayList<ResultX>,
    private val context: Context
) :
    RecyclerView.Adapter<HomeFragmentUserFeedAdapter.Holder>() {

    /** Holder of recycler view
     * @param view
     * */
    class Holder(view: UserFeedRecyclerViewTemplateBinding) : RecyclerView.ViewHolder(view.root) {

        val userProfileImage = view.ivUserProfile
        val userProfileName = view.tvUserName
        val userFeedViewPager = view.viewPager
        val userContent = view.tvContent
        val viewPagerDots = view.viewPagerDots
    }

    /** Recycler view onCreate
     * @param parent
     * @param viewType
     * @return Holder
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: UserFeedRecyclerViewTemplateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_feed_recycler_view_template,
            parent,
            false
        )
        return Holder(view)
    }

    /** Setting element when recycler view is bind with view */
    override fun onBindViewHolder(holder: Holder, position: Int) {

        val feed = userFeedList[position]

        /** UserProfile image set */
        Glide.with(context).load("${AppConstant.BASE_IMAGE_URL}${feed.image}").circleCrop()
            .into(holder.userProfileImage)

        /** Passing the Images array to viewPager adapter if feed image is not null or empty */
        if (feed.images.isNullOrEmpty()) {
            holder.userFeedViewPager.visibility = GONE
        } else {
            holder.userFeedViewPager.visibility = VISIBLE

            val imagesList: ArrayList<Image> = arrayListOf()

            feed.images.let { image ->
                image.forEach { feedImage ->
                    imagesList.add(feedImage)
                }
            }
            holder.userFeedViewPager.adapter =
                HomeFragmentUserFeedViewPagerAdapter(imagesList, context)

            if (imagesList.size > 1) {
                holder.viewPagerDots.visibility = VISIBLE

                /** Showing the dots */
                TabLayoutMediator(holder.viewPagerDots, holder.userFeedViewPager) { _, _ ->
                }.attach()
            }

        }

        /** UserProfile name */
        holder.userProfileName.text = feed.username

        /** UserFeed Content */
        holder.userContent.text = feed.content
    }

    /**
     *  Recycler view item count
     *  @return itemCount
     *  */
    override fun getItemCount(): Int {
        return userFeedList.size
    }
}
package com.example.centeralperk.presentation.fragment.home.user_feed.adapter

import android.annotation.SuppressLint
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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class UserFeedFragmentUserFeedAdapter(
    private val userFeedList: ArrayList<ResultX>,
    private val context: Context
) :
    RecyclerView.Adapter<UserFeedFragmentUserFeedAdapter.Holder>() {

    /** Holder of recycler view
     * @param view
     * */
    class Holder(view: UserFeedRecyclerViewTemplateBinding) : RecyclerView.ViewHolder(view.root) {

        val userProfileImage = view.ivUserProfile
        val userProfileName = view.tvUserName
        val userFeedViewPager = view.viewPager
        val userContent = view.tvContent
        val tabLayout = view.viewPagerDots
        val pageNumber = view.tvPageNumber
        val pageNumberCardView = view.cvPageNumber
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
    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {

        val feed = userFeedList[position]

        /** UserProfile image set */
        Glide.with(context).load("${AppConstant.BASE_IMAGE_URL}${feed.image}").circleCrop()
            .into(holder.userProfileImage)

        /** UserProfile name */
        holder.userProfileName.text = feed.username

        /** UserFeed Content */
        holder.userContent.text = feed.content

        /** Passing the Images array to viewPager adapter if feed image is not null or empty */
        if (feed.images?.size == 0 || feed.images.isNullOrEmpty()) {

            /** ViewPager visibility*/
            holder.userFeedViewPager.visibility = GONE

            /** ViewPager dots visibility*/
            holder.tabLayout.visibility = GONE

            /** PageNumber cardView visibility*/
            holder.pageNumberCardView.visibility = GONE

        } else {

            /** ViewPager visibility*/
            holder.userFeedViewPager.visibility = VISIBLE

            val imagesList: ArrayList<Image> = arrayListOf()

            /** Clearing the image list */
            imagesList.clear()

            /** Adding images in userFeedViewPager list */
            feed.images.let { image ->
                image.forEach { feedImage ->
                    imagesList.add(feedImage)
                }
            }

            /** ViewPager2 adapter */
            holder.userFeedViewPager.adapter =
                UserFeedFragmentUserFeedViewPagerAdapter(imagesList, context)

            /** Showing and hiding the dot indicator */
            if (imagesList.size <= 1 || imagesList.isNullOrEmpty()) {

                /** ViewPager dots visibility*/
                holder.tabLayout.visibility = GONE

                /** PageNumber cardView visibility*/
                holder.pageNumberCardView.visibility = GONE

            } else {

                /** ViewPager dots visibility*/
                holder.tabLayout.visibility = VISIBLE

                /** PageNumber cardView visibility*/
                holder.pageNumberCardView.visibility = VISIBLE

                /** Showing the dots */
                TabLayoutMediator(holder.tabLayout, holder.userFeedViewPager) { _, _ ->
                }.attach()

                /** default pageNumber */
                var tabCurrentPosition = "${1}/${holder.tabLayout.tabCount}"
                holder.pageNumber.text = tabCurrentPosition

                /** TabLayout tabSelected listener */
                holder.tabLayout.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {

                        /** Current ViewPager pageNumber */
                        tabCurrentPosition = "${tab?.position?.plus(1)}/${holder.tabLayout.tabCount}"
                        holder.pageNumber.text = tabCurrentPosition
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {}

                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                })
            }
        }
    }

    /**
     *  Recycler view item count
     *  @return itemCount
     *  */
    override fun getItemCount(): Int {
        return userFeedList.size
    }
}
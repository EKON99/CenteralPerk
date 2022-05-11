package com.example.centeralperk.presentation.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.centeralperk.R
import com.example.centeralperk.databinding.UserFeedRecyclerViewTemplateBinding

class HomeFragmentUserFeedAdapter : RecyclerView.Adapter<HomeFragmentUserFeedAdapter.Holder>() {

    class Holder(view: UserFeedRecyclerViewTemplateBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: UserFeedRecyclerViewTemplateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_feed_recycler_view_template,
            parent,
            false
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}
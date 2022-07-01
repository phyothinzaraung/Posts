package com.dbs.interviewtest.posts.ui.posts

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dbs.interviewtest.posts.R
import com.dbs.interviewtest.posts.data.model.Post
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter(
    private val posts: ArrayList<Post>):
    RecyclerView.Adapter<MainAdapter.MainViewHolder>(),
    Filterable {

    private var filterPostList = arrayListOf<Post>()
    private var randomNumbers = arrayListOf<String>()

    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            @SuppressLint("SetTextI18n")
            fun bind(post: Post, randomNumber: String){
                itemView.txtID.text = post.id.toString() + ": "
                itemView.txtTitle.text = post.title
                //var randomNumber = (10000000000..90000000000).random()
                val bodyText = post.body + "<b>" +  " - <br> $randomNumber </br>" + "</b>"
                itemView.txtBody.text = Html.fromHtml(bodyText)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(filterPostList[position], randomNumbers[position])
    }

    override fun getItemCount(): Int = filterPostList.size

    fun addData(postList: List<Post>, randomNumberList: List<String>){
        posts.addAll(postList)
        filterPostList.addAll(postList)
        randomNumbers.addAll(randomNumberList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filterPostList = posts
                } else {
                    val filteredList = arrayListOf<Post>()
                    for (post in posts) {
                        if (post.body.lowercase(Locale.ROOT).contains(
                                charString.lowercase(Locale.ROOT)
                            )
                        ) {
                            filteredList.add(post)
                        }
                    }
                    filterPostList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterPostList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterPostList = filterResults.values as ArrayList<Post>
                notifyDataSetChanged()
            }
        }
    }
}
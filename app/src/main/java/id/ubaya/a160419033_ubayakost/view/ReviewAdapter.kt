package id.ubaya.a160419033_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.ReviewListItemBinding
import id.ubaya.a160419033_ubayakost.model.Review
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewAdapter (val reviewList: ArrayList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: ReviewListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ReviewListItemBinding.inflate(inflater, parent, false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.view.review = reviewList[position]
    }

    override fun getItemCount() = reviewList.size

    fun updateReviewList(newReviewList: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}
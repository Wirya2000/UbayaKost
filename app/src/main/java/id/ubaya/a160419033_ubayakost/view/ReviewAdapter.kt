package id.ubaya.a160419033_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.model.Review
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewAdapter (val reviewList: ArrayList<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        with(holder.view) {
            textReviewName.text = review.name
            textReview.text = review.review
        }
    }

    override fun getItemCount() = reviewList.size

    fun updateReviewList(newReviewList: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}
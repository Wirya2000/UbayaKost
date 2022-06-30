package id.ubaya.a160419033_ubayakost.view

import android.net.ipsec.ike.TunnelModeChildSessionParams
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.databinding.QuestionListItemBinding
import id.ubaya.a160419033_ubayakost.model.FAQ
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.model.Question

class QuestionDetailAdapter (val questionList: ArrayList<Question>):
RecyclerView.Adapter<QuestionDetailAdapter.QuestionViewHolder>() {
    class QuestionViewHolder(var view: QuestionListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = QuestionListItemBinding.inflate(inflater, parent, false)

        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.view.question = questionList[position]
    }

    fun updateQuestionList(newQuestionList: List<Question>) {
        questionList.clear()
        questionList.addAll(newQuestionList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return questionList.size
    }
}
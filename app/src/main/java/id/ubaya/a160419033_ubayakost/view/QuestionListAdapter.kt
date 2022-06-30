package id.ubaya.a160419033_ubayakost.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.FaqListItemBinding
import id.ubaya.a160419033_ubayakost.model.FAQ
import kotlinx.android.synthetic.main.faq_list_item.view.*

class QuestionListAdapter(val faqList: ArrayList<FAQ>) :
    RecyclerView.Adapter<QuestionListAdapter.FAQViewHolder>(), FaqItemClickListener {
    class FAQViewHolder(var view: FaqListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = FaqListItemBinding.inflate(inflater, parent, false)

        return FAQViewHolder(view)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        holder.view.faq = faqList[position]
        holder.view.listener = this
//        val faq = faqList[position]
//        with(holder.view) {
//            textTopic.text = faq.topic
//            cardFAQ.setOnClickListener {
//                val action = QuestionListFragmentDirections.actionQuestionDetail(faq.id)
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    override fun getItemCount() = faqList.size

    fun updateFAQList(newFAQList: List<FAQ>) {
        faqList.clear()
        faqList.addAll(newFAQList)
        notifyDataSetChanged()
    }

    override fun onItemClick(v: View) {
        val faqId = v.tag.toString().toInt()
        val action = QuestionListFragmentDirections.actionQuestionDetail(faqId)
        Navigation.findNavController(v).navigate(action)
    }
}
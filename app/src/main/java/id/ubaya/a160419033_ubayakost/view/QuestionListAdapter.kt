package id.ubaya.a160419033_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.model.FAQ
import kotlinx.android.synthetic.main.faq_list_item.view.*

class QuestionListAdapter(val faqList: ArrayList<FAQ>) : RecyclerView.Adapter<QuestionListAdapter.FAQViewHolder>() {
    class FAQViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.faq_list_item, parent, false)
        return FAQViewHolder(view)
    }

    override fun onBindViewHolder(holder: FAQViewHolder, position: Int) {
        val faq = faqList[position]
        with(holder.view) {
            textTopic.text = faq.topic
            cardFAQ.setOnClickListener {
                val action = QuestionListFragmentDirections.actionQuestionDetail(faq.id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = faqList.size

    fun updateFAQList(newFAQList: ArrayList<FAQ>) {
        faqList.clear()
        faqList.addAll(newFAQList)
        notifyDataSetChanged()
    }
}
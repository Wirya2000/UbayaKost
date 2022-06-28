package id.ubaya.a160419033_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.databinding.KostListItemBinding
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*

class KostListAdapter(val kostList: ArrayList<Kost>) : RecyclerView.Adapter<KostListAdapter.KostViewHolder>(),
    KostItemClickListener {
    class KostViewHolder(var view: KostListItemBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = KostListItemBinding.inflate(inflater, parent, false)

        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        holder.view.kost = kostList[position]
        holder.view.listener = this

//        with(holder.view) {
//            textName.text = kost.name
//            textGender.text = kost.gender
//            textRegion.text = kost.region
//            textPrice.text = "Rp ${kost.price}"
//            imageKost.loadImage(kost.photoUrl, progressLoadingKostImage)
//            cardKost.setOnClickListener {
//                val action = KostListFragmentDirections.actionItemExploreToKostDetailFragment(kost.id)
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }

    override fun onItemClick(v: View) {
        val kostId = v.tag.toString().toInt()
        val action = KostListFragmentDirections.actionItemExploreToKostDetailFragment(kostId)
        Navigation.findNavController(v).navigate(action)
    }
}
package id.ubaya.a160419033_ubayakost.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ubaya.a160419033_ubayakost.R
import id.ubaya.a160419033_ubayakost.model.Kost
import id.ubaya.a160419033_ubayakost.util.loadImage
import kotlinx.android.synthetic.main.kost_list_item.view.*

class KostListAdapter(val kostList: ArrayList<Kost>) : RecyclerView.Adapter<KostListAdapter.KostViewHolder>() {
    class KostViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.kost_list_item, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        val kost = kostList[position]
        with(holder.view) {
            textName.text = kost.name
            textGender.text = kost.gender
            textRegion.text = kost.region
            textPrice.text = "Rp ${kost.price}"
            imageKost.loadImage(kost.photoUrl, progressLoadingKostImage)
            cardKost.setOnClickListener {
                val action = KostListFragmentDirections.actionItemExploreToKostDetailFragment(kost.id)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = kostList.size

    fun updateKostList(newKostList: ArrayList<Kost>) {
        kostList.clear()
        kostList.addAll(newKostList)
        notifyDataSetChanged()
    }
}
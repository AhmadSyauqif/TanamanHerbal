package com.example.tanamanherbal.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tanamanherbal.R
import com.example.tanamanherbal.activities.DetailActivity
import com.example.tanamanherbal.activities.MainActivity
import com.example.tanamanherbal.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*
import java.util.ArrayList

class MainAdapter(private val context: Context, private val modelMainList: MutableList<ModelMain>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    private val modelMainFilterList: List<ModelMain>
        get() {
            TODO()
        }

    override fun getFilter(): Filter {
        return modelFilter
    }

    private val modelFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ModelMain> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(modelMainFilterList)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (modelMainFilter in modelMainFilterList) {
                    if (modelMainFilter.nama.toLowerCase().contains(filterPattern)) {
                        filteredList.add(modelMainFilter)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            modelMainList.clear()
            val addAll = modelMainList.addAll(results.values as List<*>)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = modelMainList[position]
        holder.tvNamaTanaman.text = item.nama

        //send data to detail activity
        holder.cvListTanaman.setOnClickListener { view: View? ->
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.DETAIL_TANAMAN, modelMainList[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelMainList.size
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListTanaman: CardView
        var tvNamaTanaman: TextView

        init {
            cvListTanaman = itemView.cvListTanaman
            tvNamaTanaman = itemView.tvNamaTanaman
        }
    }

}





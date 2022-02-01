package com.example.newsapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
class newsListAdapter(private val listener:NewsItemclicked): RecyclerView.Adapter<newsViewHolder>() {

    private val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=newsViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val currItem=items[position]
        holder.titleView.text=currItem.title
        holder.author.text=currItem.author
        Glide.with(holder.itemView.context).load(currItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(updatedNews:ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)


        notifyDataSetChanged()
    }


}
class newsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView =itemView.findViewById(R.id.title)
    val image: ImageView=itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)

}
interface NewsItemclicked{
    fun onItemClicked(item:News)
}
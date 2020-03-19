package com.example.tchattest.Manager

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tchattest.R
import com.example.tchattest.dataClasses.TchatMessage
import com.eyalbira.loadingdots.LoadingDots

class TchatMessageAdapter(var tchatMessages: ArrayList<TchatMessage>?, val context: Context) : RecyclerView.Adapter<TchatMessageAdapter.ViewHolder>() {
    val YOURS: Int = 1
    val OTHERS: Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        tchatMessages?.get(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutResource = 0
        if (viewType == YOURS) {
            layoutResource = R.layout.your_message_item_row
        } else {
            layoutResource = R.layout.other_message_item_row
        }
        var view: View = LayoutInflater.from(context).inflate(layoutResource, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val itemViewType: Int?
        if (tchatMessages?.get(position)?.sender?.name.equals("you")) {
            itemViewType = YOURS
        } else {
            itemViewType = OTHERS
        }
        return itemViewType
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        if (tchatMessages != null) {
            return tchatMessages!!.size
        }
        return 0
    }

    fun addItem(newTchatMessage: TchatMessage) {
        if (this.tchatMessages == null) {
            this.tchatMessages = ArrayList<TchatMessage>()
        }
        this.tchatMessages?.add(newTchatMessage)
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Holds the TextView that will add each animal to
        var message: TextView = view.findViewById(R.id.message_text)
        var messageLoadingdots: LoadingDots? = null
        var sender: TextView? = view.findViewById(R.id.name)

        fun bind(tchatMessage: TchatMessage) {
            message.text = tchatMessage.message
            sender?.text = tchatMessage.sender.name
            (message.getBackground() as GradientDrawable).setColor(tchatMessage.sender.color)
            if (!tchatMessage.sender.name.equals("you")) {
                messageLoadingdots = this.itemView.findViewById(R.id.message_text_loading_dots)
                (messageLoadingdots!!.getBackground() as GradientDrawable).setColor(tchatMessage.sender.color)
            }

            val color = tchatMessage.sender.messageTextColor
            if (color != null) {
                message.setTextColor(color!!)
            }
        }
    }
}

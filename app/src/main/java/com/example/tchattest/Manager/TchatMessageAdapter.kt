package com.example.tchattest.Manager

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tchattest.R
import com.example.tchattest.dataClasses.TchatMessage
import com.eyalbira.loadingdots.LoadingDots

class TchatMessageAdapter :
    RecyclerView.Adapter<TchatMessageAdapter.ViewHolder>() {
    val YOURS: Int = 1
    val OTHERS: Int = 2

    private val tchatMessages = mutableListOf<TchatMessage>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tchatMessages[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutResource = if (viewType == YOURS) {
            R.layout.your_message_item_row
        } else {
            R.layout.other_message_item_row
        }
        val view: View = LayoutInflater.from(parent.context).inflate(layoutResource, parent, false)

        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (tchatMessages[position].sender.name == "you") {
            YOURS
        } else {
            OTHERS
        }
    }

    override fun getItemCount(): Int {
        return tchatMessages.size
    }

    fun addItems(newMessages: List<TchatMessage>) {
        tchatMessages.addAll(newMessages)
        notifyDataSetChanged()
    }

    fun addItem(newTchatMessage: TchatMessage) {
        this.tchatMessages.add(newTchatMessage)
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

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
                message.setTextColor(color)
            }
        }
    }
}

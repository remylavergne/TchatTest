package com.example.tchattest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tchattest.Manager.TchatMessageAdapter
import com.example.tchattest.dataClasses.TchatMessage
import com.example.tchattest.dataClasses.User

class MainActivity : AppCompatActivity() {
    var marc: User? = null
    var zoe: User? = null
    var you: User? = null
    var messageRecyclerView: RecyclerView? = null
    var itemAdapter: TchatMessageAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        marc = User("Marc", resources.getColor(R.color.gray))
        zoe = User("Zoé", resources.getColor(R.color.colorAccent))
        you = User("you", resources.getColor(R.color.colorPrimaryDark))
        setRecyclerView()
            populateTchat()
    }

    private fun setRecyclerView() {
        messageRecyclerView = findViewById(R.id.message_recycler_view)
        messageRecyclerView?.layoutManager = LinearLayoutManager(this)
        itemAdapter = TchatMessageAdapter(null, this)
        messageRecyclerView?.adapter = itemAdapter
    }

    private fun populateTchat() {

        val messages = ArrayList<TchatMessage>()
        messages.add(TchatMessage("Holla !", you!!))
        messages.add(TchatMessage("Hey ! :)", marc!!))
        messages.add(TchatMessage("Coucou les zigottos :D", zoe!!))
        messages.add(TchatMessage(":o", you!!))
        messages.add(TchatMessage("Quoiiii ? :D", zoe!!))

        for (message in messages) {
            print("insert")
            itemAdapter!!.addItem(message)

            val initialTime = System.currentTimeMillis()
            var now = System.currentTimeMillis()
            while (now - initialTime < message.timeBeforeNextMessage) {
                print("waiting...")
                now = System.currentTimeMillis()
            }
        }
    }
}

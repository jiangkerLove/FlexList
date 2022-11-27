package cn.jiangker.flexlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import cn.jiangker.flexlist.databinding.ActivityMainBinding
import cn.jiangker.lib.flexlist.FlexAdapter

class MainActivity : AppCompatActivity() {

    private val adapter = FlexAdapter(ItemOneHolder::class.java, ItemTwoHolder::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = adapter
        adapter.update(
            List(50) {
                if (it % 2 == 0)
                    ItemOne("ItemOne$it")
                else
                    ItemTwo("ItemLeft$it", "ItemRight$it")
            }
        )
        adapter.injectFunc(object : ItemOneClick {
            override fun click(itemOne: ItemOne) {
                Toast.makeText(
                    this@MainActivity,
                    itemOne.item,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        adapter.injectFunc(ItemTwoClick {
            Toast.makeText(
                this@MainActivity,
                "${it.item1} & ${it.item2}",
                Toast.LENGTH_SHORT
            ).show()
        })
    }
}
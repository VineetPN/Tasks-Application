package com.vineet.tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Dao
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.vineet.tasks.databinding.ActivityMainBinding
import com.vineet.tasks.databinding.DialogAddTaskBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var recyclerView : RecyclerView? = null
    private var isStarred = false
    private var taskFragment: TasksFragment = TasksFragment()
    companion object{
        private var count = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = GetItDoneDatabase.GetDatabase(this)
        val dao = database.GetTaskDao()
        thread {
            val taskList: List<Task> = dao.GetAllDetails()
            runOnUiThread(){
                val recyclerViewAdapter = RecyclerViewTasksAdapter(taskList)
                recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView?.adapter = recyclerViewAdapter
                recyclerView?.layoutManager = LinearLayoutManager(this)
            }
        }
        binding.pager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = "Tasks"
        }.attach()


        binding.fab.setOnClickListener() {
            val database = GetItDoneDatabase.GetDatabase(this)
            val dao = database.GetTaskDao()
            val runnable = object : Runnable{
                override fun run() {
                    dao.DeleteTask(Task(title = "CreateDatabase"))

                }
            }
            Thread(runnable).start()
            val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(dialogBinding.root)
            dialogBinding.icNotes.setOnClickListener(){
                dialogBinding.addNewTaskDescription.visibility = if(dialogBinding.addNewTaskDescription.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }

            dialogBinding.icStar.setOnClickListener(){
                var isStarSelected = false

                count++
                if(count % 2 == 0){
                    dialogBinding.icStar.setImageResource(R.drawable.ic_star)
                    isStarred = false
                }
                else {
                    dialogBinding.icStar.setImageResource(R.drawable.ic_complete_star)
                    isStarred = true
                }





            }
            dialogBinding.save.setOnClickListener(){
                var task : Task? = null
                if(isStarred) {
                    task = Task(title = dialogBinding.addNewTask.text.toString(),
                        description = dialogBinding.addNewTaskDescription.text.toString(), isStarred = true)
                }
                else {
                    task = Task(title = dialogBinding.addNewTask.text.toString(),
                        description = dialogBinding.addNewTaskDescription.text.toString())
                }

                thread {
                    dao.CreateTaskDatabase(task)

                }
                dialog.dismiss()
                taskFragment.UpdateUIWithTasks()
            }
            dialog.show()

        }

    }

    inner class PagerAdapter(activityFragment: FragmentActivity) :
        FragmentStateAdapter(activityFragment) {
        override fun getItemCount() = 1

        override fun createFragment(position: Int): Fragment {
            return taskFragment
        }

    }
}
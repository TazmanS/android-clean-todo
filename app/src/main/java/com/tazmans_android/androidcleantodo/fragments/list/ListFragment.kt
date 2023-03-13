package com.tazmans_android.androidcleantodo.fragments.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tazmans_android.androidcleantodo.R
import com.tazmans_android.androidcleantodo.R.*
import com.tazmans_android.androidcleantodo.data.viewModel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private val mTodoViewModel: ToDoViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout.fragment_list, container, false)

        val recycleView = view.recycleView
        recycleView.adapter = adapter
        recycleView.layoutManager = LinearLayoutManager(requireActivity())

        mTodoViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            adapter.setData(data)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        // Set menu
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
}
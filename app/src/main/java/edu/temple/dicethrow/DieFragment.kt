package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        const val ARG_DIE_SIDES = "sidenumber"
        private const val STATE_CURRENT_ROLL = "currentRoll"
    }

    private var dieTextView: TextView? = null

    private var dieSides: Int = 6
    private var currentRoll: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dieSides = arguments?.getInt(ARG_DIE_SIDES, 6) ?: 6
        require(dieSides > 0) { "A die must have at least one side" }
        currentRoll = if (savedInstanceState?.containsKey(STATE_CURRENT_ROLL) == true) {
            savedInstanceState.getInt(STATE_CURRENT_ROLL)
        } else {
            Random.nextInt(dieSides) + 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dieTextView?.text = currentRoll.toString()
        view.setOnClickListener{
            throwDie()
        }
    }

    fun throwDie() {
        currentRoll = Random.nextInt(dieSides) + 1
        dieTextView?.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_CURRENT_ROLL, currentRoll)
    }

    override fun onDestroyView() {
        dieTextView = null
        super.onDestroyView()
    }
}

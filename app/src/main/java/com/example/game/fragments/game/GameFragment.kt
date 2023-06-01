package com.example.game.fragments.game

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.game.R
import com.example.game.databinding.FragmentGameBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

class GameFragment : Fragment() {
    var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var st1 = 20

    private var st2 = 20
    var isUserHod = MutableStateFlow(true)
    private lateinit var viewModel: GameViewModel
    var currentProgress: ProgressBar? = null
    lateinit var handler: Handler
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        handler = Handler(Looper.getMainLooper())
        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        st1 = arguments?.getInt("st1") ?: 20
        st2 = arguments?.getInt("st2") ?: 20

        currentProgress = binding.stack1
        binding.golova2.text = requireContext().resources.getString(
            R.string.progress,
            "первая"
        )
        if (st1 > st2) {
            binding.stack1.max = st1
            binding.stack2.max = st1
        } else {
            binding.stack1.max = st2
            binding.stack2.max = st2
        }

        binding.stack1.progress = st1
        binding.stack2.progress = st2

        binding.stack1.setOnClickListener {
            currentProgress = binding.stack1
            binding.golova2.text = requireContext().resources.getString(
                R.string.progress,
                "первая"
            )
        }

        binding.stack2.setOnClickListener {
            currentProgress = binding.stack2
            binding.golova2.text = requireContext().resources.getString(
                R.string.progress,
                "вторая"
            )
        }

        binding.button.setOnClickListener {
            if (isUserHod.value) {
                currentProgress!!.progress -= binding.editText.text.toString().toInt()

                if (currentProgress == binding.stack1) {
                    binding.stack2.progress = binding.editText.text.toString().toInt()
                } else {
                    binding.stack1.progress = binding.editText.text.toString().toInt()
                }
                isUserHod.value = !isUserHod.value
                setEditText()

                handler.postDelayed({
                    when ((0..1).random()) {
                        0 -> {
                            if (binding.stack1.progress > 1) {
                                motion(binding.stack1, binding.stack2)
                            } else {
                                motion(binding.stack2, binding.stack1)
                            }
                        }

                        1 -> {
                            if (binding.stack2.progress > 1) {
                                motion(binding.stack2, binding.stack1)
                            } else {
                                motion(binding.stack1, binding.stack2)

                            }
                        }
                    }
                    isUserHod.value = !isUserHod.value
                    setEditText()
                }, 2000)
            }
        }
        isUserHod.shareIn(lifecycleScope, SharingStarted.Eagerly).onEach {
            if (it) {
                binding.golova.text = "Ваш Ход"
                if (binding.stack1.progress == 1 && binding.stack2.progress == 1) {
                    Toast.makeText(requireContext(), "Вы проиграли", Toast.LENGTH_LONG).show()
                    binding.button.isClickable = false
                    handler.removeCallbacksAndMessages(null)
                }
            } else {
                if (binding.stack1.progress == 1 && binding.stack2.progress == 1) {
                    Toast.makeText(requireContext(), "Бот проиграл", Toast.LENGTH_LONG).show()
                    binding.button.isClickable = false
                    handler.removeCallbacksAndMessages(null)
                }
                binding.golova.text = "Ход противника"
            }
        }.launchIn(lifecycleScope)
        setEditText()
        return binding.root
    }

    private fun motion(stack1: ProgressBar, stack2: ProgressBar) {
        val i = (1 until stack1.progress).random()
        stack1.progress -= i
        stack2.progress = i
    }

    private fun setEditText() {
        binding.textView1.text = binding.stack1.progress.toString()
        binding.textView2.text = binding.stack2.progress.toString()
    }
}
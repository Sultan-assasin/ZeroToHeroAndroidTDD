package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun initial(number: String): UiState

    fun increment(number: String): UiState

    fun decrement(number: String): UiState


    data class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) {
                throw IllegalStateException("step should be positive, but was $step")
            }
            if (max < 0) {
                throw IllegalStateException("max should be positive, but was $max")
            }
            if (max < step) {
                throw IllegalStateException("max should be more than step")
            }
            if (max < min) {
                throw IllegalStateException("max should be more than min")
            }
        }

        override fun initial(number: String): UiState {
            if (number == max.toString()) {
                return UiState.Max(number)
            }
            if (number == step.toString()) {
                return UiState.Base(number)
            }
            return UiState.Min(number)
        }

        override fun increment(number: String): UiState {
            val toInt = number.toInt()
            val result = toInt + step
            return initial(result.toString())
        }

        override fun decrement(number: String): UiState {
            val toInt = number.toInt()
            val result = toInt - step
            return initial(result.toString())
        }

    }

}



package dev.zzemlyanaya.focuspotion.features.pomodoro.api.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class TimerEntity(
    val sessions: Int,
    val focusSeconds: Long,
    val shortBreakSeconds: Long,
    val longBreakSecond: Long,
    val repeatAfterLongBreak: Boolean,

    @Serializable(with = TimerTypeSerializer::class)
    val currentType: TimerType,
    val currentIteration: Int,
    val currentSession: Int,
    val currentIterationMills: Long,
    val timeLeftMills: Long,

    val isStarted: Boolean,
    val isPaused: Boolean,
    val isFinished: Boolean,
) {
    val currentProgress: Float get() = 1 - timeLeftMills / currentIterationMills.toFloat()
}

@Serializable
enum class TimerType(val stage: String = "") {
    NONE("Ready?"), FOCUS("Focus"), BREAK("Rest"), LONG_BREAK("Rest")
}

object TimerTypeSerializer : KSerializer<TimerType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("TimerType", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: TimerType) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): TimerType {
        return try {
            TimerType.valueOf(decoder.decodeString().uppercase())
        } catch (e: IllegalArgumentException) {
            TimerType.NONE
        }
    }
}


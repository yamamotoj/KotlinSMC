@file:Suppress("unused")

package com.github.yamamotoj.kotlinsmc

class StateMachineBuilder {
    private val states = mutableMapOf<String, StateBuilder>()
    private var initialStateName:String? = null

    operator fun String.invoke(define: StateBuilder.() -> Unit){
        states[this] = StateBuilder(this).apply(define)
        if(initialStateName == null){
            initialStateName = this
        }
    }

    fun build(): StateMachine {
        val map = states.map { it.key to it.value.build() }.toMap()
        val initial = map[initialStateName] ?: throw IllegalStateException()
        return StateMachine(initial, map)
    }
}

class StateBuilder(val name:String) {
    private val events = mutableMapOf<String, EventBuilder>()
    infix fun String.transitTo(to:String): EventBuilder {
        val eventBuilder = EventBuilder(this, to)
        events[this]= eventBuilder
        return eventBuilder
    }
    fun build() = State(name, events.map { it.key to it.value.build() }.toMap())
}

class EventBuilder(val name:String, val transitTo: String){
    var action:(()->Unit)? = null
    infix fun action(action:()-> Unit){
        this.action = action
    }
    fun build(): Event = Event(transitTo, action)
}


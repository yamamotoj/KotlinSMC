package com.github.yamamotoj.kotlinsmc

class StateMachine(var currentState: State, val states:Map<String, State>) {
    fun sendEvent(event: String){
        val e =  currentState.events[event] ?: return
        e.action?.invoke()
        currentState = states[e.nextState] ?: throw IllegalStateException("no state")
    }

    companion object{
        fun define(def: StateMachineBuilder.() -> Unit): StateMachine {
            val builder = StateMachineBuilder()
            builder.def()
            return builder.build()
        }
    }

}
class State(val name:String, val events:Map<String, Event>)
class Event(val nextState:String, val action :(()-> Unit)?)


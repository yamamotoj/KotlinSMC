package com.github.yamamotoj.kotlinsmc

import org.junit.Assert.assertEquals
import org.junit.Test

class StateMachineTest {

    @Test
    fun testState(){
        val sm = StateMachine.define {
            "Start"{
                "TO_ZERO" transitTo "Zero" action { }
            }
            "Zero"{
                "TO_ONE" transitTo "One" action { }
            }
            "One"{
                "RETURN" transitTo  "Start" action { }
            }
        }
        assertEquals(sm.currentState.name, "Start")
        println(sm.currentState.name)
        sm.sendEvent("TO_ZERO")
        assertEquals(sm.currentState.name, "Zero")
        println(sm.currentState.name)
        sm.sendEvent("TO_ONE")
        assertEquals(sm.currentState.name, "One")
        println(sm.currentState.name)
        sm.sendEvent("RETURN")
        assertEquals(sm.currentState.name, "Start")
        println(sm.currentState.name)
    }

}
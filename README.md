# State Machine DSL

[SMC (State Machine Compiler)](http://smc.sourceforge.net/) -esque Kotlin DSL

# usage

See test code below.

```kotlin
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
        // initial State
        assertEquals(sm.currentState.name, "Start")

        sm.sendEvent("TO_ZERO")
        assertEquals(sm.currentState.name, "Zero")

        sm.sendEvent("TO_ONE")
        assertEquals(sm.currentState.name, "One")

        sm.sendEvent("RETURN")
        assertEquals(sm.currentState.name, "Start")
    }

```


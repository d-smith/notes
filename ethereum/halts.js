var halts = function(f) {
    //return true if f halts, false if it does not
}

var indefiniteLoop = function() {
    while(true) {
        "..."
    }
}

var contradiction = function() {
    return halts(contradiction) && indefiniteLoop()
}

// Assume halts(contradiction) return true
//...then we expect indefiniteLoop to be called, contradiction runs forever...

// Assume halts(contradiction) return false
// ...then indefiniteLoop is never called, so it did halt

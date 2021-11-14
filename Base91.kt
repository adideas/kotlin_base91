object Base91 {

    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!#$%&()*+,./:;<=>?@[]^_`{|}~\""
    
    fun encode(input: String): String {
        
        var output = ""
        
        if (input != "") {
            val lengthString = input.length
            var byte = 0
            var shift = 0
            
            for (i in 0 .. lengthString-1) {
                val ordinateChar = input[i].toInt()
                byte = byte or (ordinateChar shl shift);

                shift = shift + 8
                
                if (shift > 13) {
                    var value = byte and 8191
                    
                    if (value > 88) {
                        byte = byte shr 13
                        shift = shift - 13
                    } else {
                        value = byte and 16383
                        byte = byte shr 14
                        shift = shift - 14
                    }
                    
                    output += alphabet[value % 91]
                    output += alphabet[(value / 91).toInt()]
                }
            }
            
            if (shift > 0) {
                output += alphabet[byte % 91]
                if (shift > 7 || byte > 90) {
                    val index = (byte / 91).toInt()
                    output += alphabet[index]
                }
            }
        }
        
        return output
    }

    fun decode(input: String): String {
        var output = ""
        
        val alphabetIndexed = mutableMapOf<Char, Int>()
        alphabet.forEachIndexed { index, it ->
            alphabetIndexed[it] = index
        }
        
        
        
        if (input != "") {
            val lengthString = input.length
            var byte = 0
            var shift = 0
            var value = -1
            
            for (i in 0 .. lengthString-1) {
                var newValue = alphabetIndexed[input[i]]
                
                if (value < 0) {
                    value = newValue!!
                } else {
                    value = value + (newValue!! * 91)
                    byte = byte or (value shl shift)
                    
                    if (value and 8191 > 88) {
                        shift = shift + 13
                    } else {
                        shift = shift + 14
                    }
                    
                    do {
                        output = output + ((byte and 255).toChar())
                        byte = byte shr 8
                        shift = shift - 8
                    } while (shift > 7);
                    value = -1
                }
            }
            if (value + 1 > 0) {
                val a = byte or (value shl shift)
                
                
                output = output + ((a and 255).toChar())
            }
        }
        
        return output
    }
}

import java.math.BigInteger

const val p = 11
const val q = 7
const val n = p * q // 77
const val phi = (p - 1) * (q - 1) // 60
val e = findCoprimeE(phi) // 7



fun main() {
    val d = e.toBigInteger().modInverse(phi.toBigInteger()).toInt() // 43
    val openKey = Pair(e, n) // (7, 77)
    val closeKey = Pair(d, n) // (43, 77)

    for (message in 0..100) {
        println("message: $message")

        val encryptedMessage = messageEncryption(message, openKey)
        val decryptedMessage = messageDecryption(encryptedMessage, closeKey)

        println("decrypted: $decryptedMessage")
        println("-------------------------")
    }
}

fun messageEncryption(message: Int, openKey: Pair<Int, Int>): Int {
    return modPow(message, openKey.first, openKey.second)
}

fun messageDecryption(encryptedMessage: Int, closeKey: Pair<Int, Int>): Int {
    return modPow(encryptedMessage, closeKey.first, closeKey.second)
}

fun gcd(a: Int, b: Int) : Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun findCoprimeE(phi: Int) : Int {
    var e = 2
    while (e < phi) {
        if (areCoprime(e, phi))
            return e
        e++
    }
    throw IllegalArgumentException("No coprime found")
}

fun areCoprime(a: Int, b: Int) : Boolean {
    return gcd(a, b) == 1
}

fun modPow(base: Int, exp: Int, mod: Int): Int {
    var result = 1
    var b = base % mod
    var e = exp

    while (e > 0) {
        if (e % 2 == 1) {
            result = (result * b) % mod
        }
        b = (b * b) % mod
        e /= 2
    }

    return result
}

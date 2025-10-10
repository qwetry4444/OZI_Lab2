import kotlin.math.pow

const val p = 11
const val q = 7
const val n = p * q // 35
const val eulerFunctionValue = (p - 1) * (q - 1) // 24
val e = findCoprimeE(eulerFunctionValue) // 5



fun main() {
    val d = euclidAlgorithm(e, eulerFunctionValue)
    val openKey = Pair(e, n)
    val closeKey = Pair(d, n)

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

fun findCoprimeE(eulerFunctionValue: Int) : Int {
    var e = 2
    while (e < eulerFunctionValue) {
        if (areCoprime(e, eulerFunctionValue))
            return e
        e++
    }
    throw IllegalArgumentException("No coprime found")
}

fun areCoprime(a: Int, b: Int) : Boolean {
    return gcd(a, b) == 1
}

fun euclidAlgorithm(e: Int, phi: Int): Int {
    var a = phi
    var b = e
    var x0 = 0
    var x1 = 1

    while (b != 0) {
        val q = a / b
        val r = a % b
        val temp = x0 - q * x1

        a = b
        b = r
        x0 = x1
        x1 = temp
    }

    return if (x0 < 0) x0 + phi else x0
}


fun modPow(base: Int, exp: Int, mod: Int): Int {
    var result = 1L
    var b = base.toLong() % mod
    var e = exp
    val m = mod.toLong()

    while (e > 0) {
        if (e % 2 == 1) {
            result = (result * b) % m
        }
        b = (b * b) % m
        e /= 2
    }

    return result.toInt()
}

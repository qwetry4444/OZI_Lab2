import kotlin.math.absoluteValue
import kotlin.math.min
import kotlin.math.pow

const val p = 5
const val q = 7
const val n = p * q // 35
const val eulerFunctionValue = (p - 1) * (q - 1) // 24
const val e = 5 // 101



fun main() {
    val message = 28

    val d = euclidAlgorithm(e, eulerFunctionValue)

    val openKey = Pair(e, n)
    val closeKey = Pair(d, n)

    val encryptedMessage = messageEncryption(message, openKey)
    val decryptedMessage = messageDecryption(encryptedMessage, closeKey)

    println(decryptedMessage)
}

fun messageEncryption(message: Int, openKey: Pair<Int, Int>) : Int {
    return message.toDouble().pow(openKey.first.toDouble()).toInt() % openKey.second
}

fun messageDecryption(encryptedMessage: Int, closeKey: Pair<Int, Int>) : Int {
    return encryptedMessage.toDouble().pow(closeKey.first).toInt() % closeKey.second
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

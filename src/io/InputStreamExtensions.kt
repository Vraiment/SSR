package vraiment.sage.ssr.io

import java.io.InputStream

// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- //
// This file contains several extensions to make easier to work with input stream  //
// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- //

/**
 * Reads the next byte of the stream.
 *
 * @return The next byte of the stream or null if the end was reached.
 */
fun InputStream.tryReadByte(): Byte? = read().takeUnless { it == -1 }?.toByte()

/**
 * Reads the next short of the stream.
 *
 * @return The next short of the stream or null if the end was reached while reading it.
 */
fun InputStream.tryReadShort(): Short? = tryReadNumber(Short.SIZE_BYTES) { it.toShort() }

/**
 * Reads the next int of the stream.
 *
 * @return The next int of the stream or null if the end was reached while reading it.
 */
fun InputStream.tryReadInt(): Int? = tryReadNumber(Int.SIZE_BYTES) { it.toInt() }

private fun <T> InputStream.tryReadNumber(size: Int, block: (ByteArray) -> T): T? where T : Number = ByteArray(size).let {
    for (n in it.indices) {
        it[n] = tryReadByte() ?: return null
    }

    return block(it)
}

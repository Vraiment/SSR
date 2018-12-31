package vraiment.sage.ssr.io

import java.nio.ByteBuffer
import java.nio.ByteOrder

// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- //
// This file contains several extensions to make easier to work with numeric types //
// ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- //

/**
 * Converts the array of bytes to the short they represent given they are little endian.
 *
 * @return The integer equivalent to the four bytes together.
 *
 * @throws IllegalArgumentException If the array is not of the same size of a short.
 */
fun ByteArray.toShort(): Short = takeIf { it.size == Short.SIZE_BYTES }
        ?.let { ByteBuffer.wrap(this).order(ByteOrder.LITTLE_ENDIAN).short }
        ?: throw IllegalArgumentException("Array is not the size of a short (${Short.SIZE_BYTES})")

/**
 * Converts the array of bytes to the integer they represent given they are little endian.
 *
 * @return The integer equivalent to the four bytes together.
 *
 * @throws IllegalArgumentException If the array is not of the size of an integer.
 */
fun ByteArray.toInt(): Int = takeIf { it.size == Int.SIZE_BYTES }
        ?.let { ByteBuffer.wrap(this).order(ByteOrder.LITTLE_ENDIAN).int }
        ?: throw IllegalArgumentException("Array is not the size of an integer (${Int.SIZE_BYTES})")

/**
 * Converts the integer to a float by interpreting the bits of the integer as a float number.
 *
 * @return The float that is represented by the bits in the integer.
 */
fun Int.bitsToFloat(): Float = java.lang.Float.intBitsToFloat(this)

package com.example.scalecaching.common

import kotlin.random.Random

class Util {
    companion object {
        fun randomWord(): String {
            val random = Random
            val sb = StringBuilder()
            for (i in 1..random.nextInt(10) + 5) {
                sb.append(('a' + random.nextInt(26)))
            }
            return sb.toString()
        }
    }
}
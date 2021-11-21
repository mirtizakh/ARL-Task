package com.task.sample.util

val String.Companion.empty: String get() = ""

fun String?.safeGet(): String = this ?: String.empty
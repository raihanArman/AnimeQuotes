package com.example.animequotes.base.arch

/**
 * @author Raihan Arman
 * @date 14/07/2022
 */
abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}
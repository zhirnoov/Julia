package com.github.zhirnoov.julia.presentation.components

enum class CardFace(val angle : Float) {

    FrontSide(0f) {
        override val next : CardFace
        get() = BackSide
    },

    BackSide(180f) {
        override val next : CardFace
        get() =  FrontSide
    };

    abstract val next : CardFace
}
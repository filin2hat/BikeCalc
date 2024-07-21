package dev.filinhat.bikepressurecalc.common.utils

import dev.filinhat.bikepressurecalc.common.enums.WheelSize

fun WheelSize.toStringName(): String =
    when (this) {
        WheelSize.Inches24 -> "24 (ISO 507 - 540 mm)"
        WheelSize.Inches26 -> "26 (ISO 559 - 590 mm)"
        WheelSize.Inches27dot5 -> "27.5 (ISO 584 mm / 650B)"
        WheelSize.Inches28 -> "28 (ISO 622 / 700C mm)"
        WheelSize.Inches29 -> "29 (ISO 622  mm)"
    }

fun String.toWheelSize(): WheelSize =
    when (this) {
        "24 (ISO 507 - 540 mm)" -> WheelSize.Inches24
        "26 (ISO 559 - 590 mm)" -> WheelSize.Inches26
        "27.5 (ISO 584 mm / 650B)" -> WheelSize.Inches27dot5
        "28 (ISO 622 / 700C mm)" -> WheelSize.Inches28
        "29 (ISO 622  mm)" -> WheelSize.Inches29
        else -> WheelSize.Inches24
    }

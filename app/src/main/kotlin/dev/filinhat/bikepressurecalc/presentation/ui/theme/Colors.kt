package dev.filinhat.bikepressurecalc.presentation.ui.theme

import androidx.compose.ui.graphics.Color

object PrimaryColors {
    private object Palette {
        val P100 = Color(0xFF_FF_FF_FF)
        val P98 = Color(0xFF_F9_F9_FF)
        val P95 = Color(0xFF_EC_F0_FF)
        val P90 = Color(0xFF_D6_E3_FF)
        val P80 = Color(0xFF_AA_C7_FF)
        val P70 = Color(0xFF_7A_AC_FF)
        val P60 = Color(0xFF_3E_90_FF)
        val P50 = Color(0xFF_00_75_E5)
        val P40 = Color(0xFF_00_5D_B8)
        val P30 = Color(0xFF_00_46_8D)
        val P20 = Color(0xFF_00_2F_64)
        val P10 = Color(0xFF_00_1B_3E)
        val P0 = Color(0xFF_00_00_00)
    }

    val Primary = Palette.P40
    val OnPrimary = Palette.P100
    val PrimaryContainer = Palette.P90
    val OnPrimaryContainer = Palette.P10
    val PrimaryFixed = Palette.P90
    val PrimaryFixedDim = Palette.P80
    val OnPrimaryFixed = Palette.P10
    val OnPrimaryFixedVariant = Palette.P30
    val InversePrimary = Palette.P80
}

object SecondaryColors {
    private object Palette {
        val S100 = Color(0xFF_FF_FF_FF)
        val S98 = Color(0xFF_F9_F9_FF)
        val S95 = Color(0xFF_EC_F0_FF)
        val S90 = Color(0xFF_D6_E3_FF)
        val S80 = Color(0xFF_B8_C7_E7)
        val S70 = Color(0xFF_9D_AC_CA)
        val S60 = Color(0xFF_82_91_AF)
        val S50 = Color(0xFF_69_77_94)
        val S40 = Color(0xFF_50_5F_7A)
        val S30 = Color(0xFF_39_47_61)
        val S20 = Color(0xFF_22_31_4A)
        val S10 = Color(0xFF_0C_1C_34)
        val S0 = Color(0xFF_00_00_00)
    }

    val Secondary = Palette.S40
    val OnSecondary = Palette.S100
    val SecondaryContainer = Palette.S90
    val OnSecondaryContainer = Palette.S10
    val SecondaryFixed = Palette.S90
    val SecondaryFixedDim = Palette.S80
    val OnSecondaryFixed = Palette.S10
    val OnSecondaryFixedVariant = Palette.S30
}

object TertiaryColors {
    private object Palette {
        val T100 = Color(0xFF_FF_FF_FF)
        val T98 = Color(0xFF_FF_F8_F5)
        val T95 = Color(0xFF_FF_EE_E1)
        val T90 = Color(0xFF_FF_DC_C0)
        val T80 = Color(0xFF_FF_B8_75)
        val T70 = Color(0xFF_F8_93_19)
        val T60 = Color(0xFF_D5_7A_00)
        val T50 = Color(0xFF_B0_64_00)
        val T40 = Color(0xFF_8D_4F_00)
        val T30 = Color(0xFF_6B_3B_00)
        val T20 = Color(0xFF_4B_28_00)
        val T10 = Color(0xFF_2D_16_00)
        val T0 = Color(0xFF_00_00_00)
    }

    val Tertiary = Palette.T40
    val OnTertiary = Palette.T100
    val TertiaryContainer = Palette.T90
    val OnTertiaryContainer = Palette.T10
    val TertiaryFixed = Palette.T90
    val TertiaryFixedDim = Palette.T80
    val OnTertiaryFixed = Palette.T10
    val OnTertiaryFixedVariant = Palette.T30
}

object ErrorColors {
    private object Palette {
        val E100 = Color(0xFF_FF_FF_FF)
        val E98 = Color(0xFF_FF_F8_F7)
        val E95 = Color(0xFF_FF_ED_EA)
        val E90 = Color(0xFF_FF_DA_D6)
        val E80 = Color(0xFF_FF_B4_AB)
        val E70 = Color(0xFF_FF_89_7D)
        val E60 = Color(0xFF_FF_54_49)
        val E50 = Color(0xFF_DE_37_30)
        val E40 = Color(0xFF_BA_1A_1A)
        val E30 = Color(0xFF_93_00_0A)
        val E20 = Color(0xFF_69_00_05)
        val E10 = Color(0xFF_41_00_02)
        val E0 = Color(0xFF_00_00_00)
    }

    val Error = Palette.E40
    val OnError = Palette.E100
    val ErrorContainer = Palette.E90
    val OnErrorContainer = Palette.E10
}

object NeutralColors {
    private object Palette {
        val N100 = Color(0xFF_FF_FF_FF)
        val N98 = Color(0xFF_FA_F9_FD)
        val N96 = Color(0xFF_F3_F3_FA)
        val N95 = Color(0xFF_FA_F9_FD)
        val N94 = Color(0xFF_ED_ED_F4)
        val N92 = Color(0xFF_E7_E8_EE)
        val N90 = Color(0xFF_E3_E2_E6)
        val N87 = Color(0xFF_D9_D9_E0)
        val N80 = Color(0xFF_C7_C6_CA)
        val N70 = Color(0xFF_AB_AB_AF)
        val N60 = Color(0xFF_90_90_94)
        val N50 = Color(0xFF_77_77_7A)
        val N40 = Color(0xFF_5E_5E_62)
        val N30 = Color(0xFF_46_47_4A)
        val N20 = Color(0xFF_2F_30_33)
        val N10 = Color(0xFF_1A_1B_1E)
        val N0 = Color(0xFF_00_00_00)
    }

    val Surface = Palette.N98
    val OnSurface = Palette.N10
    val InverseSurface = Palette.N20
    val InverseOnSurface = Palette.N95
    val Shadow = Palette.N0
    val Scrim = Palette.N0
    val SurfaceBright = Palette.N98
    val SurfaceContainer = Palette.N94
    val SurfaceContainerHigh = Palette.N92
    val SurfaceContainerHighest = Palette.N90
    val SurfaceContainerLow = Palette.N96
    val SurfaceContainerLowest = Palette.N100
    val SurfaceDim = Palette.N87
}

object NeutralVariantColors {
    private object Palette {
        val NV100 = Color(0xFF_FF_FF_FF)
        val NV98 = Color(0xFF_F9_F9_FF)
        val NV95 = Color(0xFF_EF_F0_FA)
        val NV90 = Color(0xFF_E0_E2_EC)
        val NV80 = Color(0xFF_C4_C6_D0)
        val NV70 = Color(0xFF_A9_AB_B4)
        val NV60 = Color(0xFF_8E_90_99)
        val NV50 = Color(0xFF_74_77_7F)
        val NV40 = Color(0xFF_5B_5E_66)
        val NV30 = Color(0xFF_44_47_4E)
        val NV20 = Color(0xFF_2D_30_38)
        val NV10 = Color(0xFF_18_1C_22)
        val NV0 = Color(0xFF_00_00_00)
    }

    val OnSurfaceVariant = Palette.NV30
    val Outline = Palette.NV50
    val OutlineVariant = Palette.NV80
}


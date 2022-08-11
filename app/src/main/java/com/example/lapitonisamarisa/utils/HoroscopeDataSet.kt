package com.example.lapitonisamarisa.utils

import android.content.Context
import com.example.lapitonisamarisa.R
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel
import java.net.URL

object HoroscopeDataSet {

    enum class SignNames(val signName: String) {
        PISCES("Pisces"),
        AQUARIUS("Aquarius"),
        CAPRICORN("Capricorn"),
        SAGITTARIUS("Sagittarius"),
        SCORPIO("Scorpio"),
        LIBRA("Libra"),
        VIRGO("Virgo"),
        LEO("Leo"),
        CANCER("Cancer"),
        GEMINI("Gemini"),
        TAURUS("Taurus"),
        ARIES("Aris")
    }

    fun createDataSet(context: Context?): List<HoroscopeSignsModel> {
        return listOf(
            HoroscopeSignsModel(
                url = URL("https://www.seekpng.com/png/detail/68-685899_zodiac-sign-aries-aries-symbol-png.png"),
                signName = SignNames.ARIES.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://e7.pngegg.com/pngimages/588/834/png-clipart-taurus-astrological-sign-ascendant-zodiac-astrological-symbols-taurus-monochrome-astrological-sign.png"),
                signName = SignNames.TAURUS.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://simg.nicepng.com/png/small/217-2178737_gemini-png-gemini-sign.png"),
                signName = SignNames.GEMINI.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://w7.pngwing.com/pngs/800/617/png-transparent-cancer-computer-icons-symbol-zodiac-sign-miscellaneous-text-astrological-sign.png"),
                signName = SignNames.CANCER.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://toppng.com/uploads/preview/leo-zodiac-11550715661nithedammu.png"),
                signName = SignNames.LEO.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://www.pngkey.com/png/full/88-885319_virgo-png-virgo-sign.png"),
                signName = SignNames.VIRGO.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://cdn.pixabay.com/photo/2012/04/18/01/13/libra-36393_1280.png"),
                signName = SignNames.LIBRA.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://upload.wikimedia.org/wikipedia/commons/6/69/Scorpio.png"),
                signName = SignNames.SCORPIO.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Sagittarius_symbol_%28bold%29.svg/640px-Sagittarius_symbol_%28bold%29.svg.png"),
                signName = SignNames.SAGITTARIUS.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://cdn-icons-png.flaticon.com/512/3184/3184956.png"),
                signName = SignNames.CAPRICORN.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJY3YVruaarFU1ViGFQ0qMHewsqN0ZAiAk_A&usqp=CAU"),
                signName = SignNames.AQUARIUS.signName
            ),
            HoroscopeSignsModel(
                url = URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJgn53ZypXhID-96i9PWA3BkwTOo_nCyLCYQ&usqp=CAU"),
                signName = SignNames.PISCES.signName
            ),
        )
    }
}
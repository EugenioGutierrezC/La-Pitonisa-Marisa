package com.example.lapitonisamarisa.ui.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lapitonisamarisa.R
import com.example.lapitonisamarisa.data.model.HoroscopeSignsModel
import com.example.lapitonisamarisa.databinding.FragmentZodiacoBinding
import com.example.lapitonisamarisa.ui.adapter.HoroscopeCardsAdapter
import com.example.lapitonisamarisa.ui.viewmodel.HoroscopeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private var _binding: FragmentZodiacoBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HoroscopeCardsAdapter

    private val horoscopeViewModel: HoroscopeViewModel by viewModels()

    private lateinit var lastSignNameClick: String
    private lateinit var lastUrlNameClick: String

    companion object {
        fun createDataSet(context: Context?): List<HoroscopeSignsModel> {
            return listOf(
                HoroscopeSignsModel(
                    url = "https://www.seekpng.com/png/detail/68-685899_zodiac-sign-aries-aries-symbol-png.png",
                    signName = context!!.getString(R.string.aries)
                ),
                HoroscopeSignsModel(
                    url = "https://e7.pngegg.com/pngimages/588/834/png-clipart-taurus-astrological-sign-ascendant-zodiac-astrological-symbols-taurus-monochrome-astrological-sign.png",
                    signName = context!!.getString(R.string.taurus)
                ),
                HoroscopeSignsModel(
                    url = "https://simg.nicepng.com/png/small/217-2178737_gemini-png-gemini-sign.png",
                    signName = context!!.getString(R.string.gemini)
                ),
                HoroscopeSignsModel(
                    url = "https://w7.pngwing.com/pngs/800/617/png-transparent-cancer-computer-icons-symbol-zodiac-sign-miscellaneous-text-astrological-sign.png",
                    signName = context!!.getString(R.string.cancer)
                ),
                HoroscopeSignsModel(
                    url = "https://toppng.com/uploads/preview/leo-zodiac-11550715661nithedammu.png",
                    signName = context!!.getString(R.string.leo)
                ),
                HoroscopeSignsModel(
                    url = "https://www.pngkey.com/png/full/88-885319_virgo-png-virgo-sign.png",
                    signName = context!!.getString(R.string.virgo)
                ),
                HoroscopeSignsModel(
                    url = "https://cdn.pixabay.com/photo/2012/04/18/01/13/libra-36393_1280.png",
                    signName = context!!.getString(R.string.libra)
                ),
                HoroscopeSignsModel(
                    url = "https://upload.wikimedia.org/wikipedia/commons/6/69/Scorpio.png",
                    signName = context!!.getString(R.string.scorpio)
                ),
                HoroscopeSignsModel(
                    url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Sagittarius_symbol_%28bold%29.svg/640px-Sagittarius_symbol_%28bold%29.svg.png",
                    signName = context!!.getString(R.string.sagittarius)
                ),
                HoroscopeSignsModel(
                    url = "https://cdn-icons-png.flaticon.com/512/3184/3184956.png",
                    signName = context!!.getString(R.string.capricorn)
                ),
                HoroscopeSignsModel(
                    url = "data:image/png;base64,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",
                    signName = context!!.getString(R.string.aquarius)
                ),
                HoroscopeSignsModel(
                    url = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPsAAADJCAMAAADSHrQyAAAAkFBMVEX///8AAADl5eXm5ubk5OTj4+Px8fHz8/Pv7+/r6+v8/Pz29vb4+Pjs7OyVlZVvb2+GhoZ6enrW1tbPz89JSUmxsbF1dXVjY2OLi4vFxcXa2toPDw+6urpSUlJbW1vMzMxqamqenp46OjoxMTEjIyMYGBjAwMCmpqYdHR2AgICzs7MrKytAQECRkZE2NjZLS0udOViTAAAUaUlEQVR4nNVd2WKjOgx1MMGsaZvu27TptNPbdpb//7trFhNjHWMTIEkzD3QgOcjYlmRJPjAmPwnnQS6PIuBBUZ4IOQ/LYyFPCHnMA84TxrLXBflcZ7z8JtcgYnlMFUSJncljVkPU2KmB3UKUFyJ5IWoggh7xnqkwr8saO0bYeYOtxKsueLed5feg8dEh2h6wayrKfcLnazu7ozdcXB2i7eIDSCK/OLjtPAyXFfgyDCv55HFZtV0eKwx5osZ4ArfcGBCFDpHIY9V2J3YoT1Rtl39UbZcXQlO8BjvaADnWFXbVdvlL0UBU2IGGHbfYifzkaZpG8ijkMS9PyGOqLgh5jJoLPGUP4KaXgpe/iBuITB4LHSLSIFrsrMGOmxMVRGFC8OYXcUc89gmkeBCJgoDYRDymD5NmpAV1Fwc8RKMYPfHbpDNDFETQYJNRrGMXnRkiL1SjREJ0ZkjcwV4DGVZR0EL0T2CFTdpOpgiZwVfgxndQO5C2I+xu223aQZ/BiQASXGX9VgGIx4IgaKZI0MyyQPV7UM8yeSFuLlQTNbugd36rtJf8Zj2Dg2YGN9iRhh2a2PUMbrATeaLu96DRDg2Ehs2Ln1SAx+pmtXYIQit23e8NNsuyLI/iOBbyDyGPUS7/kMcYXcjrC+CxX6SeEC7sLC9PCPXNfPvNvP5mzh7p7e8joUNEDYRQEBCb1VOkfVSBelTNMGkfVTP95AmpI0DjP6IWop4h6mkHbU8GiQ2bNyMw7IyScDtKdPFOwd2XvMWuZ4iBzbfYrXgBGzZFmhn8C9z+M9mPfUf3fijs2sHl24B+X8K+CZvn9wcIsE6RdkD9jrG9+j1KwJ1vhDamlPKy9Xu47XchRBbJj8jkP+1IT2TaN9kPIAL8hehCWW7me0FkwKv+wYZAbC/06vkQ6vlqlKT/ARkiOkqIng9MbD89r3oSevGRLl4vdkd57WDf6xmMPPtVPLd9fwd3PedKPD/73mIP9+uU74XEeOJdvy4gfl0wyq9bgnu+x0o8w68LadvDrl9n+vOtt6v7xR1nPK4voOH3Vvj5873YwnDGNfFQ/CDtwY7Vhdzw5yts1syEZqGkzbLqUckLHU9Nm36BeKOSPKdgljXrOIpteIEh0Q5qHVeLx07A005N7I6eV9iBhq2U1472vRqCGRqBl8V89v0W3O8879EO08Yuwo522ABhllQ7qLYH/W23r/pq8ZArvYoFwtZWfW3bQ3vbM99+DzTNCFY1F2Kyfg+6/Q70i7yZiR349Xt1QUVL4mW4VNGS+mk3F4S8oEIdRiSGw1G/jcQsAbYtEuOK8iTIrki5IlO8XuxOoGhn+1497eISCZTOYd9DcKdPYxQPXb+PajvPwXrykc3Q9hSM+Ee2U9u3vk24De9Zx3xoGfNyUKEQymVijvlw9JhHOr62WqZ4A8Z8aePL2G7GUibksSjPyGMZ3mOFPAp5IePlo6qjezyVJyJ5jOQxh6Ne3rqCYA1E1kAUOkTcYOc6dq5htxBSvBg9Yrt4vdhxi109kcA1RZCLWdshFME6GWTjPCKbEYjGP/ZgD7Rxw32bZgYjXb+e2LdBcdnEM7Lp8m3Cbeiw3+9c0hVvsaJyfQGf1oltj2yK3/QWK6ateHf0aQv5yeUnkkchj5k8xuWJWP6RyaOQx6g8ob4ZN99UF9gXlexdqF9kBnbhjR2rCynIRHyVX9Uh3NiRid1GimL5h4oUmbELR/QR6WAtslmOtC22GbtoJlmgFqAGdvULgH+eaNhKvBYCYpPA60j7XmEIYHs/dO0wzr7HYFI9d7TDrvadxKxAvzuijyFSdwmawaDfjRUvjWxCZRp1IptKPDOySbG7/S4m+KSgZx4nQS7BQQ5slU6BzMwpgnWxGX2MjZ4EXfPEHTFqE5vEqOueREHpqCf+7RejHhm70GcwyEz/i6ax70CZfCYDMpdDcxNW1RjarMILlfCWY+3gzElp2CEKT74Im1Vw56QCPSeFLDYxiZlubpHFToGdu0itFrsXu2OxUbffRjaLPcxZQbkJtY6z5KCxpwYWs+fc6qmFrhy08tRAjctF1owS4qktndjK2ZvMvlcXgMv9Lxtv30Hu6ykxtcNksQtz6YNyE3T1xFDHP+1Sc9LBDijoY1vQF5pt96w5CbSak3bJGzVLXrVqrpa8orPkta72UZLqhzBW+05sY7WPIvLniXO17xeMqHMT/jVmKBLTaIcYdPxypxqzNhLD0fNkjiiPEg/XmC31GrNhU8Rm32U3JEDVn42z7zEosVhr4k1i36doO8/AKluMis8Dl+5FF2+Ktu8w5kHEnYNqkNVuY76OuHMQkv+lQ9jH/NI+5pfbMa+ettvGBfRpb0dJCZHTNOl9McbGUbyvFI0SJd7Y/PvONk5+c0P76ZIxZOOsbdew43MEp7XdYuP2lZvotL0oqLAXnbYP6vcMuLNYO+zq24zwaUlGvQCh5PKKNaOO/E6FjWIWp/3RR6dPu+z6tFolRX+pAqmkUGXNWpUGEPdG9FZS2Ks0GJhBd0UrnlkZ3Y8NCz1YM8s8S1TCoL86B/g3wlJBQ3MTRnUODf8+Zl3xPKuuG/HMGTKlfS8hQAzjIdnNvgOf7jIx2j5t7MKSP1AxAJCb6EYfqcDXEa2Yg9hBd8V7RqFiezWeO2YFchOjyhxJFWYKJB6CvT0BKkzO0rHidb4ptrkJZ4y6rzJaVcii/TQPvTHqAMeoOZg9a2OUkApZVHWNC3int+/ym2DQP+5i3wsQmWY27TDCvrfhvR1zUnpFPArVp+5+J9igpuEmt/S7Oydl6XfrboW4s1uBbmPAOyEyoJ4/M8tOCPtmihwM+bssH7DLwmMHB1P60qXnrTnotidrbyqmC5Brxvpz0LTqOqc682uHqusWG+v5YVPEZd+ZKEC8ITW1g8u+84iWyf+h4k1j3323lNGaE7PqmoPwzRPz8uu2KzMEsgZtV6u+Hf06uJXQ3I3ot9Ox3kWZU7E/WI0NdzpmJoTEjsCeFOax05EP2eSZ6DVmoRF9bGrMwp4aMxSJobHV+8y9w7WLTX35k6Ib5UE7XP1rzGax7wL69OuB9h0EAqQvb/X+p9o3Mb7tERX8PRnWdlBgc9ez8tnVt7HWEqtB1VNLbI75JkJIB+x1bK2rXKIxT526n8IV2QRjnmJ36yrNKQJbM6gyGlm5nicFWhNTC3fa86T647SdjuzGaYcNE7d9D3iCfLIh9h2VEv5C4h1PbkJBcLBd9nJI2xNQoyvmavtO+ThqERWEoHu0n4fkJsAa7pUx36prJR7OTYzhfPDxhICFf8sGcD4AZXmV28Tr3X82NefDNkJo5ibUKE43YML7cz7kwEheCh8vdX7OB5d9ZykI3nwKi3ag9j0HP7+L+iKbE+YmHJwPcOVYz2C14qXC32SsW/tor4lBwyYnq9K+eps5OR9cEQMUrU19sTOwNeaekShKr3gzcj50I1wkChWjUhHmzfmAnlzcH+Hyr68bz/nQa98lOFiCBtzbvoMZk0DxZqmrdHI+mPW0RkQbVCHccl/OB5B6/uT9EW3S73NyPvRfyIH475kn50MKqi2exJTijeR8cGWZqPinDGSwkC5Gieyc9qRdz8/P+dBj3/MAxB5+27SDad8F2HSW4hk8dexigF8XWvy6PIxoWfVXg+3y64KIerS//aKP++N8oNUEW4c5A3n42I/zoQCrwH8Mi0eij8ifn4PzwV6hUsAE8pr7cT4AXXEVOytU9sz50GPf2YY24DPxs++oSq/A4u03dgFyE75tf0/8OB9AnHKTWNoegGo3IzcxG+eDtd9BAz4Kv34H0b73xGIVAtrv83I+lPsuymNaxEW1zyppToi4qMooWRGBnFJJZ1oU9Qoi0iCqdKi8UEMUAiTe17J5LXa5VirlbSDkMqXIG4hEye3MTTTxqiH2XZycnZ2dyM+VPF6Vf5w1J86aE80FUBb4+rH9pg5xpUPI/wL+nucONr5peeJ62AQe1PYA5NqO6uPV9h05H75D22fjfDh04xyfclPKbJwPh26c45MHO9k4P/Vw6MY5PqTtHr6NN+fDoRvn+OThfJwP6aEb5/iwGTkfvoOen4nz4Xu0fSbOh+/Q9rk4H45d1w1sjTlF+mvcD904xyefk/Ph0I1zfHax796q8ez09PTParW6kccbefwjj6fyuDrtXgBx5q+b6hcr9Yub5hcdiNVqA8jlr7o3hRDlhZKXej7Oh3KalBjlH+XTStW8yboXQDX1IxOZUjCs+WbaQLQXUsjtsc47N7VDHIjzQdcOS1QPe63noO172GLAdHAHPMxj4nzoaAe0H/zEKzcRxKjfLTN4bO0Bafvu+2G3qz5UZnbiV3MSg/z1k2VltmPNSTA150Oqr5oL0PYr5sX5EIN43S82jOFx75wPnWgJqJA7S/xqzEBa4z0ZwOypxLPtf5+D88EVn//jGaMGdQvW+PzR1VVacxN+bQeh/dWMbZ+G80Efl2Cn2CX3G/NAVfwpho75vXM+bHsyApP21i//jkzElY+NOyzng5Z/BznoRLdxPZwP0CWEbT8qzodtv4PaCeFZdwH4Ay5m8m2m5HxoncYIMJ402E7Oh4RuHPhCPu2xcT6oMgiwgfsl9+R8YH/pj2MqnlkZfXjOh2aGgLb/iAJPzgewmFla8+9HxPnQtB1s+rhJfOsqgXNzy/dRVzmW88FaV/mQ+HI+gNrCtq7ymDkf6mO2oeI/5Z6cDwKUz6/SKcWbifOhftqItEAwS9W1GUcOENMFrKs8Ns6HCjyjpYVvzKYdyL6JlL6v6Pds9n1KzocKHLwh4Trv7XcdG0XsaPTxCDkfKgjglm7K834Q+Yb+PBDaToij5XwofwFKyJ9yK7bJ+SCQlUhZv54/Cs6H8heg4yJSnmjfNwE8+lWKxDs2zoeyfIW+mf2/IZwPgr6PsaoeO37OB3mkVeBnbADnAygvfGVeOx0PzvkAN//zIZwPIGzFg/A7cD6gd2/c8SG8B4Cb9SHZw76JCdpOFyNvYhjnA53wf4rvwflAgzYnxVjOh4tvwfkQABP1MIzzgYPURm5/UsfD+YDykNFATifgGJ5/h9xEQaf7C5Kvr+1gMbQ6ntyEnfNB0HjbyhyXELsd8/KL9Pn9HZiPOwTnAwOse+sCeEJ2zoc0RUGv78D5QPMqr2KL7cH5UK2vqJW75E4v9eCcD9SZ/8j7Vj6QrxJYuedoH7mJcZwPdLSeC/KOlT7OhxI7B+vYpLMqPULOhwxYOLYDNniEt/mRcz4Act6PpD/Chd+lRSv0PtIj53xA/cV34aMGsZ+cHzfnAx3y97Ejom15hxqN1v7iR835APTzTa7/wjupkN4QpOf0qDkfwMo77L5Ly8n5oLKLwL0xRslxcT6AF5W/tDN4iH2X2OClsJ8zxy5GcT6AFyRf6m334HxQ2Og5Pvb7dQflfCjA2lO4fG6C3bydAr21JDhizgeqn66Eu0Kly/nQagfADHVaHC/nA+2pdWyzwe73SYFkdHysnA8gtvzT4x3oBufDtu0R3ULxHnXabsPeP+cDo5r5csx7xIC2+50yTTMWwb45H6xxWuCGRl4VqNZIDAU85wPitJNyPvTbd1ogdVN0R/EQ+15iU91ZvoZxvn0TO7cdEBLdjXxPKDBzdxP6NoM4H3rHPAq1UOxBYx4EgU4i+26AoWM+HcT5UF2Ab/5MQEpiycibP7cQPrtAEqBCCuYWbxbOB7uNA2XjFwq738aBdy602IIWZJ9Ob+NG+jao22+t2sHPxoW448W0vo0354O13iamMaaXWGE7dhIqnxa+PZr6DKf2t0cP9GmHbaO07SCNwcL9NrW9NdwPu5IiQgHb1PbW8F7skZwPOFIkjxFV8i/bCJf+RndrfV0IsZeCpuZOFITCdtbXTcD5YJ9lIMhyC7TXUPsusRPQ8Us2jX0fxPlgixACdfwXzWDEZuuKPoKO/8FAPa2W95iF88HyyUFC4lc+BbL8ZGApe55NgczMKdJf4x6CUSJ7ki42/wln1XVfZXQng0UDYX8jO7ZfjHqy2AUoEVlzlw32su+VfOj9wEkv9iycDyGyCoDt6NquuRF2t9817OqbAhRWJ7Z+n4/zAVnsHJBbhHaL7cQmFjsCIdAzhiz23jkfkGRRj6dmYOPcRNdTA0/3nB8D5wPYz5Z0tcMY+15BAD6ln+IIOB/Atqb3wr4yc+Ym4MoM3GSzjQkdivMBhFa+hHO1b8O2rPaRFV0skwNzPsRg1++TDuGsukZVoiQSg965e3Fgzge2oTI9d+LIE9j3EgK9vuNdgBj1/uoqwYhfhO7I5vC254g+bnmYusom4k4jC4v3xF376BrzKOIOnMff4nCcDxGgtfgNsjijbVwpHnoXw+pgnA8cONrl2tqeYWttnLXtPdFH4EMt7g7F+VBQhoKqbJhoh0n6ncdglC064u2P8wEkDhY/y/PWjLoX54M9W5/SKMbiJD0E54MAymexzHoqKfw4H+xVGmiv6eKSHYDzAYToFhvP6hycmwiIdjCqc4oNetx753zgBeDIf/HNXO5g3yvxkK7/2jvnA4onLIrhVdeWijmz6loNS5D9WVzvmfMBvdtvcZmPKnNsOB96qzBTELGWtkXskfMBBc0Xz7mt+rYvRu2ukO30JMh014m/vXE+IIX7X2eWzWDfa+wCrGY7XFnzcj5w8L7mUt9aq67d/e5fEQ+f+6sjJzUd5wN6U9binXV2K0zFy0B3QuTIr3hk++F8QG/+WTyLrjc1QM+bnA/OyCYgx1ucpUg7TM35EG/Arf+KQZVJu9r3Rjxg5auFxMycD0EMeAUXC5EMqrr2rjmBkU1U0VUr+4F+nXunY8fnjkDgrNyoq++iJDsdFbYn54Nzp2OB/OnFms3M+QAf+SZy7nBtsYfVmNmiPMi/WKy5E3sE5wPP6Q6exeIjc3j/U9r3BgIQvS3uOdQOA+I2fW1PUNMfI9fKZ4a2o7jt4i33aftucZslJW8pX2TJD9H2GDkZr9mQfv8fKOb4b9ynmQ4AAAAASUVORK5CYII=",
                    signName = context!!.getString(R.string.pisces)
                ),
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZodiacoBinding.inflate(inflater, container, false)
        initRecyclerView()

        horoscopeViewModel.horoscopeLiveData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                val a = HoroscopeFragmentDirections.actionZodiacoFragmentToZodiacResultFragment2(
                    it.horoscope,
                    lastSignNameClick,
                    lastUrlNameClick
                )
                Navigation.findNavController(binding.root).navigate(a)
            }
        })

        horoscopeViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.loadingBar.isVisible = it
        })

        return binding.root
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager = GridLayoutManager(context, 3)
        adapter = HoroscopeCardsAdapter(
            createDataSet(context),
            HoroscopeCardsAdapter.OnClickListener { sign -> executeHoroscopeService(sign) }
        )
        binding.recycler.adapter = adapter
    }

    private fun executeHoroscopeService(sign: HoroscopeSignsModel) {
        lastSignNameClick = sign.signName
        lastUrlNameClick = sign.url
        horoscopeViewModel.getHoroscope(sign.signName)
    }
}
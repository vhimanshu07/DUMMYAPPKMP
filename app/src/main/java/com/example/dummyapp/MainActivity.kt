package com.example.dummyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val newAge = remember { mutableStateOf<String>("") }
            val suggestionsList =
                remember { mutableStateOf<List<SuggestedChipDetails>>(mutableListOf()) }
            val chipList by remember {
                mutableStateOf<MutableList<SuggestedChip>>(mutableListOf())
            }
            CustomTextInputLayout2(
                hintText = "Age",
                hintColor1 = Color.Black,
                hintTextStyle = TextStyle.Default,
                inputTextState = newAge,
                gridView = true,
                suggestedItemList = suggestionsList,
                inputTextClicked = {
                    newAge.value = "18yrs-20yrs"
                    suggestionsList.value = fetchSuggestions()

                },
                suggestedChipClicked = { data ->
                    newAge.value = if (data.isSelected) {
                        chipList.add(data)
                        var ans = ""
                        var count = 0
                        for (item in chipList) {
                            if (count > 2) {
                                ans += "+" + (chipList.size - count) + "more"
                                break
                            }
                            ans += item.clickedChipDetails.value

                            if (count != chipList.size - 1)
                                ans += " , "
                            count++

                        }
                        ans
                    } else {
                        var ans = ""
                        var count = 0
                        var index = 0
                        for (item in chipList) {
                            if (item.clickedChipDetails.label == data.clickedChipDetails.label) {
                                index = count
                            }
                            count++
                        }
                        count = 0
                        chipList.removeAt(index)
                        for (item in chipList) {
                            if (count > 2) {
                                ans += "+" + (chipList.size - count) + "more"
                                break
                            }
                            if (item.isSelected) {
                                ans += item.clickedChipDetails.value
                                if (count != chipList.size - 1)
                                    ans += " , "
                            }
                            count++
                        }
                        if (chipList.isEmpty()) {
                            ""
                        } else
                            ans
                    }
                    fetchMoreList(data, suggestionsList)
                },
                clickedLeftIcon = null,
                chipsTextModifier = Modifier.padding(
                    start = 12.dp,
                    top = 8.dp,
                    bottom = 8.dp,
                    end = 8.dp
                ),
                drawableLeftModifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ), multiChipSelection = true
            )

        }


    }


    fun fetchSuggestions(): List<SuggestedChipDetails> {
        return listOf(
            SuggestedChipDetails(
                label = "1",
                value = "22yrs-24yrs",
            ),
            SuggestedChipDetails(
                label = "2",
                value = "24yrs-26yrs"
            ),
            SuggestedChipDetails(
                label = "3",
                value = "26yrs-28yrs"
            ),
            SuggestedChipDetails(
                label = "4",
                value = "28yrs-30yrs"
            ),
            SuggestedChipDetails(
                label = "5",
                value = "30yrs-32yrs"
            ),
            SuggestedChipDetails(
                label = "6",
                value = "32yrs-34yrs"
            ),
            SuggestedChipDetails(
                label = "7",
                value = "34yrs-36yrs"
            )
        )

    }

    fun fetchCities(): List<SuggestedChipDetails> {
        return listOf(
            SuggestedChipDetails(
                label = "1",
                value = "Ghaziabad",
            ),
            SuggestedChipDetails(
                label = "2",
                value = "Delhi"
            ),
            SuggestedChipDetails(
                label = "3",
                value = "Noida"
            ),
            SuggestedChipDetails(
                label = "4",
                value = "Greater Noida"
            ),
            SuggestedChipDetails(
                label = "5",
                value = "Gurugram"
            ),
            SuggestedChipDetails(
                label = "6",
                value = "Faridabad"
            ),
            SuggestedChipDetails(
                label = "7",
                value = "Harayana"
            )
        )

    }

    fun fetchMoreList(
        data: SuggestedChip,
        suggestionsList: MutableState<List<SuggestedChipDetails>>,
    ) {
        val inputText = data.inputText
        val clickedChip = data.clickedChipDetails

    }
}
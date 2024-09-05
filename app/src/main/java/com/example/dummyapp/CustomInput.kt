package com.example.dummyapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Himanshu Verma on 31/07/24.
 **/


data class SuggestedChip(
    val inputText: String,
    val clickedChipDetails: SuggestedChipDetails,
    val isSelected: Boolean = false,
)

data class SuggestedChipDetails(
    val label: String,
    val value: String,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextInputLayout2(
    hintText: String,
    hintColor1: Color = Color.Black,
    hintTextStyle: TextStyle = TextStyle.Default,
    inputTextClicked: () -> Unit,
    inputTextStyle1: TextStyle = TextStyle.Default.copy(color = Color.Black),
    inputTextState: MutableState<String>,
    suggestedItemList: MutableState<List<SuggestedChipDetails>>,
    suggestedTitle: String? = null,
    gridView: Boolean = false,
    iconRight: Int? = null,
    iconLeft: Int? = null,
    clickedRightIcon: Int? = null,
    clickedLeftIcon: Int? = null,
    suggestedChipClicked: (data: SuggestedChip) -> Unit,
    chipsTextModifier: Modifier,
    drawableLeftModifier: Modifier = Modifier,
    drawableRightModifier: Modifier = Modifier,
    multiChipSelection: Boolean = false,
    dividerColor1: Color = Color.Gray,
) {
    println("Render Main layout")
    val interactionSource = remember { MutableInteractionSource() }
    val input = remember { mutableStateOf(inputTextState.value) }

    val hintColor = remember { mutableStateOf((hintColor1)) }
    val dividerColor = remember { mutableStateOf((dividerColor1)) }
    val inputTextStyle = remember { mutableStateOf((inputTextStyle1)) }



    if (interactionSource.collectIsPressedAsState().value)
        inputTextClicked()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                16.dp
            )
    )
    {
        BasicTextField(
            value = input.value,
            onValueChange = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        inputTextClicked()
                    }
                ),
            readOnly = true,
            textStyle = inputTextStyle.value,
            singleLine = true,
            interactionSource = interactionSource,
        ) { innerTextField ->

            TextFieldDefaults.DecorationBox(
                value = input.value,
                innerTextField = {
                    Box(modifier = Modifier.padding(top = 4.dp)) {
                        innerTextField()
                    }
                },
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                label = {
                    Text(
                        modifier = Modifier.padding(bottom = 12.dp),
                        text = hintText,
                        style = hintTextStyle,
                        fontWeight = FontWeight.Medium,
                        color = hintColor.value
                    )
                },
                contentPadding = PaddingValues(
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 12.dp
                ),
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(dividerColor.value)
        )


        if (suggestedItemList.value.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(10.dp))


            suggestedChips(
                suggestedTitle,
                suggestedItemList.value,
                suggestedChipClicked = {
                    suggestedChipClicked(it)
                },
                input.value,
                gridView,
                iconRight,
                iconLeft,
                clickedLeftIcon,
                clickedRightIcon,
                chipsTextModifier,
                drawableLeftModifier,
                drawableRightModifier,
                multiChipSelection
            )
        }
    }
}

/**
 *
 *
 *
 *  Rebugger(
 *         trackMap = mapOf(
 *             "hintText" to hintText,
 *             "hintColor" to hintColor,
 *             "hintTextStyle" to hintTextStyle,
 *             "inputTextClicked" to inputTextClicked,
 *             "inputTextStyle" to inputTextStyle,
 *             "suggestitemlist" to suggestedItemList,
 *             "suggestChipsTitle" to suggestedTitle,
 *             "inputText" to inputTextState,
 *             "suggestedChipClicked" to suggestedChipClicked,
 *             "suggestedItemInGridCells" to gridView,
 *             "suggestedItemsDrawableRight" to iconRight,
 *             "suggestedItemsDrawableLeft" to iconLeft,
 *             "clickedLeftIcon" to clickedLeftIcon,
 *             "clickedRightIcon" to clickedRightIcon,
 *             "chipsTextModifier" to chipsTextModifier,
 *             "drawableLeftModifier" to drawableLeftModifier,
 *             "drawableRightModifier" to drawableRightModifier,
 *             "multiSelect" to multiChipSelection,
 *             "dividerColor" to dividerColor,
 *             "interactionSource" to interactionSource,
 *             "input" to input
 *         )
 *     )
 *
 *
 *  Rebugger(
 *         trackMap = mapOf(
 *             "suggestitemlist" to suggestions,
 *             "suggestChipsTitle" to suggestChipsTitle,
 *             "inputText" to inputText,
 *             "suggestedChipClicked" to suggestedChipClicked,
 *             "suggestedItemInGridCells" to suggestedItemInGridCells,
 *             "suggestedItemsDrawableRight" to suggestedItemsDrawableRight,
 *             "suggestedItemsDrawableLeft" to suggestedItemsDrawableLeft,
 *             "clickedLeftIcon" to clickedLeftIcon,
 *             "clickedRightIcon" to clickedRightIcon,
 *             "chipsTextModifier" to chipsTextModifier,
 *             "drawableLeftModifier" to drawableLeftModifier,
 *             "drawableRightModifier" to drawableRightModifier,
 *             "multiSelect" to multiSelect
 *         )
 *     )
 *
 *
 *
 */


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun suggestedChips(
    suggestChipsTitle: String?,
    suggestions: List<SuggestedChipDetails>,
    suggestedChipClicked: (data: SuggestedChip) -> Unit,
    inputText: String,
    suggestedItemInGridCells: Boolean = false,
    suggestedItemsDrawableRight: Int?,
    suggestedItemsDrawableLeft: Int?,
    clickedLeftIcon: Int?,
    clickedRightIcon: Int?,
    chipsTextModifier: Modifier,
    drawableLeftModifier: Modifier,
    drawableRightModifier: Modifier,
    multiSelect: Boolean = false,
) {


    println("Render Chip layout")
    var selectedIndex by remember {
        mutableStateOf(-1)
    }
    var selectedIndices by remember { mutableStateOf(setOf<Int>()) }
    val suggestedItemList = remember { suggestions }


    val chipContent: @Composable (Int) -> Unit = { index ->
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (suggestedItemsDrawableLeft != null) {
                IconToBeDisplayed(
                    multiSelect,
                    selectedIndices,
                    index,
                    clickedLeftIcon,
                    suggestedItemsDrawableLeft,
                    selectedIndex,
                    drawableLeftModifier
                )
            }
            Text(
                text = suggestedItemList[index].value,
                style = TextStyle.Default,
                color = if (multiSelect)
                    if (selectedIndices.contains(index))
                        Color.LightGray
                    else
                        Color.Red
                else
                    if (selectedIndex != index)
                        Color.Red
                    else
                        Color.LightGray,
                modifier = chipsTextModifier,
                maxLines = 1
            )
            if (suggestedItemsDrawableRight != null) {
                IconToBeDisplayed(
                    multiSelect,
                    selectedIndices,
                    index,
                    clickedRightIcon,
                    suggestedItemsDrawableRight,
                    selectedIndex,
                    drawableRightModifier
                )
            }
        }

    }
    if (suggestChipsTitle?.isEmpty() == false) {
        Text(
            text = suggestChipsTitle,
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth(),
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                lineHeight = 12.sp
            ),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.padding(8.dp))
    }


    if (suggestedItemInGridCells) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            suggestedItemList.forEachIndexed { index, _ ->
                val isSelected =
                    if (multiSelect) selectedIndices.contains(index) else selectedIndex == index
                val borderColor = if (isSelected) Color.Red else Color.Gray
                val bgColor = if (isSelected) Color.Red else Color.LightGray
                Box(modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .clickable(
                        onClick = {
                            // Setting up index
                            if (multiSelect)
                                selectedIndices = if (isSelected) {
                                    selectedIndices - index
                                } else {
                                    selectedIndices + index
                                }
                            else
                                selectedIndex = index
                            suggestedChipClicked(
                                SuggestedChip(
                                    inputText,
                                    suggestedItemList[index],
                                    !isSelected
                                )
                            )
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )

                    .border(
                        BorderStroke(1.dp, borderColor),
                        shape = RoundedCornerShape(16.dp),
                    )
                    .background(
                        bgColor,
                        shape = RoundedCornerShape(16.dp)
                    )) {
                    chipContent(index)
                }
            }
        }
    } else {
        LazyRow(modifier = Modifier) {
            items(suggestedItemList.size) { index ->
                val isSelected =
                    if (multiSelect) selectedIndices.contains(index) else selectedIndex == index
                val borderColor = if (isSelected) Color.Red else Color.Gray
                val bgColor = if (isSelected) Color.Red else Color.LightGray
                Box(modifier = Modifier
                    .padding(end = 8.dp, bottom = 8.dp)
                    .clickable(
                        onClick = {
                            // Setting up index
                            if (multiSelect)
                                selectedIndices = if (isSelected) {
                                    selectedIndices - index
                                } else {
                                    selectedIndices + index
                                }
                            else
                                selectedIndex = index
                            suggestedChipClicked(
                                SuggestedChip(
                                    inputText,
                                    suggestedItemList[index],
                                    !isSelected
                                )
                            )
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )

                    .border(
                        BorderStroke(1.dp, borderColor),
                        shape = RoundedCornerShape(16.dp),
                    )
                    .background(
                        bgColor,
                        shape = RoundedCornerShape(16.dp)
                    )) {
                    chipContent(index)
                }
            }
        }
    }
}

@Composable
private fun IconToBeDisplayed(
    multiSelect: Boolean,
    selectedIndices: Set<Int>,
    index: Int,
    clickedIconDrawable: Int?,
    iconDrawable: Int,
    selectedIndex: Int,
    drawableModifier: Modifier,
) {
    Image(
        painter = painterResource(
            if (multiSelect)
                if (selectedIndices.contains(index))
                    clickedIconDrawable ?: iconDrawable
                else
                    iconDrawable
            else
                if (selectedIndex == index)
                    clickedIconDrawable ?: iconDrawable
                else
                    iconDrawable
        ),
        contentDescription = null,
        modifier = drawableModifier
    )
}
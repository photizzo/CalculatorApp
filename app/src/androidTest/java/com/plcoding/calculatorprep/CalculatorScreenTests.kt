@file:Suppress("IllegalIdentifier")

package com.plcoding.calculatorprep

import androidx.activity.ComponentActivity
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.plcoding.calculatorprep.ui.theme.CalculatorPrepTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenTests {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun add_two_numbers_passes() {
        composeTestRule.setContent {
            CalculatorPrepTheme {
                CalculatorScreen(
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }

        //1
        composeTestRule.onNodeWithText("2")
            //2
            .performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        //3
        composeTestRule.onNodeWithText("30.0").assertExists()
    }

    @Test
    fun subtract_two_numbers_passes() {
        composeTestRule.setContent {
            CalculatorPrepTheme {
                CalculatorScreen(
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }

        composeTestRule.onNodeWithTag("test-button-two").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("-").performClick()
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithText("10.0").assertExists()
    }

    @Test
    fun multiply_two_numbers_passes() {
        composeTestRule.setContent {
            CalculatorPrepTheme {
                CalculatorScreen(
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }

        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("x").performClick()
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithText("200.0").assertExists()
    }

    @Test
    fun divide_two_numbers_passes() {
        composeTestRule.setContent {
            CalculatorPrepTheme {
                CalculatorScreen(
                    state = viewModel.state,
                    onAction = viewModel::onAction
                )
            }
        }

        viewModel.state = viewModel.state.copy(
            number1 = "20",
            operation = CalculatorOperation.Divide,
            number2 = "10"
        )

        composeTestRule.onNodeWithText("=").performClick()
        composeTestRule.onNodeWithText("2.0").assertExists()
    }


}
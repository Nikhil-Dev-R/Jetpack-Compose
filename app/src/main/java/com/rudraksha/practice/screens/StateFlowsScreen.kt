package com.rudraksha.practice.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rudraksha.practice.stateflows.LiveDataExample
import com.rudraksha.practice.stateflows.MyLiveDataViewModel
import com.rudraksha.practice.stateflows.MyStateFlowViewModel
import com.rudraksha.practice.stateflows.StateFlowExample


@Composable
fun StateFlowsScreen(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "StateFlowExample")
            StateFlowExample(viewModel = MyStateFlowViewModel())
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "LiveDataExample")
            LiveDataExample(viewModel = MyLiveDataViewModel())
        }
    }
}
